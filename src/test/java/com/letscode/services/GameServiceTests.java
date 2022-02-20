package com.letscode.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.letscode.repositories.GameRepository;

@ExtendWith(SpringExtension.class)
public class GameServiceTests {
	
	@InjectMocks
	private GameService service;
	
	@Mock
	private GameRepository repository;
	
	//mockar para fazer os testes unitários

}
