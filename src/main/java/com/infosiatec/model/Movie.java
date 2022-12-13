package com.infosiatec.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="movie_id", nullable = false)
	private int id;
	
	@Column(nullable = false, length=200)
	private String title;
	
	@Column(length = 500)
	private String imageUrl;
	
	@Column(length = 500)
	private String actor;
	
	@Column(length = 100)
	private String director;
	
	@Lob
	private String plot;
	
	@Column(name = "release_date")
	private LocalDate releaseDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "movie",fetch=FetchType.EAGER, cascade= CascadeType.REMOVE )
	@JsonIgnoreProperties({"movie"})
	@OrderBy("id desc")
	private List<Review> reviews;
	
	public Movie() {}
	
	@Builder
	public Movie(String title, String imageUrl, String actor, String director, String plot, LocalDate releaseDate) {
		this.title = title;
		this.imageUrl = imageUrl;
		this.actor = actor;
		this.director = director;
		this.plot = plot;
		this.releaseDate = releaseDate;
	}
}
