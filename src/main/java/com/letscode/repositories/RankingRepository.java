package com.letscode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.entities.Ranking;


public interface RankingRepository extends JpaRepository<Ranking, Long> {

}