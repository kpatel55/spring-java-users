package com.appsdev.ecommapp.api.users.shared;

import java.io.Serializable;

public class UsersDto implements Serializable {
	private static final long serialVersionUID = -7117909261980418635L;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncPass() {
		return encPass;
	}

	public void setEncPass(String encPass) {
		this.encPass = encPass;
	}

	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String userId;
	private String encPass;
}
