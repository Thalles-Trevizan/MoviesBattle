package com.letscode.dto;

import java.io.Serializable;

import com.letscode.entities.Game;

public class GameFinishDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int correct;
	private int wrong;
	private int score;

	public GameFinishDTO() {
		super();
	}

	public GameFinishDTO(int correct, int wrong, int score) {
		this.correct = correct;
		this.wrong = wrong;
		this.score = score;
	}

	public GameFinishDTO(Game entity) {
		correct = entity.getCorrect();
		wrong = entity.getWrong();
		score = entity.getCorrect();
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
}
