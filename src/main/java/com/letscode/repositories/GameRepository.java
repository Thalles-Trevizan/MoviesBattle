package com.letscode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.letscode.entities.Game;
import com.letscode.entities.User;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	
	@Query("SELECT game FROM Game game WHERE game.openGame = :open AND game.user = :user")
	Game findByUserAndOpenGame(User user, boolean open);

}