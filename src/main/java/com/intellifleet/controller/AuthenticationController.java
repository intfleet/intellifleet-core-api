package com.intellifleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.JwtRequestDTO;
import com.intellifleet.service.AuthenticationService;


@RequestMapping("/")
@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<ApiResponseEntity> createAuthenticationToken (@RequestBody JwtRequestDTO reqDTO, @RequestHeader(value = "is-entity-list-required", required=false) boolean isSessionListRequired) throws Exception {
		
		authenticate(reqDTO.getUserName(), reqDTO.getPassword());
				
		ApiResponseEntity response = authenticationService.getAuthenticationDetails(reqDTO, isSessionListRequired);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	public void authenticate(String userName, String password) { 
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new RuntimeException("USER DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new RuntimeException("WRONG CREDENTIALS", e);
		} 
	}
}
