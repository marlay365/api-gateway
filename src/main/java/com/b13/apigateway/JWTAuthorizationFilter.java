package com.b13.apigateway;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String reqToken=request.getHeader("Authorization");
		if(reqToken==null ||reqToken.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
				Jws<Claims> jws=Jwts.parser().setSigningKey(Keys.hmacShaKeyFor("MarcHasBUilTthisCraZyKeyYall".getBytes())).parseClaimsJws(reqToken);
				Claims claims=jws.getBody();
				String username= claims.getSubject();
				List<Map<String,String>> authorities=(List<Map<String, String>>) claims.get("authorities");
				UsernamePasswordAuthenticationToken tokrn= new UsernamePasswordAuthenticationToken(username, null, authorities.stream().map(x-> new SimpleGrantedAuthority(x.get("authority"))).collect(Collectors.toList()));
				SecurityContextHolder.getContext().setAuthentication(tokrn);
				filterChain.doFilter(request, response);
			} catch(JwtException e) {
				throw new IllegalStateException("Token cannot be trusted");
			}
	}

}
