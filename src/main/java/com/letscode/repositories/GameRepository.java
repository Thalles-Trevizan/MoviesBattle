package com.letscode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.entities.Game;
import com.letscode.entities.User;


public interface GameRepository extends JpaRepository<Game, Long> {
	
	Game findByUser(User user);

}