package com.luve.signup.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.luve.signup.model.Users;
import com.luve.signup.web.dto.UsersRegistrationDto;

public interface UsersService extends UserDetailsService {
	
	Users save(UsersRegistrationDto regDto);

}
