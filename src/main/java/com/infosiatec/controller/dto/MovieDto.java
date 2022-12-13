package com.infosiatec.controller.dto;

import java.util.List;

import com.infosiatec.model.Movie;

import lombok.Data;

@Data
public class MovieDto {
	private String lastBuildDate;
	private Integer total;
	private Integer start;
	private Integer display;
	private List<Item> items = null;
	
	@Data
	public class Item{

		private String title;
		private String imageUrl;
		private String actor;
		private String director;
		private String plot;
		private String releaseDate;
		private String userRating;
		
		public Movie toEntity() {
			Movie movie = Movie.builder()
					.title(title)
					.imageUrl(imageUrl)
					.actor(actor)
					.plot(plot)
					.build();
			return movie;
		}
	}
}
