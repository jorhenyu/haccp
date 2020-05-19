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

import com.jorhen.domain.User;
import com.jorhen.service.UserServiceI;

@Controller
@RequestMapping("/process")
// @SessionAttributes(value={"lstUsers","urlName"},types={List.class,String.class})
public class ProcessController {

	// 設置log
	private static Logger log = Logger.getLogger(ProcessController.class.getName());



	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "userPwd", required = false) String userPwd) {

		System.out.println("name=" + username);
		System.out.println("password=" + userPwd);

	
		// log.debug("page"+page);
		/*
		 * List<User> u = null; if(null !=name && !name.equals("")){ User user = new
		 * User(); user.setName(name); u = userService.selectAll(user); } else { u =
		 * userService.selectAll(null); }
		 * 
		 * PageInfo<User> pageInfo = new PageInfo<User>(u); log.debug(u);
		 * log.debug(pageInfo); modelMap.addAttribute("user", u);
		 * modelMap.addAttribute("name", name); modelMap.addAttribute("pageInfo",
		 * pageInfo.getNavigatepageNums());
		 */
		// return "user/index";
		return "process/data";
	}


}
