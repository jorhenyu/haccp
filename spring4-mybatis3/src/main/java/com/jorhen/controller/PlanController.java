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

import com.jorhen.domain.Ccp;
import com.jorhen.domain.Ha;
import com.jorhen.domain.Pc;
import com.jorhen.domain.Plan;
import com.jorhen.domain.Ps;
import com.jorhen.domain.Query;
import com.jorhen.domain.Team;
import com.jorhen.domain.User;
import com.jorhen.service.CcpServiceI;
import com.jorhen.service.HaServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.service.PcServiceI;
import com.jorhen.service.PlanServiceI;
import com.jorhen.service.PsServiceI;
import com.jorhen.service.TeamServiceI;
import com.jorhen.service.UserServiceI;

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

	@Autowired
	private UserServiceI userService;

	@Autowired
	private TeamServiceI teamService;

	@Autowired
	private PsServiceI psService;

	@Autowired
	private PcServiceI pcService;

	@Autowired
	private HaServiceI haService;

	@Autowired
	private CcpServiceI ccpService;

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
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		log.info("=plan=uName==" + user.getuName());
		return "plan/mydata";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "pId", required = false) String pId) {
		Plan plan = planService.getById(pId);
		model.addAttribute("plan", plan);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
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
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
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

	// 查planid
	@RequestMapping(value = "/query")
	public String query(ModelMap model) {

		lsts = planService.getMyPlan(user.getuName());
		model.addAttribute("lsts", lsts);
		return "plan/query";
	}

	// 參數查詢
	@RequestMapping(value = "/queryByparm")
	public String queryByparm(ModelMap model, Query query) {

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人，就自己資料全撈
			lsts = planService.getMyPlan(user.getuName());
		} else {
			// 其他狀態就撈公開跟協作資料
			lsts = planService.getMyPlanByQuery(query);
		}

		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人就進可以修刪功能頁面
			return "plan/mydata";
		} else {
			// 其他狀態就根據狀態處理
			return "plan/data";
		}
	}

	@RequestMapping(value = "/cowork")
	public String cowork(ModelMap model, Plan plan) {

		plan = planService.getById(plan.getpId());
		model.addAttribute("plan", plan);
		return "plan/coworkpw";
	}

	@RequestMapping(value = "/copyPlan")
	public String copyPlan(ModelMap model, Plan plan) {

		User user1 = userService.findUserByNamePw(user.getuName(), null);
		model.addAttribute("user", user1);
		model.addAttribute("plan", plan);
		return "plan/copyPlan";
	}

	@RequestMapping(value = "/doCopy")
	public String doCopy(ModelMap model, Plan plan) {

		String planId = plan.getpId();
		log.info("==planId==" + planId);

		// 1.取得要複製plan專案表單
		plan = planService.getById(plan.getpId());
		plan.setRder(user.getuName());
		// plan.setpName("複製_"+plan.getpName());
		// 將舊planid放到專案名稱中
		plan.setpName(planId);
		// 2.產生新專案
		planService.add(plan);

		// 使用舊planId找新專案ID
		Query query = new Query();
		query.setqPname(planId);
		String newPlanId = null;
		lsts = planService.getMyPlanByQuery(query);
		// 應該只有一筆
		for (Plan p : lsts) {
			newPlanId = p.getpId();
			log.info("==newPlanId==" + newPlanId);
		}

		// 先copy team表單
		List<Team> lstTeam = teamService.selectTeamByPlanId(planId);
		// 會有多筆
		for (Team t : lstTeam) {
			t.setPlanId(newPlanId); // 只換planId,其他都一樣
			teamService.addTeam(t);
		}

		// copy ps表單

		Ps ps = psService.getMyPsBypLd(planId);
		ps.setPlanId(newPlanId);
		psService.insert(ps);

		// copy pc表單
		Pc pc = pcService.getMyPcBypLd(planId);
		pc.setPlanId(newPlanId);
		pcService.insert(pc);
		/*
		// copy ha表單 多筆
		List<Ha> lstHa = haService.selectHaByPlanId(planId);

		// 新增 Ha, 會有多筆
		for (Ha h : lstHa) {
			h.setPlanId(newPlanId); // 只換planId,其他都一樣，會產生新haId
			haService.insert(h);
		}

		// copy ccp表單 多筆，取舊的CCP資料
		List<Ccp> lstCcp = ccpService.selectCcpByPlanId(planId);

		// 先插入新的CCP,更換planId
		for (Ccp c : lstCcp) {
			c.setPlanId(newPlanId); // 只換planId,其他都一樣
			ccpService.insert(c);
		}

		// 新的 Ha，取其 haId
		List<Ha> lstHaNew = haService.selectHaByPlanId(newPlanId);
		// 新的 CCP，置換 新haId
		List<Ccp> lstCcpNew = ccpService.selectCcpByPlanId(newPlanId);

		for (int i = 0; i < lstHaNew.size(); i++) {
			Ha newHa = lstHaNew.get(i);
			for (int j = 0; j < lstCcpNew.size(); j++) {
				if (lstHaNew.get(i).getProcStep().equals(lstCcpNew.get(j).getHa().getProcStep())) {
					Ccp newCcp = lstCcpNew.get(j);
					newCcp.setHaId(newHa.getHaId());
					ccpService.updateByPrimaryKeySelective(newCcp);
				}

			}
		}

		// copy haccp表單 多筆

		// User user1 = userService.findUserByNamePw(user.getuName(),null);
		// model.addAttribute("user", user1);
		 * */
		 
		return "plan/index";
	}

}
