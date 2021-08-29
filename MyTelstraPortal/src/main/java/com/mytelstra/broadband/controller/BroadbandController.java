package com.mytelstra.broadband.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mytelstra.broadband.entity.Address;
import com.mytelstra.broadband.entity.BroadbandPlans;
import com.mytelstra.broadband.entity.CardDetails;
import com.mytelstra.broadband.entity.DataUsage;
import com.mytelstra.broadband.entity.DataUsageDetails;
import com.mytelstra.broadband.entity.RechargeInfo;
import com.mytelstra.broadband.entity.UserInfo;
import com.mytelstra.broadband.service.BroadbandServices;

@RestController
public class BroadbandController {
	@Autowired
	private BroadbandServices broadbandServices;
	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	private String home() {
		return "Broadband Services Here";
	}
	
	@RequestMapping(value="/viewPlans",method = RequestMethod.GET)
	public List<BroadbandPlans> viewPlans(){
		return broadbandServices.viewPlans();
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public List<UserInfo> users(){
		return broadbandServices.userinfo();
	}
	
	@RequestMapping(value="/userDetails/{id}", method = RequestMethod.GET)
	public UserInfo userdetailsByID(@PathVariable("id") String id){
		return broadbandServices.getUserById(id);
	}
	
	@RequestMapping(value="/rechargeHistory/{id}", method = RequestMethod.GET)
	public List<RechargeInfo> userrechargehistoryById(@PathVariable("id") String id){
		return broadbandServices.userRechargeHistory(id);
	}
	

	@RequestMapping(value="/broadbandPlan/{id}", method = RequestMethod.GET)
	public BroadbandPlans viewPlanByID(@PathVariable("id") String id) {
		return broadbandServices.getBroadbandPlanById(id);
	}
	
	@RequestMapping(value="/currentPlan/{id}",method = RequestMethod.GET)
	public RechargeInfo getCurrentPlanById(@PathVariable("id") String id) {
		return broadbandServices.getCurrentPlan(id);
	}
	
	@RequestMapping(value="/recharge",method = RequestMethod.PUT)
	public String rechargeUserPut(@Validated @RequestBody Map<String,String> id) {
		return broadbandServices.rechargeUserById(id.get("userid"),id.get("planid"));
	}
	
	@RequestMapping(value="/viewUpgradePlans/{userid}",method = RequestMethod.GET)
	public List<BroadbandPlans> viewUpgradePlans(@PathVariable("userid") String id){
		return broadbandServices.getUpgradePlans(id);
	}
	
	@RequestMapping(value="/upgradeplan",method = RequestMethod.PUT)
	public String UpgradeUserPut(@Validated @RequestBody Map<String,String> id) {
		return broadbandServices.rechargeUserById(id.get("userid"),id.get("planid"));
	}
	
	@RequestMapping(value = "/card_details", method = RequestMethod.POST)
	public CardDetails saveCardDetails(@Valid @RequestBody CardDetails cardDetails) {
	       return broadbandServices.saveCardDetails(cardDetails);
	}
	
	@RequestMapping(value = "/payment_success/{planid}", method = RequestMethod.GET)
	public Map paymentDetails(@PathVariable("planid") String id) {
		Map<String,String> p = broadbandServices.paymentDetails(); 
		Map result= new HashMap<String,String>();
		result.putAll(p);
		BroadbandPlans broadband_plan = broadbandServices.getBroadbandPlanById(id);
		result.put("plan_Id", broadband_plan.getId());
		result.put("plan_name", broadband_plan.getPlan());
		result.put("price", broadband_plan.getPrice());
		result.put("speed", broadband_plan.getSpeed());
		result.put("data", broadband_plan.getData());
		result.put("validity", broadband_plan.getValidity());
		return result;
		
	} 
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public List<BroadbandPlans> validateAddress(@Valid @RequestBody Address address) {
		if(broadbandServices.validateAddress(address)) {
			return this.viewPlans();
		}
		else {
			System.out.println("Network not avaiable at this address");
			return Collections.EMPTY_LIST;
		}
     
	}
	

	@RequestMapping(value="/currentBill/{userId}",method = RequestMethod.GET)
	public Map<String,String> getCurrentBillDetails(@PathVariable("userId") String userId) {
		RechargeInfo rechargeInfo= broadbandServices.getCurrentPlan(userId);
		BroadbandPlans plan = broadbandServices.getBroadbandPlanById(rechargeInfo.getPlanId());
		Map result= new HashMap<String,String>();
		result.put("bill_number", UUID.randomUUID().toString());
		result.put("bill_date", new Date().toString());
		result.put("plan_name",plan.getPlan());
		result.put("due_amount", plan.getPrice());
		result.put("due_date", rechargeInfo.getDateOfExpiry());
		 return result;
	}
	
	@RequestMapping(value = "/view_datausage/{userId}", method = RequestMethod.GET)
 	public  List<DataUsageDetails> getDataUsageOfUser(@PathVariable("userId") String userId) {
		DataUsage d=broadbandServices.getDataUsageOfUser(userId);
		return d.getDataUsage();
	}

/*
	@RequestMapping(value="/rechargeHistory", method = RequestMethod.GET)
	public List<RechargeInfo> userrechargehistory(@RequestBody String id){
		return broadbandServices.userRechargeHistory(id);
	}
	
	
	@RequestMapping(value="/userDetails", method = RequestMethod.GET)
	public UserInfo userdetails(@RequestBody String id){
		return broadbandServices.getUserById(id);
	}
	
	@RequestMapping(value="/currentPlan",method = RequestMethod.GET)
	public RechargeInfo getCurrentPlan(@RequestBody String id) {
		return broadbandServices.getCurrentPlan(id);
	}
	
	@RequestMapping(value="/balance/{id}",method = RequestMethod.GET)
	public Map<String,String> getBalanceById(@PathVariable("id") String id) {
		return broadbandServices.getCurrentBalance(id);
	}
	
	@RequestMapping(value="/balance",method = RequestMethod.GET)
	public Map<String,String> getBalance(@Validated @RequestBody String id) {
		return broadbandServices.getCurrentBalance(id);
	}
	
	@RequestMapping(value="/recharge/{userid}/{planid}",method = RequestMethod.GET)
	public String rechargeUserGet(@PathVariable("userid") String userid, @PathVariable("planid") String planid) {
		return broadbandServices.rechargeUserById(userid,planid);
	}
*/	
}
