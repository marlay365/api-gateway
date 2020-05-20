package com.b13.apigateway.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.b13.apigateway.dto.UserInfo;

@Component
public class Dbpulator implements CommandLineRunner {

	@Autowired UserRepository rep;
	@Autowired BCryptPasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		UserInfo user = new UserInfo();
		String password = "hello10";
		user.setPassword(encoder.encode(password));
		user.setUsername("marc");
		rep.save(user);
	}

}
