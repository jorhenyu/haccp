package com.jorhen.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jorhen
 * LoginUIServlet負責為使用者輸出登陸介面
 * 當用戶訪問LoginUIServlet時，就跳轉到WEB-INF/pages目錄下的login.jsp頁面
 */
@WebServlet("/LoginUIServlet")
public class LoginUIServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}


