package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.sevice.TestService;
import com.google.gson.Gson;

@Controller
public class TestController {

	@Autowired
	TestService service;

	@GetMapping("/welcome")
	public String welcome(Model model) {
		System.out.println("WELCOME");
		return "/welcome";
	}

	@GetMapping("/welcome2")
	public String welcome2(Model model) {
		System.out.println("WELCOME2");
		return "/welcome2";
	}
	// @RequestMapping(value="/welcome")
	// public ModelAndView nodeFind(CommandMap commandMap) throws Exception{
	// ModelAndView mv = new ModelAndView("node_find");
	// mv.addObject("nodeid",commandMap.get("nodeid"));
	// mv.addObject("nodename", commandMap.get("nodename"));
	// mv.addObject("lat", commandMap.get("lat") );
	// }

	@PostMapping("/getData.json")
	@ResponseBody
	public String getData() {
		System.out.println("getData");
		ArrayList<HashMap<String, String>> list = service.getData();
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	@PostMapping("/getIP.json")
	@ResponseBody
	public String getIP() {
		System.out.println("getIP");
		ArrayList<HashMap<String, String>> IPlist = service.getIP();
		Gson gson1 = new Gson();
		return gson1.toJson(IPlist);
	}
}