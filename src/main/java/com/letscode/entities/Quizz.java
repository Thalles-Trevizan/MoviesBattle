package com.letscode.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_quizz")
public class Quizz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	private String movie1;
	private String movie2;
	private Long answer;
	private Boolean result;

	public Quizz() {
		super();
	}

	public Quizz(Game game, String movie1, String movie2, Long answer, Boolean result) {
		this.game = game;
		this.movie1 = movie1;
		this.movie2 = movie2;
		this.answer = answer;
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovie1() {
		return movie1;
	}

	public void setMovie1(String movie1) {
		this.movie1 = movie1;
	}

	public String getMovie2() {
		return movie2;
	}

	public void setMovie2(String movie2) {
		this.movie2 = movie2;
	}

	public Long getAnswer() {
		return answer;
	}

	public void setAnswer(Long answer) {
		this.answer = answer;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
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
		Quizz other = (Quizz) obj;
		return Objects.equals(id, other.id);
	}
}
