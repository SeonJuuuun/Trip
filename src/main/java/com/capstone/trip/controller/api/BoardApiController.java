package com.capstone.trip.controller.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.dto.board.BoardSaveRequestDto;
import com.capstone.trip.dto.board.BoardUpdateRequestDto;
import com.capstone.trip.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

	private final BoardService boardService;

	/**
	 * 글작성 API
	 */
	@PostMapping("/api/v1/board")
	public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto,
		@AuthenticationPrincipal PrincipalDetail principalDetail) {
		return boardService.save(boardSaveRequestDto, principalDetail.getUser());
	}

	/**
	 * 글삭제 API
	 */
	@DeleteMapping("/api/v1/board/{id}")
	public Long deleteById(@PathVariable Long id) {
		boardService.deleteById(id);
		return id;
	}

	/**
	 * 글수정 API
	 */
	@PutMapping("/api/v1/board/{id}")
	public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
		return boardService.update(id, boardUpdateRequestDto);
	}
}