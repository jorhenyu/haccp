package com.jorhen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jorhen.domain.User;
import com.jorhen.service.UserServiceI;

@SessionAttributes(value = { "user" })
@Controller
@RequestMapping("/")
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

		boolean isUserExist = userService.isUserExist(user.getuName(), user.getuPw());
		log.info("==isUserNameExist==" + isUserExist);

		log.info("==uName==" + user.getuName());
		log.info("==uPw==" + user.getuPw());
		log.info("==user.getuName()=guest=" + user.getuName().trim() == "guest");
		log.info("== user.getuName().equals=guest=" + user.getuName().equals("guest"));

		if (user.getuName() == "guest" || user.getuName().equals("guest")) {
			modelMap.addAttribute("user", user);
			return "main";
		}

		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
		} else if (!isUserExist) {
			// 向BindingResult添加使用者未存在的校驗錯誤
			bindingResult.rejectValue("uName", "帳號密碼錯誤", "帳號密碼錯誤");

		} else {
			user = userService.findUserByNamePw(user.getuName(), user.getuPw());
			modelMap.addAttribute("user", user);
			return "main";
		}

		return "index";
	}

	@RequestMapping(value = "/myIndex")
	public String myIndex(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("user") User user) {
		log.info("==uName==" + user.getuName());
		log.info("==uPw==" + user.getuPw());
		modelMap.addAttribute("user", user);
		return "main";
	}
	
	@RequestMapping(value = "/toLogout")
	public String toLogout(HttpServletRequest request) {
		log.info("==tt==");
		HttpSession httpSession = request.getSession();
        httpSession.invalidate();			       
		return "index";
	}
	

}
