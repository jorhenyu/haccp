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

import com.jorhen.domain.Monit;
import com.jorhen.service.MonitServiceI;

@Controller
@RequestMapping("/monit")
public class MonitController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(MonitController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private MonitServiceI monitService;
	
	List<Monit> lsts = null;
	String rder = null;		

	public MonitServiceI getMonit() {
		return monitService;
	}

	public void setCcp(MonitServiceI monit) {
		monitService = monit;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = monitService.getMyMonit(user.getuName());
		model.addAttribute("lsts", lsts);				
		log.info("=plan=uName==" + user.getuName());
		return "monit/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "monitId", required = false) String monitId) {
		Monit monit = monitService.selectMonitById(monitId);
		model.addAttribute("monit", monit);				
		return "monit/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Monit monit) {
		
		monit.setMder(user.getuName());
		int aa = monitService.updateByPrimaryKeySelective(monit);
		log.info("==aa==" + aa);
		return "redirect:/monit/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {	
		String jt = request.getParameter("jt");
		System.out.println("=jt=="+jt);
		if(jt.equals("3")) {
			return "monit/add3t";
		}else {
			return "monit/add4t";
		}
		
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Monit monit) {
		monit.setRder(user.getuName());
		monitService.insert(monit);
		return "redirect:/monit/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "ccpId", required = false) String monitId) {
		System.out.println("id=" + monitId);
		monitService.deleteByPrimaryKey(monitId);
		return "redirect:/monit/index.do";
	}
	


}
