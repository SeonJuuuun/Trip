package com.capstone.trip.service;

import org.springframework.stereotype.Service;

import com.capstone.trip.domain.Accompany.Accompany;
import com.capstone.trip.domain.Accompany.AccompanyRepository;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.board.BoardRepository;
import com.capstone.trip.domain.user.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccompanyService {

	private final AccompanyRepository accompanyRepository;
	private final BoardRepository boardRepository;

	@Transactional
	public void accompanySave(Long boardId, String username, String nickname, User user) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardId));
		Accompany accompany = new Accompany();
		accompany.save(board, user);
		accompanyRepository.save(accompany);
	}

}