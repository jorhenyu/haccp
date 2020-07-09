package com.jorhen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Cat;
import com.jorhen.domain.Team;
import com.jorhen.domain.User;
import com.jorhen.service.CatServiceI;
import com.jorhen.service.TeamServiceI;
import com.jorhen.service.UserServiceI;

@Controller
@RequestMapping("/team")
// @SessionAttributes(value={"lstUsers","urlName"},types={List.class,String.class})
public class TeamController {

	// 設置log
	private static Logger log = Logger.getLogger(TeamController.class.getName());

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
	public String index(HttpServletRequest request, ModelMap modelMap) {

		lstTeams = teamService.getAllTeam();
		modelMap.addAttribute("lstTeams", lstTeams);

		return "team/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "planId", required = false) String planId) {
		Team team = teamService.getTeamById(planId);
		modelMap.addAttribute("team", team);
		return "team/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Team team) {

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
	public String doAdd(HttpServletRequest request, ModelMap modelMap,
			@Validated @ModelAttribute("teamDetail") Team team, BindingResult bindingResult) {

		boolean isIdExist = teamService.findTeamById(team.getPlanId());
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
		} else if (isIdExist) {
			// 向BindingResult添加已存在的校驗錯誤
			bindingResult.rejectValue("planId", "該類別已存在", "該類別已存在");
		} else {
			teamService.addTeam(team);
			return "redirect:/team/index.do";
		}

		modelMap.addAttribute("team", team);

		return "team/add";
	}

	// 類別刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "planId", required = false) String planId) {
		System.out.println("id=" + planId);
		teamService.deleteByPrimaryKey(planId);
		return "redirect:/team/index.do";
	}

}
