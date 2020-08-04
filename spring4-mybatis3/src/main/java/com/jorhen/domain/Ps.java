package com.jorhen.domain;

public class Ps {
    private String psId;

    private String planId;
    
    private String cId;


	private String pName;

    private String matrlM;

    private String matrlO;

    private String fdAdd;

    private String prcsAids;

    private String matrl;

    private String pdtFt;

    private String pdtMd;

    private String pmDesc;

    private String stMd;

    private String sLife;

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

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}


    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId == null ? null : psId.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getMatrlM() {
        return matrlM;
    }

    public void setMatrlM(String matrlM) {
        this.matrlM = matrlM == null ? null : matrlM.trim();
    }

    public String getMatrlO() {
        return matrlO;
    }

    public void setMatrlO(String matrlO) {
        this.matrlO = matrlO == null ? null : matrlO.trim();
    }

    public String getFdAdd() {
        return fdAdd;
    }

    public void setFdAdd(String fdAdd) {
        this.fdAdd = fdAdd == null ? null : fdAdd.trim();
    }

    public String getPrcsAids() {
        return prcsAids;
    }

    public void setPrcsAids(String prcsAids) {
        this.prcsAids = prcsAids == null ? null : prcsAids.trim();
    }

    public String getMatrl() {
        return matrl;
    }

    public void setMatrl(String matrl) {
        this.matrl = matrl == null ? null : matrl.trim();
    }

    public String getPdtFt() {
        return pdtFt;
    }

    public void setPdtFt(String pdtFt) {
        this.pdtFt = pdtFt == null ? null : pdtFt.trim();
    }

    public String getPdtMd() {
        return pdtMd;
    }

    public void setPdtMd(String pdtMd) {
        this.pdtMd = pdtMd == null ? null : pdtMd.trim();
    }

    public String getPmDesc() {
        return pmDesc;
    }

    public void setPmDesc(String pmDesc) {
        this.pmDesc = pmDesc == null ? null : pmDesc.trim();
    }

    public String getStMd() {
        return stMd;
    }

    public void setStMd(String stMd) {
        this.stMd = stMd == null ? null : stMd.trim();
    }

    public String getsLife() {
        return sLife;
    }

    public void setsLife(String sLife) {
        this.sLife = sLife == null ? null : sLife.trim();
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