package com.infosiatec.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

//	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//	private List<Review> reviews = new ArrayList<Review>();
//
//	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//	private List<Comment> comments = new ArrayList<Comment>();

	@Column(nullable = false, length = 30, unique = true)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(length = 30)
	private String familyname;

	@Column(length = 30)
	private String ownname;

	@Column(length = 30)
	private String familynameKana;

	@Column(length = 30)
	private String ownnameKana;

	@Column(nullable = false, length = 30, unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleType role;

	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp createDate;

	public User() {}
	
	@Builder
	public User(String username, String password, String email, RoleType role) {
		this.username=username;
		this.password = password;
		this.email = email;
	}
}
