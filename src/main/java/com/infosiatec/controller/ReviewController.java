package com.infosiatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosiatec.service.MovieService;
import com.infosiatec.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	private MovieService movieService;
	@Autowired
	private ReviewService reviewService;
	

	
}
