package com.mytelstra.broadband.entity;

import javax.validation.constraints.Digits;



import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carddetails")
public class CardDetails {

	private long cardNo;
	private String expDate;
	
	@Digits(integer=3,fraction=0)
	private int CVV;
	
	public CardDetails(long cardNo, String expDate, int cVV) {
		super();
		this.cardNo = cardNo;
		this.expDate = expDate;
		CVV = cVV;
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public int getCVV() {
		return CVV;
	}
	public void setCVV(int cVV) {
		CVV = cVV;
	}
	@Override
	public String toString() {
		return "CardDetails [cardNo=" + cardNo + ", expDate=" + expDate + ", CVV=" + CVV + "]";
	}
	
	
}
