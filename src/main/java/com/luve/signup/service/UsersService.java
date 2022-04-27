package com.luve.signup.service;

import com.luve.signup.model.Users;
import com.luve.signup.web.dto.UsersRegistrationDto;

public interface UsersService {
	
	Users save(UsersRegistrationDto regDto);

}
