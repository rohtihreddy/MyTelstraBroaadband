package com.mytelstra.broadband.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytelstra.broadband.entity.Address;
import com.mytelstra.broadband.entity.BroadbandPlans;
import com.mytelstra.broadband.entity.CardDetails;
import com.mytelstra.broadband.entity.DataUsage;
import com.mytelstra.broadband.entity.DataUsageDetails;
import com.mytelstra.broadband.entity.RechargeInfo;
import com.mytelstra.broadband.entity.UserInfo;
import com.mytelstra.broadband.repository.AddressRepo;
import com.mytelstra.broadband.repository.BroadbandRepository;
import com.mytelstra.broadband.repository.CardRepo;
import com.mytelstra.broadband.repository.DataUsageRepo;
import com.mytelstra.broadband.repository.UserRepository;


@Service
public class BroadbandServicesImpl implements BroadbandServices{
	
	@Autowired
	private BroadbandRepository broadbandrepo;
	
	@Autowired
	private CardRepo cardRepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private DataUsageRepo dataUsageRepo;
	

	@Override
	public List<BroadbandPlans> viewPlans() {
		
		List<BroadbandPlans> mobileplans = broadbandrepo.findAll();
		
		for(BroadbandPlans p: mobileplans)
			System.out.println(p);
		
		return mobileplans;
	}

	@Override
	public List<UserInfo> userinfo() {
		
		List<UserInfo> userinfo = userrepo.findAll();
		for(UserInfo u:userinfo)
			System.out.println(u);
		
		return userinfo;
	}

	@Override
	public CardDetails saveCardDetails(CardDetails cardDetails) {
		return cardRepo.save(cardDetails);
	}
	
	@Override
	public Map paymentDetails() {
		Map payment= new HashMap<String,String>() ;
		payment.put("Status", "Payment Succesfull");
		return payment;
		
	}
	
	@Override
	public List<RechargeInfo> userRechargeHistory(String id) {
		Optional<UserInfo> userinfo = userrepo.findById(id);
		List<RechargeInfo> rechargehistory = new ArrayList<RechargeInfo>();
		
		try {
			rechargehistory = userinfo.get().getPlanshistory();
		}catch(Exception e) {
			//if the user not found with _id=id
			System.out.println("No Such User Found");
			rechargehistory.add(0,null);
			return null;
		}
		return rechargehistory;
	}


	@Override
	public UserInfo getUserById(String id) {
		Optional<UserInfo> userinfo = userrepo.findById(id);
		if(!userinfo.isPresent())
			return new UserInfo();
		System.out.println(userinfo.get());
		return userinfo.get();
	}

	@Override
	public BroadbandPlans getBroadbandPlanById(String id) {
		Optional<BroadbandPlans> planinfo = broadbandrepo.findById(id);
		if(!planinfo.isPresent())
			return new BroadbandPlans();
		System.out.println(planinfo.get());
		return planinfo.get();
	}

	@Override
	public RechargeInfo getCurrentPlan(String id) {
		Optional<UserInfo> userinfo = userrepo.findById(id);
		if(!userinfo.isPresent() || userinfo.get().getActiveplan()==null)
			return new RechargeInfo();
		System.out.println(userinfo.get().getActiveplan());
		return userinfo.get().getActiveplan();
	}

	@Override
	public Map<String, String> getCurrentBalance(String id) {
		Optional<UserInfo> userinfo = userrepo.findById(id);
		if(!userinfo.isPresent() || userinfo.get().getActiveplan()==null)
			return new HashMap<String,String>();
		Map<String,String> balances = new HashMap<String,String>();
		balances.put("Data",Double.toString(userinfo.get().getDataremaining()));
		balances.put("Validity", userinfo.get().getActiveplan().getDateOfExpiry());
		return balances;
	}

	@Override
	public String rechargeUserById(String userid, String planid) {
		Optional<UserInfo> userinfo = userrepo.findById(userid);
		Optional<BroadbandPlans> planinfo  = broadbandrepo.findById(planid);
		if(!userinfo.isPresent())
			return "No User Found";
		if(!planinfo.isPresent())
			return "No Such Plans Exist";
		UserInfo user = userinfo.get();
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date todate = new Date();
		String today = simpleDateFormat.format(new Date());
		
		Date currentPlanExpiry = null;
		
		String Lastdate = getLastDate(today, planinfo.get().getValidity());

		
		System.out.println("today "+today);
		System.out.println("Validity "+planinfo.get().getValidity());
		
		
		RechargeInfo rinfo = new RechargeInfo();
		rinfo.setPlanId(planinfo.get().getId());
		rinfo.setBillNo(UUID.randomUUID().toString());
		rinfo.setReferenceId(UUID.randomUUID().toString());
		rinfo.setPaymentMode("debit_card");
		if(user.getActiveplan()!=null) {

			try {
				currentPlanExpiry = simpleDateFormat.parse(user.getActiveplan().getDateOfExpiry());
				
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			if(todate.compareTo(currentPlanExpiry)>0) {
				rinfo.setDateOfRecharge(today);
				rinfo.setDateOfExpiry(Lastdate);
				user.setActiveplan(rinfo);
				System.out.println("Expiry Date "+Lastdate);
			}
			else {
				rinfo.setDateOfRecharge(user.getActiveplan().getDateOfExpiry());
				rinfo.setDateOfExpiry(getLastDate(user.getActiveplan().getDateOfExpiry(),planinfo.get().getValidity()));
				System.out.println("Expiry Date "+Lastdate);
			}
		}
		else {
			rinfo.setDateOfRecharge(today);
			rinfo.setDateOfExpiry(Lastdate);
			user.setActiveplan(rinfo);
			System.out.println("Expiry Date "+Lastdate);
		}
		
		List<RechargeInfo> history = user.getPlanshistory();
		history.add(rinfo);
		user.setPlanshistory(history);
		user.setDataremaining(planinfo.get().getData());
		userrepo.save(user);
		System.out.println(user);
		
		return "Recharge Successful\n"+userrepo.findById(userid).get();
	}

	@Override
	public boolean validateAddress(@Valid Address address) {
		
		Address add= addressRepo.getAddressByPincode(address.getPincode());
		if(add!=null) {
			return true;
		}
		else {
			return false;
		}
	}

	private String getLastDate(String startDate, int validity) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(simpleDateFormat.parse(startDate));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, validity);
		
		return simpleDateFormat.format(cal.getTime());
	}

	@Override
	public List<BroadbandPlans> getUpgradePlans(String id) {

		UserInfo user=  getUserById(id);
		BroadbandPlans plan= getBroadbandPlanById(user.getActiveplan().getPlanId());
		System.out.println(plan.getPrice());
			return broadbandrepo.UpgradePlans(plan.getPrice());
	}
	
	@Override
	public DataUsage getDataUsageOfUser(String userId) {
		DataUsage d=  dataUsageRepo.getDataUsageByUserId(userId);
		return d;
	}

	

}
