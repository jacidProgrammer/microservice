package com.app.authentication.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ICheckLoginAttempts {
	public void increaseLoginAttempts(String username) throws UsernameNotFoundException;
	public void resetLoginAttempts(String username) throws UsernameNotFoundException;
}
