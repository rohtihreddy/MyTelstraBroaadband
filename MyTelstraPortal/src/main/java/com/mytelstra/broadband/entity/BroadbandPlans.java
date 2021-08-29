package com.mytelstra.broadband.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="BroadbandPlans")
public class BroadbandPlans {
	private String id;
	private String plan;
	private int validity;
	private int price;
	private int data;
	private int speed;

	
	
	public BroadbandPlans() {
		super();
		
	}


	public BroadbandPlans(String id, String plan, int validity, int price, int data, int speed) {
		super();
		this.id = id;
		this.plan = plan;
		this.validity = validity;
		this.price = price;
		this.data = data;
		this.speed = speed;
	
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPlan() {
		return plan;
	}


	public void setPlan(String plan) {
		this.plan = plan;
	}


	public int getValidity() {
		return validity;
	}


	public void setValidity(int validity) {
		this.validity = validity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getData() {
		return data;
	}


	public void setData(int data) {
		this.data = data;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	@Override
	public String toString() {
		return "BroadbandPlans [id=" + id + ", plan=" + plan + ", validity=" + validity + ", price=" + price + ", data="
				+ data + ", speed=" + speed + "]";
	}

	
	
	
}
