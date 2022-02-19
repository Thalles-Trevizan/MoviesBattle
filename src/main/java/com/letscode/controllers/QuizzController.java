package com.letscode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.dto.GetPageMoviesDTO;
import com.letscode.services.QuizzService;

@RestController
@RequestMapping(value = "/quizz")
public class QuizzController {
	
	@Autowired
	private QuizzService service;
	
//	@GetMapping(value = "/{id}")
//	public GetMoviePropDTO findMovieProperties(PathVariable String id) {
//		GetMoviePropDTO post = service.findMovieProperties(id);
//		return post;
//	}
	
	@GetMapping(value = "/movies")
	public GetPageMoviesDTO findMovies(Pageable pageable) {
		Integer pageId = 1;
		GetPageMoviesDTO page = service.findMovies(pageId, pageable);
		return page;
	}
	

}
