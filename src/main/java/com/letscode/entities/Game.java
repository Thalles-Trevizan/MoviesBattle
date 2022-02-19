package com.letscode.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_game")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "game")
	private List<Quizz> quizzes = new ArrayList<>();

	private Integer correct;
	private Integer wrong;
	private Boolean openGame;
	private Integer score;

	public Game() {
		super();
	}

	public Game(Long id, User user, Integer correct, Integer wrong, Boolean openGame, Integer score) {
		super();
		this.id = id;
		this.user = user;
		this.correct = correct;
		this.wrong = wrong;
		this.openGame = openGame;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	public Integer getWrong() {
		return wrong;
	}

	public void setWrong(Integer wrong) {
		this.wrong = wrong;
	}

	public Boolean getOpenGame() {
		return openGame;
	}

	public void setOpenGame(Boolean openGame) {
		this.openGame = openGame;
	}

	public List<Quizz> getQuizzes() {
		return quizzes;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(id, other.id);
	}
}
