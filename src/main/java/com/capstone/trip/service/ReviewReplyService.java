package com.capstone.trip.service;

import org.springframework.stereotype.Service;

import com.capstone.trip.domain.review.Review;
import com.capstone.trip.domain.review.ReviewRepository;
import com.capstone.trip.domain.reviewReply.ReviewReply;
import com.capstone.trip.domain.reviewReply.ReviewReplyRepository;
import com.capstone.trip.domain.user.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewReplyService {

	private final ReviewReplyRepository reviewReplyRepository;
	private final ReviewRepository reviewRepository;

	@Transactional
	public void reviewReplySave(Long reviewId, ReviewReply reviewReply, User user) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new IllegalArgumentException("해당 reviewId가 없습니다. id=" + reviewId));
		reviewReply.save(review, user);
		reviewReplyRepository.save(reviewReply);
	}

	@Transactional
	public void reviewReplyDelete(Long reviewReplyId) {
		reviewReplyRepository.deleteById(reviewReplyId);
	}
}
