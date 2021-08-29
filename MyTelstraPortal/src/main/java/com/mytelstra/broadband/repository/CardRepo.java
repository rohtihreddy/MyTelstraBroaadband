package com.mytelstra.broadband.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mytelstra.broadband.entity.CardDetails;



@Repository
public interface CardRepo extends MongoRepository<CardDetails, Long>{

}
