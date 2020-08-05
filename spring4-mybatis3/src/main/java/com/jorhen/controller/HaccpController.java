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

import com.jorhen.domain.Haccp;
import com.jorhen.service.HaccpServiceI;

@Controller
@RequestMapping("/haccp")
public class HaccpController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(HaccpController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private HaccpServiceI haccpService;
	
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
		log.info("=plan=uName==" + user.getuName());
		return "haccp/data";
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
	


}
