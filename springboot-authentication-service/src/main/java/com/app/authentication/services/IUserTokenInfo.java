package com.app.authentication.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.authentication.models.TO.UserTO;

public interface IUserTokenInfo {
	public UserTO findByUsername(String username) throws UsernameNotFoundException;
}
