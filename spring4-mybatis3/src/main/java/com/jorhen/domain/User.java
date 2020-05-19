package com.jorhen.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class User {
	// 用戶ID
	
    private String userId;
    // 用戶名
    @Length(min = 1, max = 20, message = "用戶名長度必須位於1到20之間")
    private String userName;
   // 用戶密碼
    @Size(min=1,max=10,message="密碼的長度應該在1和10之間")
    private String userPwd;
    
    // 用戶郵箱    
    @Email(message = "輸入正確的郵箱")
    private String userEmail;
    
    // 用戶生日    
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String userBirthday;
    
 // 用戶職位
    private String userPosition;

    

    public String getUserPosition() {
		return userPosition;
	}



	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}



	public String getUserId() {
        return userId;
    }
    


    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }
    
	public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
		return userPwd;
	}



	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}





    public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }


}