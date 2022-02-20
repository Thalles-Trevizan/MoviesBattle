package com.letscode.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.letscode.dto.UserDTO;
import com.letscode.entities.User;
import com.letscode.repositories.UserRepository;
import com.letscode.services.exceptions.DatabaseException;
import com.letscode.services.exceptions.ForbiddenException;
import com.letscode.services.exceptions.ResourceNotFoundException;
import com.letscode.services.exceptions.UnauthorizedException;
import com.letscode.tests.Factory;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

	@InjectMocks
	private UserService service;

	@Mock
	private AuthService authservice;

	@Mock
	private UserRepository repository;

	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private long nonHimselfNeitherAdminId;
	private long emailNonExistingId;
	private PageImpl<User> page;
	private User user;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		nonHimselfNeitherAdminId = 4L;
		emailNonExistingId = 5L;
		user = Factory.createUser();
		page = new PageImpl<>(List.of(user));

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(user);

		doNothing().when(authservice).validateSelfOrAdmin(existingId);
		doNothing().when(authservice).validateSelfOrAdmin(nonExistingId);
		doNothing().when(authservice).validateSelfOrAdmin(dependentId);
		doThrow(ForbiddenException.class).when(authservice).validateSelfOrAdmin(nonHimselfNeitherAdminId);
		doThrow(UnauthorizedException.class).when(authservice).validateSelfOrAdmin(emailNonExistingId);

		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(user));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		doNothing().when(repository).deleteById(existingId);
		doNothing().when(repository).deleteById(nonHimselfNeitherAdminId);
		doNothing().when(repository).deleteById(emailNonExistingId);
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);
		Page<UserDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		verify(repository, times(1)).findAll(pageable);
	}

	@Test
	public void findByIdShouldReturnUserDTOWhenIdExistsAndAreAdminOrHimself() {

		UserDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
		verify(repository, times(1)).findById(existingId);
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});

		verify(repository, times(1)).findById(nonExistingId);
	}

	@Test
	public void findByIdShouldThrowForbiddenExceptionWhenUserIsNotAdminOrHimsel() {
		Assertions.assertThrows(ForbiddenException.class, () -> {
			service.findById(nonHimselfNeitherAdminId);
		});

		verify(repository, times(0)).findById(existingId);
	}

	@Test
	public void findByIdShouldThrowUnauthorizedExceptionWhenUserIsNotFindInDataBase() {
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			service.findById(emailNonExistingId);
		});

		verify(repository, times(0)).findById(existingId);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExistsAndAreAdminOrHimself() {

		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});

		verify(repository, times(1)).deleteById(existingId);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExistButAreAdminOrHimself() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});

		verify(repository, times(1)).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentIdAndAreAdminOrHimself() {

		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});

		verify(repository, times(1)).deleteById(dependentId);
	}

	@Test
	public void deleteShouldThrowForbiddenExceptionWhenNonHimselfNeitherAdminIdButExistingId() {

		Assertions.assertThrows(ForbiddenException.class, () -> {
			service.delete(nonHimselfNeitherAdminId);
		});

		verify(repository, times(0)).deleteById(nonHimselfNeitherAdminId);
	}

	@Test
	public void deleteShouldThrowUnauthorizedExceptionWhenUserEmailNonExistingButExistingId() {

		Assertions.assertThrows(UnauthorizedException.class, () -> {
			service.delete(emailNonExistingId);
		});

		verify(repository, times(0)).deleteById(emailNonExistingId);
	}

}
