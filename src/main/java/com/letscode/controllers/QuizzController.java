package com.letscode.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.letscode.dto.GetMoviePropDTO;
import com.letscode.dto.GetPageMoviesDTO;
import com.letscode.dto.QuizzGameDTO;
import com.letscode.dto.newGameDTO;
import com.letscode.entities.Game;
import com.letscode.entities.Quizz;
import com.letscode.services.GameService;
import com.letscode.services.QuizzService;
import com.letscode.services.exceptions.UnauthorizedException;

@RestController
@RequestMapping(value = "/quizz")
public class QuizzController {
	
	@Autowired
	private QuizzService service;
	
	@Autowired
	private GameService gameService;
	
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
	
	@PostMapping(value = "/answer/{quizzId}/{answerId}")
	public ResponseEntity <newGameDTO> insertAnswerGame(@PathVariable Long quizzId, @PathVariable Long answerId){
		Game userGame = gameService.validateUserGame();
		newGameDTO gameDto = new newGameDTO(userGame);
		
		if (userGame == null || userGame.getOpenGame() == false) {
			throw new UnauthorizedException(userGame.getUser().getName() + ", você não possui um jogo aberto!");
		}
		
		Quizz newQuizz = service.validateAnswerAndNewQuizz(quizzId, answerId, gameDto);
		
		
		
		
		
		
		
//		QuizzGameDTO quizzDTO = new QuizzGameDTO(quizz);
//		newGame.setQuizz(quizzDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newQuizz.getId()).toUri();
		return ResponseEntity.created(uri).body(gameDto);
	}
	

}
