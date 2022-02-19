package com.letscode.dto;

import java.io.Serializable;

import com.letscode.entities.Quizz;

public class QuizzGameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String movie1;
	private String movie2;

	public QuizzGameDTO() {
		super();
	}

	public QuizzGameDTO(Long id, String movie1, String movie2) {
		super();
		this.id = id;
		this.movie1 = movie1;
		this.movie2 = movie2;
	}
	
	public QuizzGameDTO(Quizz entity) {
		id = entity.getId();
		movie1 = entity.getMovie1();
		movie2 = entity.getMovie2();
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
}
