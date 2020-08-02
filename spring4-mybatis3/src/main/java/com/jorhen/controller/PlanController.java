package com.jorhen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Plan;
import com.jorhen.service.OptionService;
import com.jorhen.service.PlanServiceI;

@Controller
@RequestMapping("/plan")
public class PlanController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(PlanController.class);

	// 處理業務邏輯的planService
	@Autowired
	private PlanServiceI planService;
    @Autowired
    OptionService optionService;
	
	List<Plan> lsts = null;
	String rder = null;		

	public PlanServiceI getPlan() {
		return planService;
	}

	public void setPlan(PlanServiceI plan) {
		planService = plan;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = planService.getMyPlan(user.getuName());
		model.addAttribute("lsts", lsts);
		model.addAttribute("options",this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus",this.optionService.planStatusOption());
		log.info("=plan=uName==" + user.getuName());
		return "plan/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "pId", required = false) String pId) {
		Plan plan = planService.getById(pId);
		model.addAttribute("plan", plan);
		model.addAttribute("options",this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus",this.optionService.planStatusOption());
		return "plan/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Plan plan) {
		
		plan.setMder(user.getuName());
		int aa = planService.updateByPrimaryKeySelective(plan);
		log.info("==aa==" + aa);
		return "redirect:/plan/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("options",this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus",this.optionService.planStatusOption());
		return "plan/add";
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Plan plan) {
		plan.setRder(user.getuName());
		planService.add(plan);
		return "redirect:/plan/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "pId", required = false) String pId) {
		System.out.println("id=" + pId);
		planService.deleteByPrimaryKey(pId);
		return "redirect:/plan/index.do";
	}
	
	// 管理介面
	@RequestMapping(value = "/query")
	public String query(ModelMap model) {

		lsts = planService.getMyPlan(user.getuName());
		model.addAttribute("lsts", lsts);
		return "plan/query";
	}

}
