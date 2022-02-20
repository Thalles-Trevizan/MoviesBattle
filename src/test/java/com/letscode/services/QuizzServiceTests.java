package com.letscode.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.letscode.repositories.QuizzRepository;

@ExtendWith(SpringExtension.class)
public class QuizzServiceTests {
	
	@InjectMocks
	private QuizzService service;
		
	@Mock
	private QuizzRepository repository;
	
	//mockar para fazer os testes unitários
	
	
}
