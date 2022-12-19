package com.infosiatec.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosiatec.model.Movie;
import com.infosiatec.model.Review;
import com.infosiatec.model.User;
import com.infosiatec.repository.MovieRepository;
import com.infosiatec.repository.ReviewRepository;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;


	@Transactional(readOnly=true)
	public Movie 映画詳細(int id) {
		
		return movieRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("映画詳細呼出の失敗：IDが見つかりませんでした。");
				});
	}
	

	@Transactional
	public void レビュー作成(User user, int movieId, Review requestReview) {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(()->{
					return new IllegalArgumentException("レビュー作成の失敗：IDが見つかりませんでした。");
				});
		requestReview.setUser(user);
		requestReview.setMovie(movie);
		
		reviewRepository.save(requestReview);
	}
	
	@Transactional
	public void レビュー修正(int reviewId, Review requestReview) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(()->{
					return new IllegalArgumentException("レビュー修正の失敗：IDが見つかりませんでした。");
				});
		review.setContent(requestReview.getContent());
		review.setTitle(requestReview.getTitle());
		review.setRating(requestReview.getRating());
		review.setUpdateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

	}
	
	@Transactional
	public void レビュー削除(int reviewId) {
		reviewRepository.deleteById(reviewId);
	}
	


	
}
