package com.capstone.trip.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.capstone.trip.domain.accompany.Accompany;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.review.Review;
import com.capstone.trip.service.AccompanyService;
import com.capstone.trip.service.BoardService;
import com.capstone.trip.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final BoardService boardService;
	private final ReviewService reviewService;
	private final AccompanyService accompanyService;

	@GetMapping("/")
	public String index(Model model,
		@PageableDefault(size = 40, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
		@RequestParam(required = false, defaultValue = "") String search) {
		Page<Board> boards = boardService.findByTitleContainingOrContentContaining(search, search, pageable);
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
		int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boards", boards);
		return "index";
	}

	@GetMapping("/review/main")
	public String reviewIndex(Model model,
		@PageableDefault(size = 40, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
		@RequestParam(required = false, defaultValue = "") String search) {
		Page<Review> reviews = reviewService.findByTitleContainingOrContentContaining(search, search, pageable);
		int startPage = Math.max(1, reviews.getPageable().getPageNumber() - 4);
		int endPage = Math.min(reviews.getTotalPages(), reviews.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("reviews", reviews);
		return "/layout/review/review-main";
	}

//	@GetMapping("/user/mypage/request")
//	public String accompanyIndex(Model model, @RequestParam(value = "boardId") Long boardId) {
//		System.out.println("basdfasdfasdf" + boardId);    // 문제
//		Accompany accompany = accompanyService.findByBoard_Id(boardId);
//		model.addAttribute("accompany", accompany);
//		return "layout/user/mypage/mypage-request";
//	}

}