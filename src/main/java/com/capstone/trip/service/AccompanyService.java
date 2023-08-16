package com.capstone.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.trip.domain.accompany.Accompany;
import com.capstone.trip.domain.accompany.AccompanyRepository;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.board.BoardRepository;
import com.capstone.trip.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccompanyService {

	private final AccompanyRepository accompanyRepository;
	private final BoardRepository boardRepository;

	@Transactional
	public void save(Long userId, Long boardId, String username, String nickname, String title, User user) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardId));
		Accompany accompany = new Accompany();
		accompany.save(board, user);
		accompanyRepository.save(accompany);
	}

	public List<Accompany> findByUser_Id(Long id) {
		return accompanyRepository.findByUser_Id(id);
	}

	public List<Accompany> findByBoard_Id(Long id) {
		return accompanyRepository.findByBoard_Id(id);
	}


}