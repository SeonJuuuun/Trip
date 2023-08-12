package com.capstone.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.domain.accompany.Accompany;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.service.AccompanyService;
import com.capstone.trip.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class myPageController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private AccompanyService accompanyService;

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

	@GetMapping("/user/mypage/request")   // 내가 신청한 동행 목록이고 accompanyDB에 userId를 기준으로 리스트로 가져오면 될듯.
	public String accompanyIndex(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail ) {
		List<Accompany> accompany = accompanyService.findByUser_Id(principalDetail.getId()); // userId를 받을수가 없음
		model.addAttribute("accompany", accompany);
		return "layout/user/mypage/mypage-request";
	}
}
