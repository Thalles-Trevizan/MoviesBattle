package com.letscode.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.letscode.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GameControllerIT {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenUtil tokenUtil;
	
	private String playerUsername;
	private String playerPassword;
	private String adminUsername;
	private String adminPassword;
	
	@BeforeEach
	void setUp() throws Exception {
		playerUsername = "maria@gmail.com";
		playerPassword = "123456";
		adminUsername = "thalles@gmail.com";
		adminPassword = "123456";
	}
	
	@Test
	public void insertNewGameShouldThrowUnauthorizedExceptionWhenNoTokenGiven() throws Exception {

		ResultActions result =
				mockMvc.perform(post("/game/start")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}	
	
	@Test
	public void insertNewGameShouldReturnGameWhenPlayerAuthenticated() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, playerUsername, playerPassword);
		
		ResultActions result =
				mockMvc.perform(post("/game/start")
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.response").isNotEmpty());
		result.andExpect(jsonPath("$.user").isNotEmpty());
		result.andExpect(jsonPath("$.quizz").isNotEmpty());
		result.andExpect(jsonPath("$.openGame").isNotEmpty());
	}
	
	@Test
	public void insertNewGameShouldReturnGameWhenAdminAuthenticated() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
		
		ResultActions result =
				mockMvc.perform(post("/game/start")
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.response").isNotEmpty());
		result.andExpect(jsonPath("$.user").isNotEmpty());
		result.andExpect(jsonPath("$.quizz").isNotEmpty());
		result.andExpect(jsonPath("$.openGame").isNotEmpty());
	}
	
	@Test
	public void finishShouldThrowUnauthorizedExceptionWhenNoTokenGiven() throws Exception {

		ResultActions result =
				mockMvc.perform(post("/game/finish")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}	
	
	@Test
	public void finishShouldReturnGameOverWhenPlayerAuthenticatedAndHasAGame() throws Exception {
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, playerUsername, playerPassword);
		
		
				mockMvc.perform(post("/game/start")
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));
		
		ResultActions result =
				mockMvc.perform(post("/game/finish")
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isAccepted());
		result.andExpect(jsonPath("$.response").isNotEmpty());
		result.andExpect(jsonPath("$.user").isNotEmpty());
		result.andExpect(jsonPath("$.game").isNotEmpty());
	}
	
	@Test
	public void finishShouldThrowUnauthorizedExceptionWhenPlayerAuthenticatedButHaventAStartedGame() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);

		ResultActions result =
				mockMvc.perform(post("/game/finish")
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}	

}
