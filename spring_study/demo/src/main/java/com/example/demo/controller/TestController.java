package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.sevice.TestService;

@Controller
public class TestController {
	
	@Autowired
	TestService service;

	@GetMapping("/welcome")
	public String welcome(Model model) {
		System.out.println("WELCOME");
		ArrayList<HashMap<String, String>> list = service.getData();
		for(HashMap<String, String> i : list){
			System.out.println(i);
			
		}
		model.addAttribute("/welcome",list);
		return "/welcome";
	}
}