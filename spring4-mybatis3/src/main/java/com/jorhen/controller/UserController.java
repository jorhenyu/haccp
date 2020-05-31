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
import com.jorhen.domain.User;
import com.jorhen.service.CatServiceI;
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
	List<User> lstUsers = null;
	
	// 處理業務邏輯的catService
	@Autowired
	private CatServiceI catService;
	List<Cat> lstCats = null;

	public UserServiceI getUser() {
		return userService;
	}

	public void setUser(UserServiceI user) {
		userService = user;
	}
	
	public CatServiceI getCat() {
		return catService;
	}

	public void setCat(CatServiceI cat) {
		catService = cat;
	}
	
	
    //管理者首面
	@RequestMapping(value = "/mgtInfo/index")
	public String mgtInfo() {
		return "user/mgtInfo/data";
	}
	//會員管理介面
	@RequestMapping(value = "/mber/index")
	public String mberIndex(HttpServletRequest request, ModelMap modelMap) {
		
		lstUsers = userService.getAllUser();
		modelMap.addAttribute("lstUsers", lstUsers);

		return "user/mgtInfo/mber/data";
	}	
   //會員更新介面
	@RequestMapping("/mber/update")
	public String mberUpdate(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "uId", required = false) String uId) {
		User user = userService.getUserById(uId);
		modelMap.addAttribute("user", user);
		return "user/mgtInfo/mber/update";
	}
	//會員更新執行
	@RequestMapping("/mber/doUpdate")
	public String doMberUpdate(HttpServletRequest request, ModelMap modelMap,
			@Validated @ModelAttribute("userDetail") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			return "user/mber/update";
		}

		int aa = userService.updateByPrimaryKeySelective(user);
		log.info("==aa=="+aa);
		return "redirect:/user/mber/index.do";		

	}
	//會員新增導引
	@RequestMapping("/mber/add")
	public String mberAdd() {
		return "user/mgtInfo/mber/add";
	}
	//會員新增執行
	@RequestMapping("/mber/doAdd")
	public String doUserAdd(HttpServletRequest request, ModelMap modelMap,
			@Validated @ModelAttribute("userDetail") User user, BindingResult bindingResult) {
		log.info("==aa=="+user.getuName());
		boolean isUserNameExist = userService.findUserByUsername(user.getuName(), null);
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
		} else if (isUserNameExist) {
			// 向BindingResult添加使用者已存在的校驗錯誤
			bindingResult.rejectValue("uName", "該用戶名已存在", "該用戶名已存在");
		} else {
			userService.addUser(user);
			return "redirect:/user/mber/index.do";
		}

		modelMap.addAttribute("user", user);

		return "user/mgtInfo/mber/add";
	}
	//會員刪除
	@RequestMapping("/mber/del")
	public String mberDel(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "uId", required = false) String uId) {
		System.out.println("id=" + uId);
		userService.deleteByPrimaryKey(uId);
		return "redirect:/user/mber/index.do";
	}
	
	//類別管理介面
	@RequestMapping(value = "/cat/index")
	public String catIndex(HttpServletRequest request, ModelMap modelMap) {
		
		lstCats = catService.getAllCat();
		modelMap.addAttribute("lstCats", lstCats);

		return "user/mgtInfo/cat/data";
	}
	
	   //類別更新介面
		@RequestMapping("/cat/update")
		public String catUpdate(HttpServletRequest request, ModelMap modelMap,
				@RequestParam(value = "cId", required = false) String cId) {
			Cat cat = catService.getCatById(cId);
			modelMap.addAttribute("cat", cat);
			return "user/mgtInfo/cat/update";
		}
		//類別更新執行
		@RequestMapping("/cat/doUpdate")
		public String doCatUpdate(HttpServletRequest request, ModelMap modelMap,
				@Validated @ModelAttribute("userDetail") Cat cat, BindingResult bindingResult) {

			if (bindingResult.hasErrors()) {
				List<ObjectError> errors = bindingResult.getAllErrors();
				return "user/cat/update";
			}

			int aa = catService.updateByPrimaryKeySelective(cat);
			log.info("==aa=="+aa);
			return "redirect:/user/cat/index.do";		

		}
		//類別新增導引
		@RequestMapping("/cat/add")
		public String catAdd() {
			return "user/mgtInfo/cat/add";
		}
		//類別新增執行
		@RequestMapping("/cat/doAdd")
		public String doCatAdd(HttpServletRequest request, ModelMap modelMap,
				@Validated @ModelAttribute("catDetail") Cat cat, BindingResult bindingResult) {
			log.info("==aa=="+cat.getcId());
			boolean isCatIdExist = catService.findCatByCatId(cat.getcId());
			if (bindingResult.hasErrors()) {
				List<ObjectError> errors = bindingResult.getAllErrors();
			} else if (isCatIdExist) {
				// 向BindingResult添加類別已存在的校驗錯誤
				bindingResult.rejectValue("cId", "該類別已存在", "該類別已存在");
			} else {
				catService.addCat(cat);
				return "redirect:/user/cat/index.do";
			}

			modelMap.addAttribute("cat", cat);

			return "user/mgtInfo/cat/add";
		}
		//類別刪除
		@RequestMapping("/cat/del")
		public String catDel(HttpServletRequest request, ModelMap modelMap,
				@RequestParam(value = "cId", required = false) String cId) {
			System.out.println("id=" + cId);
			catService.deleteByPrimaryKey(cId);
			return "redirect:/user/cat/index.do";
		}
		



}
