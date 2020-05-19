package com.jorhen.web.UI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author jorhen
 * 為使用者提供註冊的使用者介面的Servlet
 * RegisterUIServlet負責為使用者輸出註冊介面
 * 當用戶訪問RegisterUIServlet時，就跳轉到WEB-INF/pages目錄下的register.jsp頁面
 */
public class RegisterUIServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
