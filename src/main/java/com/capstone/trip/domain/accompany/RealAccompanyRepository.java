package com.capstone.trip.domain.accompany;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealAccompanyRepository extends JpaRepository<RealAccompany, Long> {

    List<RealAccompany> findByBoardId(Long boardId);

    List<RealAccompany> findByUserId(Long userId);
}
