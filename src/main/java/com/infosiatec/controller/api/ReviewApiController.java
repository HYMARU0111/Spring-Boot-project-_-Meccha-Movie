package com.infosiatec.controller.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import com.infosiatec.service.MovieService;
import com.infosiatec.service.ReviewService;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
public class ReviewApiController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private MovieService movieService;
//	
//	@PostMapping("/api/movie/{movie_id}/reviews")
//	public String reviewSave(@Valid ReviewDto reviewDto, Errors errors, @RequestBody Model model,@PathVariable int movie_id, @AuthenticationPrincipal PrincipalDetail principalDetail) {
//		if(errors.hasErrors()) {
//			model.addAttribute("reviewDto", reviewDto);
//			
//			Map<String, String> validationResult = reviewService.validateReview(errors);
//			for(String key : validationResult.keySet()) {
//				model.addAttribute(key, validationResult.get(key));
//			}
//			return "redirect:/movie/" + movie_id +"/reviews";
//		}
//		reviewService.レビュー作成(reviewDto, principalDetail.getUser(),movieService.映画詳細(movie_id)); 
//		return "redirect:/";
//	}
	
}
