package com.jorhen.domain;

public class Query {
    
    private String qPname; //專案名稱
    
    private String qCatId; //類別ID

    private String qMaker; //製作單位

    private String qPstatus; //檔案狀態

    private String qCwPw; //協作密碼

    private String qRder; //作者    
    
    private String rDateStart; //紀錄開始
    
    private String rDateEnd; //紀錄結束
    
    private String qtypeReg; //管制類型  
    
    int pageNum; //當前頁
    
    int pageSize; //頁面大小

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getQtypeReg() {
		return qtypeReg;
	}

	public void setQtypeReg(String qtypeReg) {
		this.qtypeReg = qtypeReg;
	}

	public String getqPname() {
		return qPname;
	}

	public void setqPname(String qPname) {
		this.qPname = qPname;
	}

	public String getqCatId() {
		return qCatId;
	}

	public void setqCatId(String qCatId) {
		this.qCatId = qCatId;
	}

	public String getqMaker() {
		return qMaker;
	}

	public void setqMaker(String qMaker) {
		this.qMaker = qMaker;
	}

	public String getqPstatus() {
		return qPstatus;
	}

	public void setqPstatus(String qPstatus) {
		this.qPstatus = qPstatus;
	}

	public String getqCwPw() {
		return qCwPw;
	}

	public void setqCwPw(String qCwPw) {
		this.qCwPw = qCwPw;
	}

	public String getqRder() {
		return qRder;
	}

	public void setqRder(String qRder) {
		this.qRder = qRder;
	}

	public String getrDateStart() {
		return rDateStart;
	}

	public void setrDateStart(String rDateStart) {
		this.rDateStart = rDateStart;
	}

	public String getrDateEnd() {
		return rDateEnd;
	}

	public void setrDateEnd(String rDateEnd) {
		this.rDateEnd = rDateEnd;
	}
    

  
}