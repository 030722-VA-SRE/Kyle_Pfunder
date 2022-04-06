package com.revature.controllers;

import com.revature.models.User;
import com.revature.dtos.UserDTO;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.jboss.logging.MDC;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userServ;
	private AuthService authServ;
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserController(UserService user, AuthService authServ) {
		super();
		this.userServ = user;
		this.authServ = authServ;
	}


	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll(@RequestHeader(value = "Authorization", required = false) String token) {
		MDC.put("requestId", UUID.randomUUID().toString());
		authServ.verify(token);
		LOG.info("users retrieved");
		return new ResponseEntity<>(userServ.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getById(@PathVariable("userId") int userId, @RequestHeader("Authorization") String token) {
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		// add verification method
		// call getId and getUsername and compare to token

		return new ResponseEntity<>(userServ.getUserById(userId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user) {
		User newUser = userServ.createUser(user);
		return new ResponseEntity<>("User " + newUser.getUsername() + " has been created.", HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") int userId) {
		return new ResponseEntity<>(userServ.updateUser(userId, user), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> DeleteById(@PathVariable("userId") int userId) {
		userServ.deleteUser(userId);
		return new ResponseEntity<>("User deleted.", HttpStatus.OK);
	}
}