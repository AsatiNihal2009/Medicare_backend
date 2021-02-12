package com.medicare.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medicare.dto.request.LoginDTO;
import com.medicare.dto.request.SignUpDTO;
import com.medicare.dto.response.JwtResponse;
import com.medicare.dto.response.MessageResponse;
import com.medicare.entity.Role;
import com.medicare.entity.User;
import com.medicare.enums.ERole;
import com.medicare.exceptions.RoleException;
import com.medicare.repository.RoleRepository;
import com.medicare.repository.UserRepository;
import com.medicare.security.JwtUtils;
import com.medicare.services.SignInService;
@Service
public class SignInServiceImpl implements SignInService {

	private AuthenticationManager authenticationManager;

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private PasswordEncoder encoder;

	private JwtUtils jwtUtils;
	
	@Autowired
	public SignInServiceImpl(UserRepository userRepository,AuthenticationManager authenticationManager, 
			RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public ResponseEntity<JwtResponse> getSignInData(LoginDTO userData) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		JwtResponse response=new JwtResponse();
		
		response.setToken(jwt);
		response.setId(userDetails.getId());
		response.setUsername(userDetails.getUsername());
		response.setRoles(roles);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> registerUser(SignUpDTO userData) {

		if (userRepository.existsByUsername(userData.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		//Create New User
		User user=new User();
		user.setUsername(userData.getUsername());
		user.setPassword(encoder.encode(userData.getPassword()));

		List<String> strRoles = userData.getRole();
		Set<Role> roles= new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RoleException("Error:"+ERole.ROLE_USER.name()+" Role is not found."));
			roles.add(userRole);
		}else {
			
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RoleException("Error:"+ERole.ROLE_ADMIN.name()+" Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RoleException("Error:"+ERole.ROLE_USER.name()+" Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	
}
