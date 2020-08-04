package com.jorhen.domain;

public class Pc {
    private String pcId;

    private String planId;

    private String pUse;

    private String sSpot;

    private String cObj;

    private String notes;

    private String rder;

    private String rDate;

    private String mder;

    private String mDate;
    
    private Plan plan;

    public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId == null ? null : pcId.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getpUse() {
        return pUse;
    }

    public void setpUse(String pUse) {
        this.pUse = pUse == null ? null : pUse.trim();
    }

    public String getsSpot() {
        return sSpot;
    }

    public void setsSpot(String sSpot) {
        this.sSpot = sSpot == null ? null : sSpot.trim();
    }

    public String getcObj() {
        return cObj;
    }

    public void setcObj(String cObj) {
        this.cObj = cObj == null ? null : cObj.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
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