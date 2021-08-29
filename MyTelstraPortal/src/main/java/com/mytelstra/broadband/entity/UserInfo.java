package com.mytelstra.broadband.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="BroadbandUserInfo")
public class UserInfo {
	private String id;
	private String username;
	private List<RechargeInfo> planshistory;
	private RechargeInfo activeplan;
	private double dataremaining;
	
	public UserInfo() {
		super();
		
	}
	public UserInfo(String id, String username, List<RechargeInfo> planshistory, RechargeInfo activeplan, double dataremaining) {
		super();
		this.id = id;
		this.username = username;
		this.planshistory = planshistory;
		this.activeplan = activeplan;
		this.dataremaining = dataremaining;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<RechargeInfo> getPlanshistory() {
		return planshistory;
	}
	public void setPlanshistory(List<RechargeInfo> planshistory) {
		this.planshistory = planshistory;
	}
	public RechargeInfo getActiveplan() {
		return activeplan;
	}
	public void setActiveplan(RechargeInfo activeplan) {
		this.activeplan = activeplan;
	}
	public double getDataremaining() {
		return dataremaining;
	}
	public void setDataremaining(int dataremaining) {
		this.dataremaining = dataremaining;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", planshistory=" + planshistory + ", activeplan="
				+ activeplan + ", dataremaining=" + dataremaining ;
	}
}
