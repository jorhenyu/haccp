package com.jorhen.web.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import com.jorhen.domain.User;
import com.jorhen.exception.UserExistException;
import com.jorhen.service.UserServiceI;
import com.jorhen.service.impl.UserServiceImpl;
import com.jorhen.util.WebUtils;
import com.jorhen.web.formbean.RegisterFormBean;
/**
 * 處理用戶註冊的Servlet
 * @author jorhen
 *
 */
public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //將用戶端提交的表單數據封裝到RegisterFormBean對象中
        RegisterFormBean formbean = WebUtils.request2Bean(request,RegisterFormBean.class);
        //校驗用戶註冊填寫的表單數據
        if (formbean.validate() == false) {//如果校驗失敗
            //將封裝了用戶填寫的表單數據的formbean物件發送回register.jsp頁面的form表單中進行顯示
            request.setAttribute("formbean", formbean);
            //校驗失敗就說明是用戶填寫的表單數據有問題，那麼就跳轉回register.jsp
            request.getRequestDispatcher("/main/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        /*
        try {
            // 註冊字串到日期的轉換器
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.copyProperties(user, formbean);//把表單的數據填充到javabean中
            user.setUserId(WebUtils.makeId());//設置使用者的Id屬性
            UserServiceI service = new UserServiceImpl();
            //調用service層提供的註冊使用者服務實現使用者註冊
            //service.registerUser(user);
            String message = String.format(
                    "註冊成功！！3秒後為您自動跳到登錄頁面！！<meta http-equiv='refresh' content='3;url=%s'/>", 
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            request.getRequestDispatcher("/message.jsp").forward(request,response);

        } catch (UserExistException e) {
            formbean.getErrors().put("userName", "註冊用戶已存在！！");
            request.setAttribute("formbean", formbean);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // 在後臺記錄異常
            request.setAttribute("message", "對不起，註冊失敗！！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
        */
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    

}
