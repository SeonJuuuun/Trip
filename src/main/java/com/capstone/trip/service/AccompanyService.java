package com.capstone.trip.service;

import com.capstone.trip.domain.accompany.Accompany;
import com.capstone.trip.domain.accompany.AccompanyRepository;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.board.BoardRepository;
import com.capstone.trip.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateAccompanyAccept(Long accompanyId, boolean accept) {
        Accompany accompany = accompanyRepository.findById(accompanyId)
                .orElseThrow(() -> new EntityNotFoundException("해당 Accompany가 존재하지 않습니다. ID: " + accompanyId));

        // Accompany 엔티티의 accept 값을 업데이트
        accompany.update(accept);
    }

    public List<Accompany> findByUser_Id(Long id) {
        return accompanyRepository.findByUser_Id(id);
    }

    public List<Accompany> findByBoard_Id(Long id) {
        return accompanyRepository.findByBoard_Id(id);
    }

    public void deleteById(Long accompanyId) {
        accompanyRepository.deleteById(accompanyId);
    }
}
