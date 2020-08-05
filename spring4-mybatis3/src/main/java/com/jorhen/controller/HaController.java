package com.jorhen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Ha;
import com.jorhen.service.HaServiceI;

@Controller
@RequestMapping("/ha")
public class HaController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(HaController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private HaServiceI haService;
	
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
		log.info("=plan=uName==" + user.getuName());
		return "ha/data";
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
	


}
