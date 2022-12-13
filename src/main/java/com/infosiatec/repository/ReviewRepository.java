package com.infosiatec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.infosiatec.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	@Query(value = "INSERT INTO review(title, content, creation_date, update_date, rating, movie_id, user_id) "
			+ "VALUES(?1, ?2, now(), now(), ?3, ?4, ?5", nativeQuery= true)
	@Modifying
	int reviewSave(String title, String content, double rating,int movieId, int userId);
}
