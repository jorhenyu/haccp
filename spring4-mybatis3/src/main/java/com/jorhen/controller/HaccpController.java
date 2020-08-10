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
import com.jorhen.domain.Haccp;
import com.jorhen.domain.Query;
import com.jorhen.service.HaccpServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.util.ExcelUtil;

@Controller
@RequestMapping("/haccp")
public class HaccpController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(HaccpController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private HaccpServiceI haccpService;
	
	@Autowired
	OptionService optionService;
	
	List<Haccp> lsts = null;
	String rder = null;		

	public HaccpServiceI getHaccp() {
		return haccpService;
	}

	public void setHaccp(HaccpServiceI haccp) {
		haccpService = haccp;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = haccpService.getMyHaccp(user.getuName());
		model.addAttribute("lsts", lsts);				
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		return "haccp/mydata";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "haccpId", required = false) String haccpId) {
		Haccp haccp = haccpService.selectHaccpById(haccpId);
		model.addAttribute("haccp", haccp);				
		return "haccp/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Haccp haccp) {
		
		haccp.setMder(user.getuName());
		int aa = haccpService.updateByPrimaryKeySelective(haccp);
		log.info("==aa==" + aa);
		return "redirect:/haccp/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		
		return "haccp/add";		
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Haccp haccp) {
		haccp.setRder(user.getuName());
		haccpService.insert(haccp);
		return "redirect:/haccp/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "haccpId", required = false) String haccpId) {
		System.out.println("id=" + haccpId);
		haccpService.deleteByPrimaryKey(haccpId);
		return "redirect:/haccp/index.do";
	}
	
	@RequestMapping("/report")
	public String queryPro(HttpServletRequest request, ModelMap model) {
       //產生目前自己有的專案
		lsts = haccpService.getHaccpByPlanIdDistinct(user.getuName());
		model.addAttribute("lsts", lsts);		
		return "haccp/report";

	}

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Haccp haccp) {

		log.info("==getPlanId==" + haccp.getPlanId());		 
		
		//获取数据		
		lsts = haccpService.selectHaccpByPlanId(haccp.getPlanId());		
	      
        
        //excel标题
        String[] title = {"重要管制點","危害","危害描述","管制界限","監控項目","監控方法","監控頻率","監控執行人","矯正措施","紀錄","確認"};
        //excel文件名
        String fileName = "haccpTable"+System.currentTimeMillis()+".xls";
        //sheet名
        String sheetName = "重要管制點計畫表";
        String [][] content = new String[lsts.size()][];
        for (int i = 0; i < lsts.size(); i++) {
            content[i] = new String[title.length];
            haccp = lsts.get(i);
            content[i][0] = haccp.getHa().getProcStep();
            
            if(haccp.getHa().getpHa().equals("phy")) {
            	content[i][1] = "物理性";
            }else if(haccp.getHa().getpHa().equals("chem")) {
            	content[i][1] = "化學性";
            }else {
            	content[i][1] = "生物性";
            }
            content[i][2] = haccp.getHa().getHaDesc();
            content[i][3] = haccp.getcLimit();
            content[i][4] = haccp.getmItm();
            content[i][5] = haccp.getmMd();
            content[i][6] = haccp.getmFre();
            content[i][7] = haccp.getmPrin();
            content[i][8] = haccp.getcMeas();
            content[i][9] = haccp.getRecord();
            content[i][10] = haccp.getConfirm();
            
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
			lsts = haccpService.getMyHaccp(user.getuName());
		} else {
			// 其他狀態就撈公開跟協作資料
			lsts = haccpService.getMyHaccpByQuery(query);
		}

		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人就進可以修刪功能頁面
			return "haccp/mydata";
		} else {
			// 其他狀態就根據狀態處理
			return "haccp/data";
		}
	}
	
	@RequestMapping(value = "/cowork")
	public String cowork(ModelMap model, Haccp haccp) {

		haccp = haccpService.selectHaccpById(haccp.getHaccpId());
		model.addAttribute("haccp", haccp);
		return "haccp/coworkpw";
	}
	


}
