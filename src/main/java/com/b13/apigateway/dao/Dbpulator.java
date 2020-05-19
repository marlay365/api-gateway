package com.b13.apigateway.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.b13.apigateway.dto.UserInfo;

public class Dbpulator implements CommandLineRunner {

	@Autowired UserRepository rep;
	@Autowired BCryptPasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		UserInfo user = new UserInfo();
		user.setPassword("hello10");
		user.setPassword(encoder.encode(user.getPassword()));
		user.setUsername("marc");
		rep.save(user);
	}

}
