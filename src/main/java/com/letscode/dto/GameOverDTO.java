package com.letscode.dto;

import java.io.Serializable;

public class GameOverDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String response;

	private UserGameDTO user;

	private GameFinishDTO game;

	public GameOverDTO() {
		super();
	}

	public GameOverDTO(String response, UserGameDTO user, GameFinishDTO game) {
		this.response = response;
		this.user = user;
		this.game = game;
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

	public GameFinishDTO getGame() {
		return game;
	}

	public void setGame(GameFinishDTO game) {
		this.game = game;
	}
}
