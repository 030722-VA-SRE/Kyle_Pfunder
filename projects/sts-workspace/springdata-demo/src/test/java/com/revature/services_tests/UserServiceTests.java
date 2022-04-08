package com.revature.services_tests;

import com.revature.repositories.ItemRepository;
import com.revature.services.AuthService;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.models.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;


public class UserServiceTests {

	static List<UserDTO> usersDTO = new ArrayList<>(); 	
	static List<User> users = new ArrayList<>();
	static ItemRepository itemRepo;
	static UserRepository userRepo;
	static AuthService authServ;
	static UserService userServ;
	static UserDTO userDTO;
	static Role role;
	static User user;
	
	
	@BeforeAll
	public static void test() {
		userRepo = mock(UserRepository.class);
		user = new User(1, "Kyle", "Password1", "Kyle@gmail.com");
		userDTO = new UserDTO(user);
		users.add(user);
		usersDTO.add(userDTO);
		userServ = new UserService(userRepo, itemRepo, authServ);
	}
	
	@Test
	public void getUsersTest() {
		when(userRepo.findAll()).thenReturn(users);
		assertEquals(userDTO, userServ.getAll());
	}
	
	@Test
	public void testGetUserById() {
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		assertEquals(userDTO, userServ.getUserById(1));
	}
	
	
	@Test
	public void testCreateUser() {
		when(userRepo.save(any(User.class))).thenReturn(user);
		assertEquals(user, userServ.createUser(user));
	}
	
	@Test
	public void testUpdateUser() {
		User user2 = new User(2, "Kyle", "Password2", "kyle705@revature.net");
		User user3 = new User(3, "test3", "Password3", "kyle@hotmail.com");
		
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user3));
		when(userRepo.save(any(User.class))).thenReturn(user2);
		
		assertEquals(user2, userServ.updateUser(user2));
	}
	
	@Test
	public void testDeleteUser() {
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		doNothing().when(userRepo).delete(any(User.class));
		userServ.deleteUser(1);
		verify(userRepo, times(1)).delete(user);
	}	
}