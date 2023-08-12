package com.capstone.trip.domain.accompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccompanyRepository extends JpaRepository<Accompany, Long> {

	List<Accompany> findByUser_Id(Long userId);
}