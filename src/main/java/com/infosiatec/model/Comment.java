package com.infosiatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private int id;
	
	@Column(length = 150)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "creation_date")
	@CreatedDate
	private String creationDate;
	
	@Column(name = "update_date")
	@LastModifiedDate
	private String updateDate;

	public Comment() {}
	
	public Comment(String content, String creationDate, String updateDate) {
		this.content = content;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}
}
