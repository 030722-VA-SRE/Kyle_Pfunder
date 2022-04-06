package com.revature.services;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.dtos.UserDTO;
import com.revature.repositories.ItemRepository;
import com.revature.repositories.UserRepository;
import com.revature.exceptions.AuthorizationException;
import com.revature.exceptions.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserService {

	private Role role;
	private UserRepository userRepo;
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository userRepo, ItemRepository itemRepo) {
		super();
		this.userRepo = userRepo;
	}


	public UserDTO getUserById(int id) {
		User user = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
		return new UserDTO(user);
	}

	@Transactional
	public User createUser(User newUser) {
		String[] splitEmail = newUser.getEmail().split("@");
		
		if (splitEmail[1].equals("revature.net")) {
			newUser.setRole(role = Role.ADMIN);
		}
		else {
			newUser.setRole(role = Role.USER);
		}
		return userRepo.save(newUser);
	}

	public List<UserDTO> getAll() {
		List<User> users = userRepo.findAll();
		List<UserDTO> usersDTO = users.stream()
			.map((user) -> new UserDTO(user))
			.collect(Collectors.toList());
		return usersDTO;
	}

	@Transactional
	public User updateUser(int id, User user) {
		return userRepo.save(user);
	}

	@Transactional
	public void deleteUser(int id) throws AuthorizationException {
		getUserById(id);
		userRepo.deleteById(id);
	}
}