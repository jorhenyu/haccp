package com.jorhen.domain;

public class Plan {
    private String pId;

    private String pName;
    
    private String cId;

    private String maker;

    private String pStatus;

    private String cwPw;

    private String rder;

    private String rDate;

    private String mder;

    private String mDate;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }
    
    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId == null ? null : cId.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus == null ? null : pStatus.trim();
    }

    public String getCwPw() {
        return cwPw;
    }

    public void setCwPw(String cwPw) {
        this.cwPw = cwPw == null ? null : cwPw.trim();
    }

    public String getRder() {
        return rder;
    }

    public void setRder(String rder) {
        this.rder = rder == null ? null : rder.trim();
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate == null ? null : rDate.trim();
    }

    public String getMder() {
        return mder;
    }

    public void setMder(String mder) {
        this.mder = mder == null ? null : mder.trim();
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate == null ? null : mDate.trim();
    }
}