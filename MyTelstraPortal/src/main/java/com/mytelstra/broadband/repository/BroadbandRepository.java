package com.mytelstra.broadband.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mytelstra.broadband.entity.Address;
import com.mytelstra.broadband.entity.BroadbandPlans;

@Repository
public interface BroadbandRepository extends MongoRepository<BroadbandPlans, String> {
	
	@Query(value="{'price' :{$gt: ?0} }")
	List<BroadbandPlans> UpgradePlans(int price);
}
