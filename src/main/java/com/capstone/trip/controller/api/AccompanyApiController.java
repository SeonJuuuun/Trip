package com.capstone.trip.controller.api;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.service.AccompanyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AccompanyApiController {

	private final AccompanyService accompanyService;

	@PostMapping("/applyToAccompany/{boardId}")
	public void save(@PathVariable("boardId") Long boardId,
		@RequestBody Map<String, String> data,
		@AuthenticationPrincipal PrincipalDetail principalDetail) {
		String username = data.get("username");
		String nickname = data.get("nickname");
		accompanyService.accompanySave(boardId, username, nickname, principalDetail.getUser());
	}

}