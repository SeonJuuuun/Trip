package com.capstone.trip.service;

import org.springframework.stereotype.Service;

import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.board.BoardRepository;
import com.capstone.trip.domain.reply.Reply;
import com.capstone.trip.domain.reply.ReplyRepository;
import com.capstone.trip.domain.user.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final BoardRepository boardRepository;

	@Transactional
	public void replySave(Long boardId, Reply reply, User user) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardId));
		reply.save(board, user);
		replyRepository.save(reply);
	}

	@Transactional
	public void replyDelete(Long replyId) {
		replyRepository.deleteById(replyId);
	}
}
