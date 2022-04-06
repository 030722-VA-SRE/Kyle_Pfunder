package com.revature.services;

import com.revature.models.User;

import com.revature.repositories.UserRepository;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;

import org.slf4j.Logger;
import org.jboss.logging.MDC;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AuthService {

	private UserRepository userRep;
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	public AuthService(UserRepository userRep) {
		super();
		this.userRep = userRep;
	}


	public UserDTO login(String username, String password) {
		User principal = userRep.getUserByUsername(username);

		if (principal == null || !principal.getPassword().equals(password)) {
			throw new AuthenticationException("Attempted to login with username: " + username);
		}

		LOG.info("User " + principal.getUsername() + "'s credentials validated.");
		return new UserDTO(principal);
	}


	public String generateToken(UserDTO principal) {
		User user = userRep.getById(principal.getUserId());
		return user.getUserId()+":"+user.getUsername().toString();
	}


	public void verify(String token) {
		if (token == null) {
			throw new AuthorizationException("null token");
		}

		String[] splitToken = token.split(":");

		User user = userRep.getById(Integer.valueOf(splitToken[0]));

	if (user == null || !user.getUsername().equals(splitToken[1])) {
		throw new AuthorizationException("Unable to verify token: " + splitToken[0] + ", " + splitToken[1]);
	}

	LOG.info("token verification: success");
	MDC.put("userId", user.getUserId());
	}
}