package com.capstone.trip.domain.Accompany;

import com.capstone.trip.domain.BaseTimeEntity;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accompany extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "boardId")
	private Board board;

	@Column
	private boolean accept;

	public void save(Board board, User user) {
		this.board = board;
		this.user = user;
	}

	public void update(boolean accept) {
		this.accept = accept;
	}
}
