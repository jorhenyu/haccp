package com.jorhen.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Ccp;
import com.jorhen.domain.Ha;
import com.jorhen.domain.Query;
import com.jorhen.service.CcpServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.util.ExcelUtil;

@Controller
@RequestMapping("/ccp")
public class CcpController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(CcpController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private CcpServiceI ccpService;
	
    @Autowired
    OptionService optionService;
	
	List<Ccp> lsts = null;
	String rder = null;		

	public CcpServiceI getCcp() {
		return ccpService;
	}

	public void setCcp(CcpServiceI ccp) {
		ccpService = ccp;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = ccpService.getMyCcp(user.getuName());
		model.addAttribute("lsts", lsts);				
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		return "ccp/mydata";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "ccpId", required = false) String ccpId) {
		Ccp ccp = ccpService.selectCcpById(ccpId);
		model.addAttribute("ccp", ccp);
		
		if(ccp.getqTb().equals("3")) {
			return "ccp/update3t";
		}else {
			return "ccp/update4t";
		}	
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Ccp ccp) {
		
		ccp.setMder(user.getuName());
		int aa = ccpService.updateByPrimaryKeySelective(ccp);
		log.info("==aa==" + aa);
		return "redirect:/ccp/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {	
		String jt = request.getParameter("jt");
		System.out.println("=jt=="+jt);
		if(jt.equals("3")) {
			return "ccp/add3t";
		}else {
			return "ccp/add4t";
		}
		
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Ccp ccp) {
		ccp.setRder(user.getuName());
		ccpService.insert(ccp);
		return "redirect:/ccp/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "ccpId", required = false) String ccpId) {
		System.out.println("id=" + ccpId);
		ccpService.deleteByPrimaryKey(ccpId);
		return "redirect:/ccp/index.do";
	}
	
	// 管理介面
	@RequestMapping(value = "/query")
	public String query(HttpServletRequest request, ModelMap model, Ccp ccp) {
		String pName = request.getParameter("pName");		
		lsts = ccpService.selectSHaByPname(pName ,user.getuName());
		model.addAttribute("lsts", lsts);
		return "ccp/query";
	}
	
	
	@RequestMapping("/report")
	public String queryPro(HttpServletRequest request, ModelMap model) {
       //產生目前自己有的專案
		lsts = ccpService.getCcpByPlanIdDistinct(user.getuName());
		model.addAttribute("lsts", lsts);		
		return "ccp/report";

	}

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Ccp ccp) {

		log.info("==getPlanId==" + ccp.getPlanId());		 
		
		//获取数据		
		lsts = ccpService.selectCcpByPlanId(ccp.getPlanId());		
	      
        
        //excel标题
        String[] title = {"加工步驟","危害","危害描述","Q1.對危害是否有防制措施？","Q2.此步驟可消除或降低危害至可接受水準？","Q3.污染能使危害達到或增至不可接受之水準？","Q4.接續步驟能使危害被消除或降低至可接受之水準？","CCP"};
        //excel文件名
        String fileName = "ccpTable"+System.currentTimeMillis()+".xls";
        //sheet名
        String sheetName = "重要管制點判定表";
        String [][] content = new String[lsts.size()][];
        for (int i = 0; i < lsts.size(); i++) {
            content[i] = new String[title.length];
            ccp = lsts.get(i);
            content[i][0] = ccp.getHa().getProcStep();
            
            if(ccp.getHa().getpHa().equals("phy")) {
            	content[i][1] = "物理性";
            }else if(ccp.getHa().getpHa().equals("chem")) {
            	content[i][1] = "化學性";
            }else {
            	content[i][1] = "生物性";
            }
            content[i][2] = ccp.getHa().getHaDesc();
            content[i][3] = ccp.getQ1();
            content[i][4] = ccp.getQ1();
            content[i][5] = ccp.getQ1();
            content[i][6] = ccp.getQ1();
            content[i][7] = ccp.getQ1();       
            
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

		

	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 參數查詢
	@RequestMapping(value = "/queryByparm")
	public String queryByparm(ModelMap model, Query query) {

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人，就自己資料全撈
			lsts = ccpService.getMyCcp(user.getuName());
		} else {
			// 其他狀態就撈公開跟協作資料
			lsts = ccpService.getMyCcpByQuery(query);
		}

		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人就進可以修刪功能頁面
			return "ccp/mydata";
		} else {
			// 其他狀態就根據狀態處理
			return "ccp/data";
		}
	}
	
	@RequestMapping(value = "/cowork")
	public String cowork(ModelMap model, Ccp ccp) {

		ccp = ccpService.selectCcpById(ccp.getCcpId());
		model.addAttribute("ccp", ccp);
		return "ccp/coworkpw";
	}
	

}
