package com.spring.ecommerce;



import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")

public class PostController 
{
	@GetMapping(value="/")
	public void redirect(HttpServletResponse res) throws IOException {
		//res.sendRedirect("/swagger-ui.html");
		res.sendRedirect("/swagger-ui.html");
		
	}
}
