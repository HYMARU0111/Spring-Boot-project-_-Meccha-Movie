package com.infosiatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosiatec.service.MovieService;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@GetMapping({"","/"})
	public String main(Model model,@PageableDefault(size = 10, sort = "id", direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("movies", movieService.映画リスト(pageable));
		return "main/main";
	}
	
	@GetMapping("/movie/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("movie", movieService.映画詳細(id));
		return "board/movieBoard";
	}
	
}
