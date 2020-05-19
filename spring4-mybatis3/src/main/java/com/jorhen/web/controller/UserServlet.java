package com.jorhen.web.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jorhen.domain.User;
import com.jorhen.service.UserServiceI;

/**
 * @author jorhen
 * @WebServlet是Servlet3.0提供的注解，目的是將一個繼承了HttpServlet類的普通java類標注為一個Servlet
 * UserServlet使用了@WebServlet標注之後，就不需要在web.xml中配置了
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	//處理業務邏輯的userService
    private UserServiceI userService;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//獲取所有的使用者資訊
        List<User> lstUsers = userService.getAllUser();
        request.setAttribute("lstUsers", lstUsers);
        System.out.println("===3==");
        
        for(User item : lstUsers){
        	System.out.println(item);
        }
        request.getRequestDispatcher("/right.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("===2==");
        this.doGet(request, response);
    }

    public void init() throws ServletException {
    	//在Servlet初始化時獲取Spring上下文對象(ApplicationContext)
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
      //從ApplicationContext中獲取userService
        System.out.println("===1==");
        userService = (UserServiceI) ac.getBean("userService");
    }
}


