package com.letscode.dto;

import java.io.Serializable;

import com.letscode.entities.Game;

public class GameOverDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String response;

	private UserGameDTO user;

	private Game game;

	public GameOverDTO() {
		super();
	}

	public GameOverDTO(String response, UserGameDTO user, Game game) {
		super();
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
