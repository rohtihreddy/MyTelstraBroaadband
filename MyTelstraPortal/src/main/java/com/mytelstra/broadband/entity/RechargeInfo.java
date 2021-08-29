package com.mytelstra.broadband.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RechargeInfo {
	private String planID;
	private String dateOfRecharge;
	private String dateOfExpiry;
	private String referenceId;
	private String billNo;
	private String paymentMode;
	public RechargeInfo() {
		super();
		
	}
	public RechargeInfo(String planId, String dateOfRecharge, String dateOfExpiry) {
		super();
		this.planID = planId;
		this.dateOfRecharge = dateOfRecharge;
		this.dateOfExpiry = dateOfExpiry;
	}
	public String getPlanId() {
		return planID;
	}
	public void setPlanId(String planID) {
		this.planID = planID;
	}
	public String getDateOfRecharge() {
		return dateOfRecharge;
	}
	public void setDateOfRecharge(String dateOfRecharge) {
		this.dateOfRecharge = dateOfRecharge;
	}
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Override
	public String toString() {
		return "RechargeInfo [planID=" + planID + ", dateOfRecharge=" + dateOfRecharge + ", dateOfExpiry="
				+ dateOfExpiry + ", referenceId=" + referenceId + ", billNo=" + billNo + ", paymentMode=" + paymentMode
				+ "]";
	}
	

}
