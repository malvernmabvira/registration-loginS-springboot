package com.luve.signup.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luve.signup.model.Roles;
import com.luve.signup.model.Users;
import com.luve.signup.repo.UsersRepo;
import com.luve.signup.web.dto.UsersRegistrationDto;

@Service
public class UsersServiceImp implements UsersService {
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	
	
	private UsersRepo repo;

	public UsersServiceImp(UsersRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Users save(UsersRegistrationDto regDto) {
		Users user = new Users(regDto.getFirstName(), regDto.getLastName(), regDto.getEmail(), 
				passEncoder.encode(regDto.getPassword()), Arrays.asList(new Roles("ROLE_USER")));
		return repo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
