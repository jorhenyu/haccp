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

import com.jorhen.domain.Pc;
import com.jorhen.domain.Ps;
import com.jorhen.service.PcServiceI;

@Controller
@RequestMapping("/pc")
public class PcController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(PcController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private PcServiceI pcService;
	
	List<Pc> lsts = null;
	String rder = null;		

	public PcServiceI getPc() {
		return pcService;
	}

	public void setPc(PcServiceI pc) {
		pcService = pc;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = pcService.getMyPc(user.getuName());
		model.addAttribute("lsts", lsts);				
		log.info("=plan=uName==" + user.getuName());
		return "pc/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "pcId", required = false) String pcId) {
		Pc pc = pcService.selectPcById(pcId);
		model.addAttribute("pc", pc);				
		return "pc/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Pc pc) {
		
		pc.setMder(user.getuName());
		int aa = pcService.updateByPrimaryKeySelective(pc);
		log.info("==aa==" + aa);
		return "redirect:/pc/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(Model model) {				
		return "pc/add";
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Pc pc) {
		pc.setRder(user.getuName());
		pcService.insert(pc);
		return "redirect:/pc/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "pcId", required = false) String pcId) {
		System.out.println("id=" + pcId);
		pcService.deleteByPrimaryKey(pcId);
		return "redirect:/pc/index.do";
	}
	


}
