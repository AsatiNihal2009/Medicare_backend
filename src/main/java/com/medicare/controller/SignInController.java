package com.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medicare.dto.request.LoginDTO;
import com.medicare.dto.request.SignUpDTO;
import com.medicare.services.SignInService;
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/medicare/auth")
public class SignInController {

	private SignInService signInService;
	
	@Autowired
	public SignInController(SignInService signInService) {
		this.signInService = signInService;
	}

	@PostMapping(path = "/signin",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDTO userData) {
		return signInService.getSignInData(userData);
	}
	
	@PostMapping(path = "signup",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpDTO userData) {
		return signInService.registerUser(userData);
	}
	
	
	
	
}
