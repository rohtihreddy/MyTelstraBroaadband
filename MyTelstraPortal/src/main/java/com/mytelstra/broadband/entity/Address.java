package com.mytelstra.broadband.entity;



import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address {

	private String street;
	private String city;
	
	private String pincode;
	
	private String state;

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getPincode() {
		return pincode;
	}

	public String getState() {
		return state;
	}
	
}
