package com.letscode.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.letscode.repositories.RankingRepository;

@ExtendWith(SpringExtension.class)
public class RankingServiceTests {
	
	@InjectMocks
	private RankingService service;
	
	@Mock
	private RankingRepository repository;
	
	//mockar para fazer os testes unitários

}
