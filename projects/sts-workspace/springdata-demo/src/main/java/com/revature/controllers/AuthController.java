package com.revature.controllers;

//import com.revature.dtos.UserDTO;
import com.revature.services.AuthService;
import com.revature.dtos.UserDTO;

import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthService authServ;
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	

	@Autowired
	public AuthController(AuthService authServ) {
		super();
		this.authServ = authServ;
	}


	@PostMapping
	public ResponseEntity<UserDTO> login(@RequestParam("username") String username, @RequestParam("password") String password) {
		UserDTO principal = authServ.login(username, password);
		
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		MDC.put("requestId", UUID.randomUUID().toString());
		LOG.debug("starting login");
		
		String token = authServ.generateToken(principal);
		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", token);
		
		LOG.debug("login successful");
		LOG.info("login successful");

		return new ResponseEntity<>(principal, headers, HttpStatus.ACCEPTED);
	}
}