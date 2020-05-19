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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jorhen.domain.User;
import com.jorhen.service.UserServiceI;

@Controller
@RequestMapping("/")
// @SessionAttributes("lstUsers")
public class MainController {

	// 設置log
	private static Logger log = Logger.getLogger(MainController.class.getName());

	@Autowired
	// 處理業務邏輯的userService
	private UserServiceI userService;

	public UserServiceI getUser() {
		return userService;
	}

	public void setUser(UserServiceI user) {
		userService = user;
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, ModelMap modelMap,
			@Validated @ModelAttribute("userDetail") User user, BindingResult bindingResult) {

		boolean isUserNameExist = userService.findUserByUsername(user.getUserName(), user.getUserPwd());

		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
		} else if (!isUserNameExist) {
			// 向BindingResult添加使用者未存在的校驗錯誤
			bindingResult.rejectValue("userName", "該用戶未存在", "該用戶未存在");

		} else {

			List<User> lstUsers = userService.getAllUser();
			modelMap.addAttribute("lstUsers", lstUsers);
			return "main";
		}

		modelMap.addAttribute("user", user);

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
		return "index";
	}

}
