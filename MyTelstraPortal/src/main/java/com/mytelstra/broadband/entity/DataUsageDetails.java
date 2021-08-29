package com.mytelstra.broadband.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DataUsageDetails{
	String date ;
	double dataUsed;
	public DataUsageDetails(String date, double dataUsed) {
		super();
		this.date = date;
		this.dataUsed = dataUsed;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getDataUsed() {
		return dataUsed;
	}
	public void setDataUsed(double dataUsed) {
		this.dataUsed = dataUsed;
	}
	
}