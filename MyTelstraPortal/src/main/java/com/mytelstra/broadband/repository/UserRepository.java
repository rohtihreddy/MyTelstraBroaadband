package com.mytelstra.broadband.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mytelstra.broadband.entity.UserInfo;

@Repository
public interface UserRepository extends MongoRepository<UserInfo, String> {

}
