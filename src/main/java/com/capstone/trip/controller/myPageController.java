package com.capstone.trip.controller;

import com.capstone.trip.domain.review.Review;
import com.capstone.trip.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class myPageController {

	@Autowired
	private BoardService boardService;
	private ReviewService reviewService;

	@GetMapping("/user/mypage/mypost")
	public String mypost(Model model,
		@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
		@AuthenticationPrincipal PrincipalDetail principalDetail) {
		Page<Board> boards = boardService.findByUser_Id(principalDetail.getId(), pageable);
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
		int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boards", boards);
		return "layout/user/mypage/mypage-mypost";
	}

	@GetMapping("/user/mypage/myreview")
	public String myreview(Model model,
						 @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
						 @AuthenticationPrincipal PrincipalDetail principalDetail) {
		Page<Review> reviews = reviewService.findByUser_Id(principalDetail.getId(), pageable);
		int startPage = Math.max(1, reviews.getPageable().getPageNumber() - 4);
		int endPage = Math.min(reviews.getTotalPages(), reviews.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("reviews", reviews);
		return "layout/user/mypage/mypage-myreview";
	}

	// @GetMapping("/user/mypage/request")
	// public String request() { return "layout/user/mypage/mypage-request"; }

}
