package com.letscode.tests;

import com.letscode.dto.UserDTO;
import com.letscode.dto.UserInsertDTO;
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

	public static UserDTO createUserDTO() {
		User user = createUser();
		return new UserDTO(user);
	}

	public static UserInsertDTO createUserInsertDTO() {
		UserInsertDTO user = new UserInsertDTO();
		user.setPassword("password");
		return user;
	}
}
