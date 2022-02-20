package com.letscode.tests;

import com.letscode.dto.UserInsertDTO;
import com.letscode.entities.Game;
import com.letscode.entities.Role;
import com.letscode.entities.User;

public class Factory {

	public static User createUser() {
		User user = new User(1L, "User X", "userx@gmail.com", "123456");
		return user;
	}

	public static Role createRole() {
		Role role = new Role(1L, "Tester");
		return role;
	}

	public static Game createGame() {
		Game game = new Game(1L, createUser(), 1,1, true, 100);
		return game;
	}

	public static UserInsertDTO createUserInsertDTO() {
		UserInsertDTO user = new UserInsertDTO();
		user.setPassword("password");
		return user;
	}
}
