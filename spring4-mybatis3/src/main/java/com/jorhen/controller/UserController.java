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
@RequestMapping("/user")
// @SessionAttributes(value={"lstUsers","urlName"},types={List.class,String.class})
public class UserController {

	// 設置log
	private static Logger log = Logger.getLogger(UserController.class.getName());

	// 處理業務邏輯的userService
	@Autowired
	private UserServiceI userService;

	public UserServiceI getUser() {
		return userService;
	}

	public void setUser(UserServiceI user) {
		userService = user;
	}

	@RequestMapping(value = "/mgtInfo/index")
	public String index(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "userPwd", required = false) String userPwd) {

		System.out.println("name=" + username);
		System.out.println("password=" + userPwd);

		List<User> lstUsers = null;

		lstUsers = userService.getAllUser();

		// for (User item : lstUsers) {
		// System.out.println(item.getUserName());
		// System.out.println(item.getUserBirthday());
		// }
		modelMap.addAttribute("lstUsers", lstUsers);

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
		return "user/mgtInfo/data";
	}
	@RequestMapping(value = "/myInfo/index")
	public String indexMyinfo(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "userPwd", required = false) String userPwd) {

		System.out.println("name=" + username);
		System.out.println("password=" + userPwd);

		List<User> lstUsers = null;

		lstUsers = userService.getAllUser();

		// for (User item : lstUsers) {
		// System.out.println(item.getUserName());
		// System.out.println(item.getUserBirthday());
		// }
		modelMap.addAttribute("lstUsers", lstUsers);

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
		return "user/myInfo/data";
	}
	/*
	 * 
	 * 
	 * @RequestMapping(value="/index", method={RequestMethod.GET}) public String
	 * index(HttpServletRequest request, ModelMap
	 * modelMap, @RequestParam(value="name", required=false) String
	 * name, @RequestParam(value="page", defaultValue="1") String paramPage){
	 * 
	 * if(null == paramPage){ paramPage = "1"; }
	 * 
	 * int page = Integer.parseInt(paramPage); int pageSize = 3;
	 * log.debug("name="+name); log.debug("page"+page); PageHelper.startPage(page,
	 * pageSize); PageHelper.orderBy("id desc"); List<User> u = null; if(null !=name
	 * && !name.equals("")){ User user = new User(); user.setName(name); u =
	 * userService.selectAll(user); } else { u = userService.selectAll(null); }
	 * 
	 * PageInfo<User> pageInfo = new PageInfo<User>(u); log.debug(u);
	 * log.debug(pageInfo); modelMap.addAttribute("user", u);
	 * modelMap.addAttribute("name", name); modelMap.addAttribute("pageInfo",
	 * pageInfo.getNavigatepageNums()); return "user/index"; }
	 * 
	 * 
	 * userService.updateByPrimaryKey(user); return "redirect:index.do"; }
	 */

	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "userId", required = false) String userId) {
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		return "user/update";
	}

	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap,
			@Validated @ModelAttribute("userDetail") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			return "user/update";
		}

		int aa = userService.updateByPrimaryKeySelective(user);
		log.info("==aa=="+aa);
		return "redirect:/user/index.do";		

	}

	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}

	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap,
			@Validated @ModelAttribute("userDetail") User user, BindingResult bindingResult) {

		boolean isUserNameExist = userService.findUserByUsername(user.getUserName(), null);
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
		} else if (isUserNameExist) {
			// 向BindingResult添加使用者已存在的校驗錯誤
			bindingResult.rejectValue("userName", "該用戶名已存在", "該用戶名已存在");
		} else {
			userService.addUser(user);
			return "redirect:/user/index.do";
		}

		modelMap.addAttribute("user", user);

		return "user/add";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "userId", required = false) String userId) {
		System.out.println("id=" + userId);
		userService.deleteByPrimaryKey(userId);
		return "redirect:/user/index.do";
	}

}
