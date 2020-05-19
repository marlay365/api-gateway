package com.b13.apigateway.dto;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Entity
@Table(name = "user-service")
public class UserInfo {

	@Id
	String username;
	String password;
}
