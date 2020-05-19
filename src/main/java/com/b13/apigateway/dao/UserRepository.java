package com.b13.apigateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.b13.apigateway.dto.UserInfo;


public interface UserRepository extends JpaRepository<UserInfo, String> {

	public UserInfo findByUsername(String username);
}
