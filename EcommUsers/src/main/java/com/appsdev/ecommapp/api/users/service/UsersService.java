package com.appsdev.ecommapp.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appsdev.ecommapp.api.users.shared.UsersDto;

public interface UsersService extends UserDetailsService {
	UsersDto createUser(UsersDto req);
	UsersDto getUserDetailsByEmail(String email);
	UsersDto getUserById(String userId);
}
