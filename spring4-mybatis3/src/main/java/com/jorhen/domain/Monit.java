package com.jorhen.domain;

public class Monit {
    private String monitId;

    private String haId;

    private String planId;

    private String bNum;

    private String typeReg;

    private String ucl;

    private String lcl;

    private String mVal;

    private String unit;

    private String mStat;

    private String rder;

    private String rDate;

    private String mder;

    private String mDate;

    public String getMonitId() {
        return monitId;
    }

    public void setMonitId(String monitId) {
        this.monitId = monitId == null ? null : monitId.trim();
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

    public String getbNum() {
        return bNum;
    }

    public void setbNum(String bNum) {
        this.bNum = bNum == null ? null : bNum.trim();
    }

    public String getTypeReg() {
        return typeReg;
    }

    public void setTypeReg(String typeReg) {
        this.typeReg = typeReg == null ? null : typeReg.trim();
    }

    public String getUcl() {
        return ucl;
    }

    public void setUcl(String ucl) {
        this.ucl = ucl == null ? null : ucl.trim();
    }

    public String getLcl() {
        return lcl;
    }

    public void setLcl(String lcl) {
        this.lcl = lcl == null ? null : lcl.trim();
    }

    public String getmVal() {
        return mVal;
    }

    public void setmVal(String mVal) {
        this.mVal = mVal == null ? null : mVal.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getmStat() {
        return mStat;
    }

    public void setmStat(String mStat) {
        this.mStat = mStat == null ? null : mStat.trim();
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