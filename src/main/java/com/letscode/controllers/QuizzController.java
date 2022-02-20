package com.letscode.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		
	@PostMapping(value = "/answer/{quizzId}/{answerId}")
	public ResponseEntity <newGameDTO> insertAnswerGame(@PathVariable Long quizzId, @PathVariable Long answerId){
		Game userGame = gameService.validateUserGame();
		
		if(userGame == null || userGame.getOpenGame() == false) {
			throw new UnauthorizedException("Usuário não possui nenhum jogo iniciado, Favor iniciar um novo jogo!");
		}else {
		
		newGameDTO gameDto = new newGameDTO(userGame);
		
		Quizz newQuizz = service.validateAnswerAndNewQuizz(quizzId, answerId, gameDto);
		QuizzGameDTO newQuizzDTO = new QuizzGameDTO(newQuizz);
		gameDto.setQuizz(newQuizzDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newQuizz.getId()).toUri();
		return ResponseEntity.created(uri).body(gameDto);
		}
	}
}
