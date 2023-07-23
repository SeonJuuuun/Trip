package com.capstone.trip.dto.review;

import com.capstone.trip.domain.review.Review;
import com.capstone.trip.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveRequestDto {

	private String title;
	private String content;
	private int count;
	private User user;


	public Review toEntity() {
		return Review.builder()
			.title(title)
			.content(content)
			.count(0)
			.user(user)
			.build();
	}

	public void setUser(User user) {
		this.user = user;
	}



}
