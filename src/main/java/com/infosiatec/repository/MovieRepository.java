package com.infosiatec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infosiatec.model.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	boolean existsByTitle(String title);
	Page<Movie> findByTitleContaining(@Param("query") String query, Pageable pageable);
}
