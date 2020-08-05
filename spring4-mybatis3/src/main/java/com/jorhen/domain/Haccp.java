package com.jorhen.domain;

public class Haccp {
    private String haccpId;

    private String haId;

    private String planId;

    private String cLimit;

    private String mItm;

    private String mMd;

    private String mFre;

    private String mPrin;

    private String cMeas;

    private String record;

    private String confirm;

    private String rder;

    private String rDate;

    private String mder;

    private String mDate;
    
    private Plan plan;
    
    private Ha ha;

    public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Ha getHa() {
		return ha;
	}

	public void setHa(Ha ha) {
		this.ha = ha;
	}

	public String getHaccpId() {
        return haccpId;
    }

    public void setHaccpId(String haccpId) {
        this.haccpId = haccpId == null ? null : haccpId.trim();
    }

    public String getHaId() {
        return haId;
    }

    public void setHaId(String haId) {
        this.haId = haId == null ? null : haId.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getcLimit() {
        return cLimit;
    }

    public void setcLimit(String cLimit) {
        this.cLimit = cLimit == null ? null : cLimit.trim();
    }

    public String getmItm() {
        return mItm;
    }

    public void setmItm(String mItm) {
        this.mItm = mItm == null ? null : mItm.trim();
    }

    public String getmMd() {
        return mMd;
    }

    public void setmMd(String mMd) {
        this.mMd = mMd == null ? null : mMd.trim();
    }

    public String getmFre() {
        return mFre;
    }

    public void setmFre(String mFre) {
        this.mFre = mFre == null ? null : mFre.trim();
    }

    public String getmPrin() {
        return mPrin;
    }

    public void setmPrin(String mPrin) {
        this.mPrin = mPrin == null ? null : mPrin.trim();
    }

    public String getcMeas() {
        return cMeas;
    }

    public void setcMeas(String cMeas) {
        this.cMeas = cMeas == null ? null : cMeas.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm == null ? null : confirm.trim();
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