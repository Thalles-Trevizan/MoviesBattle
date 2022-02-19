package com.letscode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letscode.entities.Game;
import com.letscode.entities.Ranking;
import com.letscode.repositories.RankingRepository;

@Service
public class RankingService {

	@Autowired
	private RankingRepository repository;
	
	@Transactional
	public void saveGame(Game userGame) {
		Ranking userRanking = new Ranking(userGame);
		repository.save(userRanking);
	}

}
