package com.letscode.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.letscode.entities.Game;
import com.letscode.entities.Quizz;

public class GameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String response;

	private UserGameDTO user;

	private List<QuizzGameDTO> quizzes = new ArrayList<>();

	private int correct;
	private int wrong;
	private int score;

	public GameDTO() {
		super();
	}

	public GameDTO(String response, UserGameDTO user, int correct, int wrong, int score) {
		super();
		this.response = response;
		this.user = user;
		this.correct = correct;
		this.wrong = wrong;
		this.score = score;
	}

	public GameDTO(Game entity) {
		user = new UserGameDTO(entity.getUser());
		correct = entity.getCorrect();
		wrong = entity.getWrong();
		score = entity.getCorrect();
	}

	public GameDTO(Game entity, Set<Quizz> quizzes) {
		this(entity);
		quizzes.forEach(quizz -> this.quizzes.add(new QuizzGameDTO(quizz)));
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

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public int getWrong() {
		return wrong;
	}

	public void setWrong(int wrong) {
		this.wrong = wrong;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<QuizzGameDTO> getQuizzes() {
		return quizzes;
	}
}
