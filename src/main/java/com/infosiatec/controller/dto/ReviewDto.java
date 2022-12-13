package com.infosiatec.controller.dto;


import com.infosiatec.model.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
	private String title;
	
	private String content;
	
	private double rating;
	
	public Review toEntity() {
		Review review = Review.builder()
				.title(title)
				.content(content)
				.rating(rating)
				.build();
		
		return review;
	}
	
}
