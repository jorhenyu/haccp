package com.jorhen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Team;
import com.jorhen.domain.TeamForm;
import com.jorhen.service.TeamServiceI;

@Controller
@RequestMapping("/team")
// @SessionAttributes(value={"lstUsers","urlName"},types={List.class,String.class})
public class TeamController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(TeamController.class);

	// 處理業務邏輯的teamService
	@Autowired
	private TeamServiceI teamService;
	List<Team> lstTeams = null;

	public TeamServiceI getTeam() {
		return teamService;
	}

	public void setTeam(TeamServiceI team) {
		teamService = team;
	}

	// 管理介面
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, ModelMap modelMap, Team team) {

		lstTeams = teamService.getAllTeam(user.getuName());
		modelMap.addAttribute("lstTeams", lstTeams);

		return "team/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "teamId", required = false) String teamId) {
		Team team = teamService.getTeamById(teamId);
		modelMap.addAttribute("team", team);
		return "team/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Team team) {
		team.setMder(user.getuName());
		int aa = teamService.updateByPrimaryKeySelective(team);
		log.info("==aa==" + aa);
		return "redirect:/team/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add() {
		return "team/add";
	}

	// 新增執行
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("teamForm") TeamForm teamForm, Team team1) {
		/*
		 * if (StringUtils.isNotBlank(team.getPlanId())) {
		 * team.setRder(user.getuName()); teamService.addTeam(team); return
		 * "redirect:/team/index.do"; } else { // 向BindingResult添加類別已存在的校驗錯誤
		 * bindingResult.rejectValue("planId", "專案ID不存在", "專案ID不存在");
		 * modelMap.addAttribute("team", team); return "team/add"; }
		 */
		System.out.println("===PlanId===="+team1.getPlanId());
		String planId = team1.getPlanId();
		
		List<Team> teams = teamForm.getTeams();

		if (null != teams && teams.size() > 0) {			
			for (Team team : teams) {
				team.setPlanId(planId);
				team.setRder(user.getuName()); 
				teamService.addTeam(team);				
			}
		}		

		return "redirect:/team/index.do";

	}

	// 類別刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "teamId", required = false) String teamId) {
		System.out.println("id=" + teamId);
		teamService.deleteByPrimaryKey(teamId);
		return "redirect:/team/index.do";
	}

}
