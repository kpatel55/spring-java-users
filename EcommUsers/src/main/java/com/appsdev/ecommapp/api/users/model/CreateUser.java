package com.appsdev.ecommapp.api.users.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

public class CreateUser {
	
	@NotNull(message="First name can't be empty")
	@Size(min=2, message="First Name should be greater than 1 character")
	private String firstName;
	
	@NotNull(message="Last name can't be empty")
	@Size(min=2, message="Last Name should be greater than 1 character")
	private String lastName;
	
	@NotNull(message="Password can't be empty")
	@Size(min=8, max=16, message="Password should be between 8 and 16 characters")
	private String password;
	
	@NotNull(message="Email can't be empty")
	@Email
	private String email;

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
}
