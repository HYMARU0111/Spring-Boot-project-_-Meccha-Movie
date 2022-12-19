package com.infosiatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.infosiatec.repository.MovieRepository;
import com.infosiatec.service.MovieService;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepository movieRepository;
	
	@GetMapping("/")
	public String main(Model model,@PageableDefault(size = 10, sort = "id", direction=Sort.Direction.DESC) Pageable pageable
				, @RequestParam(value="searchKeyword", required = false) String searchKeword) {

		
		if(searchKeword == null) {
			model.addAttribute("movies", movieRepository.findAll(pageable));
		}else {
			model.addAttribute("movies", movieRepository.findByTitleContaining(searchKeword,pageable));

		}
		
		return "main/main";
	}
	
	@GetMapping("/movie/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("avg", movieRepository.avg(id));
		model.addAttribute("reviewCnt", movieRepository.reviewCnt(id));
		model.addAttribute("movie", movieService.映画詳細(id));
		return "board/movieBoard";
	}
	
}
