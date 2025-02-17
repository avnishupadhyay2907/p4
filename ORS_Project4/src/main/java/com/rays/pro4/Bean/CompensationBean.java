package com.rays.pro4.Bean;

import java.util.Date;

public class CompensationBean extends BaseBean {

	private long staffMemberId;
	private String staffMemberName;
	private String paymentAmount;
	private Date dateApplied;
	private String state;
	public long getStaffMemberId() {
		return staffMemberId;
	}
	public void setStaffMemberId(long staffMemberId) {
		this.staffMemberId = staffMemberId;
	}
	public String getStaffMemberName() {
		return staffMemberName;
	}
	public void setStaffMemberName(String staffMemberName) {
		this.staffMemberName = staffMemberName;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return paymentAmount;
	}

}
