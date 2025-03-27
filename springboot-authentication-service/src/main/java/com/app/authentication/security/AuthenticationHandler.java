package com.app.authentication.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.app.authentication.services.ICheckLoginAttempts;

public class AuthenticationHandler implements AuthenticationEventPublisher {
	
	private Logger log = LoggerFactory.getLogger(AuthenticationHandler.class);
	
	@Autowired
	private ICheckLoginAttempts checkLoginAttempts;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		log.info("Login Succes " + authentication.getName());
		checkLoginAttempts.resetLoginAttempts(authentication.getName());
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		log.error("Login Error" + authentication.getName() + " | " + exception.getMessage());
		checkLoginAttempts.increaseLoginAttempts(authentication.getName());
	}

}
