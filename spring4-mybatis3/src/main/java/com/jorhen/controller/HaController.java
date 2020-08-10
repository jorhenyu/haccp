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

import com.jorhen.domain.Ha;
import com.jorhen.domain.Pc;
import com.jorhen.domain.Query;
import com.jorhen.service.HaServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.util.ExcelUtil;

@Controller
@RequestMapping("/ha")
public class HaController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(HaController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private HaServiceI haService;
	
    @Autowired
    OptionService optionService;
	
	List<Ha> lsts = null;
	String rder = null;		

	public HaServiceI getHa() {
		return haService;
	}

	public void setHa(HaServiceI ha) {
		haService = ha;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = haService.getMyHa(user.getuName());
		model.addAttribute("lsts", lsts);	
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		
		return "ha/mydata";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "haId", required = false) String haId) {
		Ha ha = haService.selectHaById(haId);
		model.addAttribute("ha", ha);				
		return "ha/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Ha ha) {
		
		ha.setMder(user.getuName());
		int aa = haService.updateByPrimaryKeySelective(ha);
		log.info("==aa==" + aa);
		return "redirect:/ha/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(Model model) {				
		return "ha/add";
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Ha ha) {
		ha.setRder(user.getuName());
		haService.insert(ha);
		return "redirect:/ha/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "haId", required = false) String haId) {
		System.out.println("id=" + haId);
		haService.deleteByPrimaryKey(haId);
		return "redirect:/ha/index.do";
	}
	
	// 管理介面
	@RequestMapping(value = "/query")
	public String query(ModelMap model) {

		lsts = haService.getMyHa(user.getuName());
		model.addAttribute("lsts", lsts);
		return "ha/query";
	}
	
	@RequestMapping("/report")
	public String queryPro(HttpServletRequest request, ModelMap model) {
       //產生目前自己有的專案
		lsts = haService.getHaByPlanIdDistinct(user.getuName());
		model.addAttribute("lsts", lsts);		
		return "ha/report";

	}

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Ha ha) {

		log.info("==getPlanId==" + ha.getPlanId());		 
		
		//获取数据		
		lsts = haService.selectHaByPlanId(ha.getPlanId());		
	      
        
        //excel标题
        String[] title = {"加工步驟","危害","危害描述","影響產品安全","判定左欄之理由","預防措施","本步驟是否為重要管制點"};
        //excel文件名
        String fileName = "hazardAnalysisTable"+System.currentTimeMillis()+".xls";
        //sheet名
        String sheetName = "危害分析工作表";
        String [][] content = new String[lsts.size()][];
        for (int i = 0; i < lsts.size(); i++) {
            content[i] = new String[title.length];
            ha = lsts.get(i);
            content[i][0] = ha.getProcStep();
            
            if(ha.getpHa().equals("phy")) {
            	content[i][1] = "物理性";
            }else if(ha.getpHa().equals("chem")) {
            	content[i][1] = "化學性";
            }else {
            	content[i][1] = "生物性";
            }
            content[i][2] = ha.getHaDesc();
            content[i][3] = ha.getIssafe();
            content[i][4] = ha.getReason();
            content[i][5] = ha.getpMeas();
            content[i][6] = ha.getCcp().getCcp();
                  
            
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
			lsts = haService.getMyHa(user.getuName());
		} else {
			// 其他狀態就撈公開跟協作資料
			lsts = haService.getMyHaByQuery(query);
		}

		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人就進可以修刪功能頁面
			return "ha/mydata";
		} else {
			// 其他狀態就根據狀態處理
			return "ha/data";
		}
	}
	
	@RequestMapping(value = "/cowork")
	public String cowork(ModelMap model, Ha ha) {

		ha = haService.selectHaById(ha.getHaId());
		model.addAttribute("ha", ha);
		return "ha/coworkpw";
	}
	


}
