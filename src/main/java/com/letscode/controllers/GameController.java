package com.letscode.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.letscode.dto.GameFinishDTO;
import com.letscode.dto.GameOverDTO;
import com.letscode.dto.QuizzGameDTO;
import com.letscode.dto.UserGameDTO;
import com.letscode.dto.newGameDTO;
import com.letscode.entities.Game;
import com.letscode.entities.Quizz;
import com.letscode.services.GameService;
import com.letscode.services.QuizzService;
import com.letscode.services.RankingService;
import com.letscode.services.exceptions.UnauthorizedException;

@RestController
@RequestMapping(value = "/game")
public class GameController {
	
	@Autowired
	private GameService service;

	@Autowired
	private QuizzService quizzService;

	@Autowired
	private RankingService rankingService;
	
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
	
	@PostMapping(value = "/finish")
	public ResponseEntity <GameOverDTO> insertGameOver(){
		
		Game userGame = service.validateUserGame();
		
		if(userGame == null) {
			throw new UnauthorizedException("Você não possui nenhum jogo aberto, inicie um novo jogo para jogar!");
		}else {
			rankingService.saveGame(userGame);
			service.finishGame(userGame);
			
			UserGameDTO userGameDTO = new UserGameDTO(userGame.getUser());
			String finish = "você acabou de terminar o jogo!";
			GameFinishDTO gameFinishDTO = new GameFinishDTO(userGame);

			GameOverDTO gameOver = new GameOverDTO(finish, userGameDTO, gameFinishDTO);

			return ResponseEntity.accepted().body(gameOver);
		}
	}
}
