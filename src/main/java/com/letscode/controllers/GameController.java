package com.letscode.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.letscode.dto.QuizzGameDTO;
import com.letscode.dto.newGameDTO;
import com.letscode.entities.Quizz;
import com.letscode.services.GameService;
import com.letscode.services.QuizzService;

@RestController
@RequestMapping(value = "/game")
public class GameController {
	
	@Autowired
	private GameService service;

	@Autowired
	private QuizzService quizzService;
	
	@PostMapping(value = "/start")
	public ResponseEntity <newGameDTO> insertNewGame(){
		newGameDTO newGame = service.insertNewGame();
		
		Quizz quizz = quizzService.insertNewQuizz(newGame.getId());
		QuizzGameDTO quizzDTO = new QuizzGameDTO(quizz);
		newGame.setQuizz(quizzDTO);
		
		newGame.setResponse("Jogo Iniciado!!");
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newGame.getId()).toUri();
		return ResponseEntity.created(uri).body(newGame);
	}
	
//	@PostMapping(value = "/answer/{quizzId}")
//	public ResponseEntity <newGameDTO> insertAnswerGame(@PathVariable Long quizzId){
//
//		Game userGame = service.validateUserGame();
//		
//		if (userGame == null || userGame.getOpenGame() == false) {
//			throw new UnauthorizedException(userGame.getUser().getName() + ", você não possui um jogo aberto!");
//		}
//		
//		Quizz quizz = quizzService.validateAnswer(quizzId, userGame);
//		
//		
//		
//		
//		
//		newGameDTO newGame = service.insertNewGame();
//		
//		QuizzGameDTO quizzDTO = new QuizzGameDTO(quizz);
//		newGame.setQuizz(quizzDTO);
//		
//		newGame.setResponse("Jogo Iniciado!!");
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(newGame.getId()).toUri();
//		return ResponseEntity.created(uri).body(newGame);
//	}

}
