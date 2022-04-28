package com.luve.signup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luve.signup.model.Users;

public interface UsersRepo extends JpaRepository<Users, Long> {
	
	Users findByEmail(String email);

}
