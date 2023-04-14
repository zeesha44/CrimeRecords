package com.crimerecord.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimerecord.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	Optional<Users> findByEmailAndPassword(String email, String password);

}
