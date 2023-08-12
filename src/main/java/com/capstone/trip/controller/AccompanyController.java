package com.capstone.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.trip.service.AccompanyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AccompanyController {

	private final AccompanyService accompanyService;

	// @GetMapping("/user/mypage/mypost/accompany/{id}")
	// public String myDetail(@PathVariable Long id, Model model) {
	// 	model.addAttribute("accompany", accompanyService.getAccompanyById(id));
	// 	return "layout/accompany/accompany-detail";
	// }
}
