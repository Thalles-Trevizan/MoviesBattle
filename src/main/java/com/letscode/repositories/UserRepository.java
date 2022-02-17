package com.letscode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}