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

import com.jorhen.domain.Ps;
import com.jorhen.service.OptionService;
import com.jorhen.service.PsServiceI;

@Controller
@RequestMapping("/ps")
public class PsController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(PsController.class);

	// 處理業務邏輯的psService
	@Autowired
	private PsServiceI psService;
    @Autowired
    OptionService optionService;
	
	List<Ps> lsts = null;
	String rder = null;		

	public PsServiceI getPs() {
		return psService;
	}

	public void setPs(PsServiceI ps) {
		psService = ps;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = psService.getMyPs(user.getuName());
		model.addAttribute("lsts", lsts);
		model.addAttribute("options",this.optionService.catsTypeOption(null));		
		log.info("=plan=uName==" + user.getuName());
		return "ps/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "psId", required = false) String psId) {
		Ps ps = psService.selectPsById(psId);
		model.addAttribute("ps", ps);
		model.addAttribute("options",this.optionService.catsTypeOption(null));		
		return "ps/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Ps ps) {
		
		ps.setMder(user.getuName());
		int aa = psService.updateByPrimaryKeySelective(ps);
		log.info("==aa==" + aa);
		return "redirect:/ps/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("options",this.optionService.catsTypeOption(null));		
		return "ps/add";
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Ps ps) {
		ps.setRder(user.getuName());
		psService.insert(ps);
		return "redirect:/ps/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "psId", required = false) String psId) {
		System.out.println("id=" + psId);
		psService.deleteByPrimaryKey(psId);
		return "redirect:/ps/index.do";
	}
	


}
