package com.letscode.dto;

import java.io.Serializable;

import com.letscode.entities.Game;

public class newGameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String response;
	private UserGameDTO user;
	private QuizzGameDTO quizz;
	private Boolean openGame;

	public newGameDTO() {
		super();
	}

	public newGameDTO(Long id, String response, UserGameDTO user, Boolean openGame) {
		super();
		this.id = id;
		this.response = response;
		this.user = user;
		this.openGame = openGame;
	}

	public newGameDTO(Game entity) {
		id = entity.getId();
		user = new UserGameDTO(entity.getUser());
		openGame = entity.getOpenGame();
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public UserGameDTO getUser() {
		return user;
	}

	public void setUser(UserGameDTO user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QuizzGameDTO getQuizz() {
		return quizz;
	}

	public void setQuizz(QuizzGameDTO quizz) {
		this.quizz = quizz;
	}

	public Boolean getOpenGame() {
		return openGame;
	}

	public void setOpenGame(Boolean openGame) {
		this.openGame = openGame;
	}
}