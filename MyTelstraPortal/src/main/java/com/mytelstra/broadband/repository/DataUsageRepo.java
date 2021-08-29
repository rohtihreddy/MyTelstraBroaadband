package com.mytelstra.broadband.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mytelstra.broadband.entity.DataUsage;



@Repository
public interface DataUsageRepo extends MongoRepository<DataUsage, Long>{
	@Query(value="{userId : ?0}")
	public DataUsage getDataUsageByUserId(String userId);

}
