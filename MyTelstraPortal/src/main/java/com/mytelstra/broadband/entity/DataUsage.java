package com.mytelstra.broadband.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Datausage")
public class DataUsage {
public String userId; //check public

public List<DataUsageDetails> dataUsage;

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public List<DataUsageDetails> getDataUsage() {
	return dataUsage;
}


public DataUsage() {
	super();
	// TODO Auto-generated constructor stub
}

public DataUsage(String userId, List<DataUsageDetails> dataUsage) {
	super();
	this.userId = userId;
	this.dataUsage= dataUsage;
}




}
