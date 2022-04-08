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
import org.springframework.data.domain.Sort;


@Service
public class UserService {

	private Role role;
	private AuthService authServ;
	private ItemRepository itemRepo;
	private UserRepository userRepo;
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository userRepo, ItemRepository itemRepo, AuthService authServ) {
		super();
		this.userRepo = userRepo;
		this.authServ = authServ;
		this.itemRepo = itemRepo;
	}


	public UserDTO getUserById(int id) {
		User user = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
		return new UserDTO(user);
	}

	@Transactional
	public User createUser(User newUser) {
		authServ.getAccess(newUser);
		return userRepo.save(newUser);
	}

	public List<UserDTO> getAll() {
		List<User> users = userRepo.findAll(Sort.by(Sort.Direction.ASC,"userId"));
		List<UserDTO> usersDTO = users.stream()
			.map((user) -> new UserDTO(user))
			.collect(Collectors.toList());
		return usersDTO;
	}

	@Transactional
	public UserDTO updateUser(User user) {
		User update = userRepo.findById(user.getUserId()).orElseThrow(UserNotFoundException::new);
		
		if (user.getUsername() != null && !user.getUsername().equals(update.getUsername())) {
			update.setUsername(user.getUsername());
		}
		if (user.getPassword() != null && !user.getPassword().equals(update.getPassword())) {
			update.setPassword(user.getPassword());
		}
		if (user.getEmail() != null && !user.getEmail().equals(update.getEmail())) {
			update.setEmail(user.getEmail());
			
			User user2 = userRepo.save(update);
			authServ.getAccess(user2);
		}
		return new UserDTO(userRepo.save(update));
	}	

	@Transactional
	public void deleteUser(int id) throws AuthorizationException {
		getUserById(id);
		userRepo.deleteById(id);
	}
}