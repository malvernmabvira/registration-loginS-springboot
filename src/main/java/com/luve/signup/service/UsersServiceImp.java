package com.luve.signup.service;

import java.util.Arrays;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luve.signup.model.Roles;
import com.luve.signup.model.Users;
import com.luve.signup.repo.UsersRepo;
import com.luve.signup.web.dto.UsersRegistrationDto;

@Service
public class UsersServiceImp implements UsersService {
	
	private UsersRepo repo;

	public UsersServiceImp(UsersRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Users save(UsersRegistrationDto regDto) {
		Users user = new Users(regDto.getFirstName(), regDto.getLastName(), regDto.getEmail(), 
				regDto.getPassword(), Arrays.asList(new Roles("ROLE_USER")));
		return repo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
