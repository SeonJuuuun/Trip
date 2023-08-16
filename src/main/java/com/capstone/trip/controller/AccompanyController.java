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

	@GetMapping("/accompanyList/{id}")
	public String accompanyStatus(@PathVariable Long id, Model model) {
		model.addAttribute("accompany", accompanyService.findByBoard_Id(id));
		return "layout/accompany/accompany-detail";
	}
}
