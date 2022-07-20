package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/welcome")
	public String welcome() {
		System.out.println("WELCOME");
		return "/welcome";
	}
}