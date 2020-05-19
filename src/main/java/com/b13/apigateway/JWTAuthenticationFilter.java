package com.b13.apigateway;

import com.b13.apigateway.dto.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
	private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserInfo creds = new ObjectMapper().readValue(req.getInputStream(), UserInfo.class);
            Authentication auth= new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), Collections.emptyList());
			Authentication auth1= authenticationManager.authenticate(auth);
			return auth1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token=Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername()).claim("authorities", authResult.getAuthorities())
		.setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
		.signWith(Keys.hmacShaKeyFor("MarcHasBUilTthisCraZyKeyYall".getBytes()))
		.compact();
		response.addHeader("Authorization", token);
	}
}
