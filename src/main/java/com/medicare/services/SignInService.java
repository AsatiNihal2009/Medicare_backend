package com.medicare.services;

import org.springframework.http.ResponseEntity;

import com.medicare.dto.request.LoginDTO;
import com.medicare.dto.request.SignUpDTO;

public interface SignInService {

	ResponseEntity<?> getSignInData(LoginDTO userData);

	ResponseEntity<?> registerUser(SignUpDTO userData);

}
