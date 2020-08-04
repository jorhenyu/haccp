package com.jorhen.domain;

public class Ha {
    private String haId;

    private String planId;

    private String procStep;

    private String pHa;
    
    private String haDesc;

	private String issafe;

    private String reason;

    private String pMeas;

    private String rder;

    private String rDate;

    private String mder;

    private String mDate;
    
    private Plan plan;
    
    public String getHaDesc() {
		return haDesc;
	}

	public void setHaDesc(String haDesc) {
		this.haDesc = haDesc;
	}

    public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
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

    public String getProcStep() {
        return procStep;
    }

    public void setProcStep(String procStep) {
        this.procStep = procStep == null ? null : procStep.trim();
    }

    public String getpHa() {
        return pHa;
    }

    public void setpHa(String pHa) {
        this.pHa = pHa == null ? null : pHa.trim();
    }

    public String getIssafe() {
        return issafe;
    }

    public void setIssafe(String issafe) {
        this.issafe = issafe == null ? null : issafe.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getpMeas() {
        return pMeas;
    }

    public void setpMeas(String pMeas) {
        this.pMeas = pMeas == null ? null : pMeas.trim();
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