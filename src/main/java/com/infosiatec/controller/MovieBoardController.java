package com.infosiatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieBoardController {
	
	@GetMapping("/auth/movieboard")
	public String MovieBoard() {
		return "board/movieBoard";
	}
}
