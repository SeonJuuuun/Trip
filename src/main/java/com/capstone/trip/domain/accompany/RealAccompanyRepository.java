package com.capstone.trip.domain.accompany;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealAccompanyRepository extends JpaRepository<RealAccompany, Long> {

    List<RealAccompany> findByUser_Id(Long userId);

    List<RealAccompany> findByBoard_Id(Long boardId);
}
