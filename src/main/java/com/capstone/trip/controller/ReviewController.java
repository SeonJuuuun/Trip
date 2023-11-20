package com.capstone.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.trip.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@GetMapping("/review/save")
	public String save() {
		return "layout/review/review-save";
	}

	@GetMapping("/review/{id}/update")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("review", reviewService.detail(id));
		return "layout/review/review-update";
	}

	@GetMapping("/review/star")
	public String star() { return "layout/review/review-star";}

	/**
	 * 글상세 페이지
	 */
	@GetMapping("/review/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("review", reviewService.detail(id));
		reviewService.updateCount(id);
		return "layout/review/review-detail";
	}
}
