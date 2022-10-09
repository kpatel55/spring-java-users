package com.appsdev.ecommapp.api.users.controllers;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdev.ecommapp.api.users.model.CreateUser;
import com.appsdev.ecommapp.api.users.model.GetUserRes;
import com.appsdev.ecommapp.api.users.model.UsersRes;
import com.appsdev.ecommapp.api.users.service.UsersService;
import com.appsdev.ecommapp.api.users.shared.UsersDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment env;
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port");
	}
	
	@PostMapping
	public ResponseEntity<UsersRes> createUser(@Valid @RequestBody CreateUser req) {
		logger.info("Creating new user");

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UsersDto usersDto = modelMapper.map(req, UsersDto.class);
		UsersDto newUser = usersService.createUser(usersDto);
		
		UsersRes res = modelMapper.map(newUser, UsersRes.class);
		
		logger.info("Response returned from creating user: " + res);
		return new ResponseEntity<UsersRes>(res, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/{userId}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetUserRes> getUser(@PathVariable("userId") String userId) {

		logger.info("Getting user: " + userId);
		UsersDto usersDto = usersService.getUserById(userId);
		GetUserRes res = new ModelMapper().map(usersDto, GetUserRes.class);
		
		logger.info("Response returned from getting user: " + res);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
