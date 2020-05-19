package com.b13.apigateway.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping()
	public void getProducts(HttpServletResponse response) throws IOException {
		response.getWriter().print("This is working");
	}
}
