package com.b13.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(jwtUserDetailsService);
		provider.setPasswordEncoder(encoder());
		return provider;
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		return encoder;
	}
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class)
		.authorizeRequests().anyRequest().authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}


	
	
	
	
	
}
