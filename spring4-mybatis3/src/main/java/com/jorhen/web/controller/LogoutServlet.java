package com.jorhen.web.controller;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //移除存儲在session中的user物件，實現登出功能
        request.getSession().removeAttribute("user");
        //由於字串中包含有單引號，在這種情況下使用MessageFormat.format方法拼接字串時就會有問題
        //MessageFormat.format方法只是把字串中的單引號去掉，不會將內容填充到指定的預留位置中
        String tempStr1 = MessageFormat.format(
                "註銷成功！！3秒後為您自動跳到登錄頁面！！<meta http-equiv='refresh' content='3;url={0}'/>", 
                request.getContextPath()+"/servlet/LoginUIServlet");
        System.out.println(tempStr1);//輸出結果：註銷成功！！3秒後為您自動跳到登錄頁面！！<meta http-equiv=refresh content=3;url={0}/>
        System.out.println("---------------------------------------------------------");
        /**
         * 要想解決"如果要拼接的字串包含有單引號，那麼MessageFormat.format方法就只是把字串中的單引號去掉，不會將內容填充到指定的預留位置中"這個問題，
         * 那麼可以需要使用單引號引起來的字串中使用2個單引號引起來，例如："<meta http-equiv=''refresh'' content=''3;url={0}''/>"
         * 這樣MessageFormat.format("<meta http-equiv=''refresh'' content=''3;url={0}''/>","index.jsp")就可以正常返回
         * <meta http-equiv=''refresh'' content=''3;url=index.jsp'/>
         */
        String tempStr2 = MessageFormat.format(
                "註銷成功！！3秒後為您自動跳到登錄頁面！！<meta http-equiv=''refresh'' content=''3;url={0}''/>", 
                request.getContextPath()+"/servlet/LoginUIServlet");
        /**
            * 輸出結果：
         * 註銷成功！！3秒後為您自動跳到登錄頁面！！
         * <meta http-equiv='refresh' content='3;url=/webmvcframework/servlet/LoginUIServlet'/>
         */
        System.out.println(tempStr2);
        
        String message = String.format(
                "註銷成功！！3秒後為您自動跳到登錄頁面！！<meta http-equiv='refresh' content='3;url=%s'/>", 
                request.getContextPath()+"/servlet/LoginUIServlet");
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
