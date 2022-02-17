package com.letscode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.entities.Quizz;


public interface QuizzRepository extends JpaRepository<Quizz, Long> {

}