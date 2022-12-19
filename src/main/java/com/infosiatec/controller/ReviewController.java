package com.infosiatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.infosiatec.service.MovieService;
import com.infosiatec.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	private MovieService movieService;
	@Autowired
	private ReviewService reviewService;
	

	
}
