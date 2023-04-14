package com.crimerecord.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimerecord.model.Users;
import com.crimerecord.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	
	public Users registerUsers(String email, String password) {
		
		if(email == null || password == null) {
			return null;	
		}
		
		else {
			Users users = new Users();
			users.setEmail(email);
			users.setPassword(password);
			users.setEmail(email);
			return usersRepository.save(users);
		}	
		
	}
	
	public Users authenticate(String email, String password) {
		return usersRepository.findByEmailAndPassword(email, password).orElse(null);
		
	}

}
