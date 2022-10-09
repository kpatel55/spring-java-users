package com.appsdev.ecommapp.api.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdev.ecommapp.api.users.data.UserEntity;
import com.appsdev.ecommapp.api.users.data.UsersRepo;
import com.appsdev.ecommapp.api.users.model.UsersRes;
import com.appsdev.ecommapp.api.users.shared.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {

	UsersRepo usersRepo;
	BCryptPasswordEncoder bcPass;

	@Autowired
	public UsersServiceImpl(UsersRepo usersRepo, BCryptPasswordEncoder bcPass) {
		this.usersRepo = usersRepo;
		this.bcPass = bcPass;
	}

	@Override
	public UsersDto createUser(UsersDto req) {
		req.setUserId(UUID.randomUUID().toString());
		req.setEncPass(bcPass.encode(req.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity userEntity = modelMapper.map(req, UserEntity.class);
		usersRepo.save(userEntity);

		UsersDto res = modelMapper.map(userEntity, UsersDto.class);
		return res;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usersRepo.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new User(userEntity.getEmail(), userEntity.getEncPass(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UsersDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = usersRepo.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new ModelMapper().map(userEntity, UsersDto.class);
	}

	@Override
	public UsersDto getUserById(String userId) {
		UserEntity userEntity = usersRepo.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException("User not found");
		
		UsersDto usersDto = new ModelMapper().map(userEntity, UsersDto.class);
		return usersDto;
	}
}
