package com.jorhen.domain;

/**
 *车票信息类
 *@author linbingwen
 *@2015年5月13日8:30:12
 */
public class Ticket {
	private Integer ticketId;
	private String ticketAddress;
	private Integer ticketPrice;
	private Integer ticketCId;
	private Customer customer;//使用一个customer来表示顾客
 
	public Customer getCustomer() {
		return customer;
	}
 
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
 
	public Integer getTicketId() {
		return ticketId;
	}
 
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
 
	public String getTicketAddress() {
		return ticketAddress;
	}
 
	public void setTicketAddress(String ticketAddress) {
		this.ticketAddress = ticketAddress;
	}
 
	public Integer getTicketPrice() {
		return ticketPrice;
	}
 
	public void setTicketPrice(Integer ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
 
	public Integer getTicketCId() {
		return ticketCId;
	}
 
	public void setTicketCId(Integer ticketCId) {
		this.ticketCId = ticketCId;
	}
 
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", ticketAddress="
				+ ticketAddress + ", ticketPrice=" + ticketPrice
				+ ", ticketCId=" + ticketCId + "]";
	}
 
 
}

