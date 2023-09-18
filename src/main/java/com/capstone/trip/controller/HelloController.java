package com.capstone.trip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "hello Spring Boot!";
	}

	@GetMapping("/notice/main")
	public String noticeIndex(){
		return "알람 기능 입니다!";
	}
}