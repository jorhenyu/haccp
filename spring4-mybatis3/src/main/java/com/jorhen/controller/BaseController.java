package com.jorhen.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jorhen.domain.User;

public class BaseController {

	private Logger logger = Logger.getLogger(BaseController.class);
	protected User user;

	@ModelAttribute // 進入控制器方法之前執行方法
	public void common(HttpSession session) {
		this.user = (User) session.getAttribute("user"); // 取得session

	}

}
