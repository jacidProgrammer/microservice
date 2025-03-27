package com.app.authentication.models.TO;

import com.app.authentication.models.entity.User;

public class UserTO {
	
	private String name;

	private String email;
	
	public UserTO(User user) {
		name = user.getName();
		email= user.getEmail();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
