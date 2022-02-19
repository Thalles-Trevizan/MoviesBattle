package com.letscode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letscode.dto.newGameDTO;
import com.letscode.entities.Game;
import com.letscode.entities.User;
import com.letscode.repositories.GameRepository;
import com.letscode.services.exceptions.UnauthorizedException;

@Service
public class GameService {
	
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private AuthService authService;
	
	
	
	@Transactional
	public newGameDTO insertNewGame() {
		User user = authService.authenticated();		
		Game entity = new Game();
		Game validateFirstGame = validateUserGame();
		if (validateFirstGame != null) {
			throw new UnauthorizedException("Usuário já possui um jogo iniciado");
		}
		
		entity.setUser(user);
		entity.setOpenGame(true);
		entity = repository.save(entity);
		return new newGameDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Game validateUserGame() {
		User user = authService.authenticated();		
		return repository.findByUser(user);
	}
	
	@Transactional
	public Game UpdateGame(Long gameId, Boolean result) {
		Game game = repository.getOne(gameId);
		
		if(result == true) {
			game.setCorrect(game.getCorrect() + 1);
			Integer newScore = game.getCorrect()*100;
			game.setScore(newScore);
		}else {
			game.setWrong(game.getWrong() + 1);
		}
		
		if(game.getWrong() >= 3L) {
			game.setOpenGame(false);
		}
		repository.save(game);
		
		return game;
	}

}
