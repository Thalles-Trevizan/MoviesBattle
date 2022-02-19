package com.letscode.dto;

import java.io.Serializable;

import com.letscode.entities.Quizz;

public class QuizzRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean result;

	public QuizzRequestDTO() {
		super();
	}

	public QuizzRequestDTO(Long id, Boolean result) {
		super();
		this.id = id;
		this.result = result;
	}
	
	public QuizzRequestDTO(Quizz entity) {
		id = entity.getId();
		result = entity.getResult();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
}
