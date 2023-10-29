package com.capstone.trip.domain.board;

import java.util.List;

import com.capstone.trip.domain.BaseTimeEntity;
import com.capstone.trip.domain.reply.Reply;
import com.capstone.trip.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob
	private String content;

	@Column
	private int count; //조회수

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
	private String startday;
	private String lastday;
	private int pnum;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String picture;
	public void update(String title, String content, String startday, String lastday, int punm, String city, String state, String picture) {
		this.title = title;
		this.content = content;
		this.startday = startday;
		this.lastday = lastday;
		this.pnum = punm;
		this.city = city;
		this.state = state;
		this.picture = picture;
	}
	
	@OrderBy("id desc")
	@JsonIgnoreProperties({"board"})
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Reply> replyList;
}