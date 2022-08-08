package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.sevice.TestService;
import com.google.gson.Gson;

@Controller
public class TestController {

	@Autowired
	TestService service;

	@GetMapping("/welcome")
	public String welcome() {
		System.out.println("WELCOME");
		return "/welcome";
	}

	@GetMapping("/welcome2")
	public String welcome2() {
		System.out.println("WELCOME");
		return "/welcome2";
	}

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

	@PostMapping("/getLink.json")
	@ResponseBody
	public String getLink() {
		ArrayList<HashMap<String, String>> Linklist = service.getLink();
		Gson gson2 = new Gson();
		return gson2.toJson(Linklist);
	}
	// 좌표 DB 업데이트
	// , produces="application/json; charset=UTF-8"
	@PostMapping(value = "/getUpdate.json")
	@ResponseBody
	public String getUpdate(@RequestBody ArrayList<HashMap<String , String>> params) {
		System.out.println(params);
		Gson gson = new Gson();
		service.update(params);
		return gson.toJson(params);
	}
	@PostMapping(value = "/setIP.json")
	@ResponseBody
	public String setIP(@RequestBody ArrayList<HashMap<String, String>> res){
		System.out.println(res);
		Gson gson = new Gson();
		service.setIP(res);
		return gson.toJson(res);
	}
	@PostMapping(value = "/delIP.json")
	@ResponseBody
	public String delIP(@RequestBody ArrayList<HashMap<String, String>> del){
		System.out.println(del);
		Gson gson = new Gson();
		service.delIP(del);
		return gson.toJson(del);
	}
}