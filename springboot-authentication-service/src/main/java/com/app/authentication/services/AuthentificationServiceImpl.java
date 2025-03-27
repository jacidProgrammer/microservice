package com.app.authentication.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.authentication.models.TO.UserTO;
import com.app.authentication.models.entity.User;
import com.app.authentication.repository.UserRepository;

@Service
public class AuthentificationServiceImpl implements IUserTokenInfo, ICheckLoginAttempts, UserDetailsService {
	
	@Autowired
	public UserRepository userRepository;
	
	private Logger log = LoggerFactory.getLogger(AuthentificationServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if( user == null ) {
			log.error("This user doesn't exist "+username);
			throw new UsernameNotFoundException("This user doesn't exist "+username);
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnable(), true, true, true, authorities);
	}

	@Override
	public UserTO findByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if( user == null ) {
			log.error("This user doesn't exist "+username);
			throw new UsernameNotFoundException("This user doesn't exist "+username);
		}
		return new UserTO(user);
	}

	@Override
	public void increaseLoginAttempts(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if( user == null ) {
			log.error("This user doesn't exist "+username);
			throw new UsernameNotFoundException("This user doesn't exist "+username);
		} 
		int loginAttempts = user.getLoginAttempts();
		user.setLoginAttempts(loginAttempts++);
		if ( loginAttempts >= 3 ) {
			log.info("User Disabled");
			user.setEnable(false);
		}
		userRepository.save(user);
	}
	
	@Override
	public void resetLoginAttempts(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if( user == null ) {
			log.error("This user doesn't exist "+username);
			throw new UsernameNotFoundException("This user doesn't exist "+username);
		} 
		if(user.getLoginAttempts() > 0) {
			user.setLoginAttempts(0);
			userRepository.save(user);
		}
	}
}
