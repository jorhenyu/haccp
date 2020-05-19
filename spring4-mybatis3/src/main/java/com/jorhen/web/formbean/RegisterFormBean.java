package com.jorhen.web.formbean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/**
 * 封裝的用戶註冊表單bean，用來接收register.jsp中的表單輸入項的值
 * RegisterFormBean中的屬性與register.jsp中的表單輸入項的name一一對應
 * RegisterFormBean的職責除了負責接收register.jsp中的表單輸入項的值之外還擔任著校驗表單輸入項的值的合法性
 * @author jorhen
 *
 */
public class RegisterFormBean {

    //RegisterFormBean中的屬性與register.jsp中的表單輸入項的name一一對應
    //<input type="text" name="userName"/>
    private String userName;
    //<input type="password" name="userPwd"/>
    private String userPwd;
    //<input type="password" name="confirmPwd"/>
    private String confirmPwd;
    //<input type="text" name="email"/>
    private String email;
    //<input type="text" name="birthday"/>
    private String birthday;

    
    /**
     * 存儲校驗不通過時給使用者的錯誤提示資訊
     */
    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /*
     * validate方法負責校驗表單輸入項
     * 表單輸入項校驗規則：
     *         private String userName; 用戶名不能為空，並且要是3-8的字母 abcdABcd 
     *         private String userPwd; 密碼不能為空，並且要是3-8的數字
     *         private String confirmPwd; 兩次密碼要一致
     *         private String email; 可以為空，不為空要是一個合法的郵箱 
     *         private String birthday; 可以為空，不為空時，要是一個合法的日期

     */
    public boolean validate() {

        boolean isOk = true;

        if (this.userName == null || this.userName.trim().equals("")) {
            isOk = false;
            errors.put("userName", "用戶名不能為空！！");
        } else {
            if (!this.userName.matches("[a-zA-Z]{3,8}")) {
                isOk = false;
                errors.put("userName", "用戶名必須是3-8位元的字母！！");
            }
        }

        if (this.userPwd == null || this.userPwd.trim().equals("")) {
            isOk = false;
            errors.put("userPwd", "密碼不能為空！！");
        } else {
            if (!this.userPwd.matches("\\d{3,8}")) {
                isOk = false;
                errors.put("userPwd", "密碼必須是3-8位元的數字！！");
            }
        }

        // private String password2; 兩次密碼要一致
        if (this.confirmPwd != null) {
            if (!this.confirmPwd.equals(this.userPwd)) {
                isOk = false;
                errors.put("confirmPwd", "兩次密碼不一致！！");
            }
        }

        // private String email; 可以為空，不為空要是一個合法的郵箱
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "郵箱不是一個合法郵箱！！");
            }
        }

        // private String birthday; 可以為空，不為空時，要是一個合法的日期
        if (this.birthday != null && !this.birthday.trim().equals("")) {
            try {
                DateLocaleConverter conver = new DateLocaleConverter();
                conver.convert(this.birthday);
            } catch (Exception e) {
                isOk = false;
                errors.put("birthday", "生日必須要是一個日期！！");
            }
        }

        return isOk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
