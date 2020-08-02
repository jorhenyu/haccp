package com.jorhen.domain;

public class Team {
	
	private String teamId;
	
    private String planId;

    private String mber;

    private String pos;

    private String skill;    
    
    private String duty;
    
    private String bg;

	private String rder;

    private String rDate;

    private String mder;

    private String mDate;    
    
    
    private Plan plan;
    
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}
    
    public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getMber() {
        return mber;
    }

    public void setMber(String mber) {
        this.mber = mber == null ? null : mber.trim();
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos == null ? null : pos.trim();
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
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