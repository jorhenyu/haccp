package com.jorhen.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jorhen.domain.User;
import com.jorhen.service.UserServiceI;
import com.jorhen.service.impl.UserServiceImpl;

/**
 * 處理用戶登錄的servlet
 * 
 * @author jorhen
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	//  處理業務邏輯的userService
	private UserServiceI userService;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 獲取用戶填寫的登錄用戶名
		String username = request.getParameter("username");
		// 獲取使用者填寫的登錄密碼
		String password = request.getParameter("password");

		System.out.println("username==" + username);
		System.out.println("password==" + password);

		// UserServiceI service = new UserServiceImpl();
		// 用戶登錄
		//User user = userService.loginUser(username, password);
		User user = new User();
		if (user == null) {
			String message = String.format(
					"對不起，用戶名或密碼有誤！！請重新登錄！2秒後為您自動跳到登錄頁面！！<meta http-equiv='refresh' content='2;url=%s'",
					request.getContextPath() + "/LoginUIServlet");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		// 登錄成功後，就將使用者存儲到session中
		request.getSession().setAttribute("user", user);
		String message = String.format("恭喜：%s,登陸成功！本頁將在3秒後跳到首頁！！<meta http-equiv='refresh' content='3;url=%s'",
				user.getUserName(), request.getContextPath() + "/index.jsp");
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		// 在Servlet初始化時獲取Spring上下文對象(ApplicationContext)
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		// 從 ApplicationContext中獲取 userService
		userService = (UserServiceI) ac.getBean("userService");
	}
}
