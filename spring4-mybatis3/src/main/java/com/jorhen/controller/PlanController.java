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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jorhen.domain.Ccp;
import com.jorhen.domain.File;
import com.jorhen.domain.Ha;
import com.jorhen.domain.Haccp;
import com.jorhen.domain.Pc;
import com.jorhen.domain.Plan;
import com.jorhen.domain.Ps;
import com.jorhen.domain.Query;
import com.jorhen.domain.Team;
import com.jorhen.domain.User;
import com.jorhen.service.CcpServiceI;
import com.jorhen.service.FileServiceI;
import com.jorhen.service.HaServiceI;
import com.jorhen.service.HaccpServiceI;
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
	
	@Autowired
	private FileServiceI fileService;

	
	@Autowired
	private HaccpServiceI haccpService;

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
	public String query(ModelMap model, Query query) {

		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		query.setqRder(user.getuName());
		query.setqPstatus("");
		// lsts = haService.getMyHa(user.getuName());
		lsts = planService.getMyPlanByQuery(query);
		PageInfo<Plan> pageInfo = new PageInfo<Plan>(lsts);
		// lsts = pageInfo.getList();
		model.addAttribute("lsts", lsts);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		//lsts = planService.getMyPlan(user.getuName());
		//model.addAttribute("lsts", lsts);
		return "plan/query";
	}

	// 參數查詢
	@RequestMapping(value = "/queryByparm")
	public String queryByparm(ModelMap model, Query query) {
		
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人，就自己資料全撈
			query.setqRder(user.getuName());
			query.setqPstatus("");
			// lsts = haService.getMyHa(user.getuName());
			lsts = planService.getMyPlanByQuery(query);
			PageInfo<Plan> pageInfo = new PageInfo<Plan>(lsts);
			// lsts = pageInfo.getList();
			model.addAttribute("lsts", lsts);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("options", this.optionService.catsTypeOption(null));
			model.addAttribute("planStatus", this.optionService.planStatusOption());
			return "plan/mydata";
		} else {
			lsts = planService.getMyPlanByQuery(query);
			PageInfo<Plan> pageInfo = new PageInfo<Plan>(lsts);
			// lsts = pageInfo.getList();
			model.addAttribute("lsts", lsts);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("options", this.optionService.catsTypeOption(null));
			model.addAttribute("planStatus", this.optionService.planStatusOption());
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
	public String doCopy(ModelMap model, @RequestParam(value = "pId", required = false) String pId,
			@RequestParam(value = "rdPot", required = false) String rdPot) {
		
		String planId = pId;
		log.info("==planId==" + planId);
		log.info("==rdPot==" + rdPot);
		Plan plan = new Plan();
		//String planId = plan.getpId();
		//String planId = "550c523c91614eeeacdc7937cccf6e2a";//old
		log.info("1==planId==" + planId);

		// 1.取得要複製plan專案表單
		plan = planService.getById(planId);
		log.info("=2=getpName==" + plan.getpName());
		
		log.info("=2=getRder==" + plan.getRder());
		//公開表單的會員
		User myUser = userService.findUserByNamePw(plan.getRder(), null);
		
		//String newPlanId = "66908277ad404b2a8a4e37c287b72f91";
		//如果分享專案會員不是管理者，就加5分
		if(myUser.getuPosi().equals("mber")) {			
			int myRdPot = Integer.parseInt(myUser.getRdPot())+5;
			myUser.setRdPot(String.valueOf(myRdPot));
			userService.updateByPrimaryKeySelective(myUser);			
		}
		//如果複製專案會員，就減5分
		myUser = userService.findUserByNamePw(user.getuName(), null);
		myUser.setRdPot(rdPot);
		userService.updateByPrimaryKeySelective(myUser);
		
		String pNameOld = null;
		
		plan.setRder(user.getuName());
		// plan.setpName("複製_"+plan.getpName());
		// 將舊planid放到專案名稱中
		pNameOld = plan.getpName();
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
			newPlanId = p.getpId();  //new
			log.info("==newPlanId==" + newPlanId);
		}
		
		//再把新plan的planName改回來
		plan = planService.getById(newPlanId);
		plan.setpName(pNameOld);
		planService.updateByPrimaryKeySelective(plan);
		

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
		
		// copy file表單
		File file = fileService.getMyFileBypLd(planId);
        file.setPlanId(newPlanId);
        fileService.insert(file);
	
		
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
			log.info("=s=HaId==" +c.getHaId());
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
		
		
		// copy haccp表單 多筆，取舊的haccp資料
		List<Haccp> lstHaccp = haccpService.selectHaccpByPlanId(planId);
		
		// 先插入新的haccp,更換planId
		for (Haccp h : lstHaccp) {
			h.setPlanId(newPlanId); // 只換planId,其他都一樣
			log.info("=s=HaccpId==" +h.getHaccpId());
			haccpService.insert(h);
		}
		
		// copy haccp表單 多筆

		// 新的 Ha，取其 hacId
		//List<Ha> lstHaNew = haService.selectHaByPlanId(newPlanId);
		// 新的 haccp，置換 新haId
		List<Haccp> lstHaccpNew = haccpService.selectHaccpByPlanId(newPlanId);

		for (int i = 0; i < lstHaNew.size(); i++) {
			Ha newHa = lstHaNew.get(i);
			for (int j = 0; j < lstHaccpNew.size(); j++) {
				if (lstHaNew.get(i).getProcStep().equals(lstHaccpNew.get(j).getHa().getProcStep())) {
					Haccp newHaccp = lstHaccpNew.get(j);
					newHaccp.setHaId(newHa.getHaId());
					haccpService.updateByPrimaryKeySelective(newHaccp);
				}

			}
		}
		
		return "plan/index";
	}

}
