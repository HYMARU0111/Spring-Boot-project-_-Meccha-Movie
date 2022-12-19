package com.infosiatec.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infosiatec.model.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	boolean existsByTitle(String title);
	Page<Movie> findByTitleContaining(String SearchKeyword, Pageable pageable);

	@Query(value =
		"SELECT avg(coalesce(review.rating,0)) as avg FROM movie LEFT OUTER JOIN review ON movie.movie_id = review.movie_id WHERE movie.movie_id =:id",nativeQuery=true)
	double avg(@Param("id") int id);
	
	@Query(value =
	"SELECT count(distinct review.review_id) as reviewCnt FROM movie LEFT OUTER JOIN review ON movie.movie_id = review.movie_id WHERE movie.movie_id =:id",nativeQuery=true)
	int reviewCnt(@Param("id") int id);
}
