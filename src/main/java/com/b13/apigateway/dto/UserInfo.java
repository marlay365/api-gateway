package com.b13.apigateway.dto;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_service")
public class UserInfo {

	@Id
	String username;
	String password;
}
