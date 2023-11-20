package com.capstone.trip.service;

import com.capstone.trip.domain.accompany.Accompany;
import com.capstone.trip.domain.accompany.AccompanyRepository;
import com.capstone.trip.domain.accompany.RealAccompany;
import com.capstone.trip.domain.accompany.RealAccompanyRepository;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.board.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RealAccompanyService {

    private final RealAccompanyRepository realAccompanyRepository;
    private final AccompanyRepository accompanyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void save(Long accompanyId) {
        Accompany accompany = accompanyRepository.findById(accompanyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 accompanyId가 없습니다. id=" + accompanyId));

        RealAccompany realAccompany = new RealAccompany();
        realAccompany.save(accompany);
        realAccompanyRepository.save(realAccompany);
    }

    public List<RealAccompany> findByBoardId(Long boardId) {
        return realAccompanyRepository.findByBoardId(boardId);
    }


    @Transactional
    public List<Board> getUserAccompanyHistory(Long userId) {
        List<RealAccompany> realAccompanyList = realAccompanyRepository.findByUserId(userId);

        List<Board> userAccompanyHistory = new ArrayList<>();

        for (RealAccompany realAccompany : realAccompanyList) {
            Long boardId = realAccompany.getBoard().getId();
            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            optionalBoard.ifPresent(userAccompanyHistory::add);
        }

        return userAccompanyHistory;
    }
}
