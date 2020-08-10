package com.jorhen.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class User {
	// 用戶ID
    private String uId;
    // 用戶名
    @Length(min = 1, max = 20, message = "用戶名長度必須位於1到20之間")
    private String uName;
    
    // 用戶密碼
    @Size(min=1,max=10,message="密碼的長度應該在1和10之間")
    private String uPw;

    // 用戶郵箱    
    @Email(message = "輸入正確的郵箱")
    private String uEmail;

    // 用戶職位
    private String uPosi;
    
    // 點數
    private String rdPot;

    public String getRdPot() {
		return rdPot;
	}

	public void setRdPot(String rdPot) {
		this.rdPot = rdPot;
	}

	public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuPw() {
        return uPw;
    }

    public void setuPw(String uPw) {
        this.uPw = uPw == null ? null : uPw.trim();
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail == null ? null : uEmail.trim();
    }

    public String getuPosi() {
        return uPosi;
    }

    public void setuPosi(String uPosi) {
        this.uPosi = uPosi == null ? null : uPosi.trim();
    }
}