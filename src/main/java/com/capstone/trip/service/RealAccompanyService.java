package com.capstone.trip.service;

import com.capstone.trip.domain.accompany.RealAccompany;
import com.capstone.trip.domain.accompany.RealAccompanyRepository;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.board.BoardRepository;
import com.capstone.trip.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RealAccompanyService {

    private final RealAccompanyRepository realAccompanyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void save(Long userId, Long boardId, String username, String nickname, String title, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardId));
        RealAccompany realAccompany = new RealAccompany();
        realAccompany.save(board, user);
        realAccompanyRepository.save(realAccompany);
    }
}
