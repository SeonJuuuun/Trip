package com.capstone.trip.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.trip.domain.review.Review;
import com.capstone.trip.domain.review.ReviewRepository;
import com.capstone.trip.domain.user.User;
import com.capstone.trip.dto.review.ReviewSaveRequestDto;
import com.capstone.trip.dto.review.ReviewUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

	@Transactional
	public Long save(ReviewSaveRequestDto reviewSaveRequestDto, User user) {
		reviewSaveRequestDto.setUser(user);
		return reviewRepository.save(reviewSaveRequestDto.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public Page<Review> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
		return reviewRepository.findByTitleContainingOrContentContaining(title, content, pageable);
	}

	@Transactional(readOnly = true)
	public Review detail(Long id) {
		return reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
	}

	@Transactional
	public void deleteById(Long id) {
		reviewRepository.deleteById(id);
	}

	@Transactional
	public Long update(Long id, ReviewUpdateRequestDto reviewUpdateRequestDto) {
		Review review = reviewRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
		review.update(reviewUpdateRequestDto.getTitle(), reviewUpdateRequestDto.getContent());
		return id;
	}

	@Transactional
	public int updateCount(Long id) {
		return reviewRepository.updateCount(id);
	}

}
