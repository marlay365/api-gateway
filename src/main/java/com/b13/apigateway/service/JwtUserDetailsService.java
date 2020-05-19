package com.b13.apigateway.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.b13.apigateway.dao.UserRepository;
import com.b13.apigateway.dto.UserInfo;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired UserRepository repo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInfo obj = repo.findByUsername(username);
		if (obj == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(obj.getUsername(), obj.getPassword(), Collections.emptyList());
	}

}