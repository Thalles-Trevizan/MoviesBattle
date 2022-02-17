package com.letscode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.entities.Game;


public interface GameRepository extends JpaRepository<Game, Long> {

}