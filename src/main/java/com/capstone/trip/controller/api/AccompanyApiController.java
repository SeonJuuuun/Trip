package com.capstone.trip.controller.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.domain.Accompany.Accompany;
import com.capstone.trip.service.AccompanyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AccompanyApiController {

	private final AccompanyService accompanyService;

	@PostMapping("/board/{boardId}/accompany")
	public void save(@PathVariable Long boardId,
		@RequestBody Accompany accompany,
		@AuthenticationPrincipal PrincipalDetail principalDetail) {
		accompanyService.accompanySave(boardId, accompany, principalDetail.getUser());
	}

}