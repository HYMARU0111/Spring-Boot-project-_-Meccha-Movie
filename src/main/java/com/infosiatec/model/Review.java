package com.infosiatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Review extends TimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob
	private String content;

	private double rating;

	
//	public void setUser(User user) {
//		if (this.user != null) {
//			this.user.getReviews().remove(this);
//		}
//		this.user = user;
//		user.getReviews().add(this);
//	}

	public Review() {

	}

	@Builder
	public Review(String title, String content, double rating) {
		this.title = title;
		this.content = content;
		this.rating = rating;
	}
}
