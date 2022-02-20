package com.letscode.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.letscode.entities.User;

public class UserGameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo name é obrigatório")
	private String name;

	public UserGameDTO() {
		super();
	}

	public UserGameDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public UserGameDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}