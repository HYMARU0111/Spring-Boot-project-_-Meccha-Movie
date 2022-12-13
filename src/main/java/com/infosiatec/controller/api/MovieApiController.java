package com.infosiatec.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosiatec.config.auth.PrincipalDetail;
import com.infosiatec.controller.dto.ResponseDto;
import com.infosiatec.model.Review;
import com.infosiatec.service.MovieService;

@RestController
public class MovieApiController {
	@Autowired
	private MovieService movieService;

	
	@PostMapping("/api/movie/{movieId}/reviews")
	public ResponseDto<Integer> reviewSave(@PathVariable int movieId,@RequestBody Review review, @AuthenticationPrincipal PrincipalDetail principal){

		movieService.レビュー作成(principal.getUser(), movieId, review);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/movie/{movieId}/reviews/{reviewId}")
	public ResponseDto<Integer> reviewUpdate(@PathVariable int reviewId, @RequestBody Review review){
		
		movieService.レビュー修正(reviewId, review);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/movie/{movieId}/reviews/{reviewId}")
	public ResponseDto<Integer> reviewDelete(@PathVariable int reviewId){

		movieService.レビュー削除(reviewId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
