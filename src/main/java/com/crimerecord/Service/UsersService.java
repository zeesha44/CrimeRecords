package com.crimerecord.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimerecord.exception.RecordNotFoundException;
import com.crimerecord.model.Criminals;
import com.crimerecord.model.Users;
import com.crimerecord.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	
	public Users registerUsers(String email, String password, String firstName, String lastName, String userType) {
		
		if(email == null || password == null) {
			return null;	
		}
		
		else {
			Users users = new Users();
			users.setEmail(email);
			users.setPassword(password);
			users.setFirstName(firstName);
			users.setLastName(lastName);
			users.setUserType(userType);
			return usersRepository.save(users);
		}	
		
	}
	
	public Users authenticate(String email, String password) {
		return usersRepository.findByEmailAndPassword(email, password).orElse(null);
		
	}

	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		return (List<Users>)usersRepository.findAll();
	}
	
	public Users getUserById(int id) throws RecordNotFoundException{
		System.out.println("getUserById");
		Optional<Users> users = usersRepository.findById(id);
		
		if(users.isPresent()) {
			return users.get();
		}
		else {
			throw new RecordNotFoundException("no user");
		}
	}
	
	public Users UpdateUser(Users user) {
		System.out.println("UpdateUser");
		Optional<Users> users = usersRepository.findById(user.getId());
		if(users.isPresent()) {
			Users newUser = users.get();
			newUser.setEmail(user.getEmail());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setUserType(user.getUserType());
			newUser.setPassword(user.getPassword());
			
			newUser = usersRepository.save(user);
			return newUser;
		}
		else {
			user = usersRepository.save(user);
			return user;
		}
		
	}
	
	public void deleteUser(int id) throws RecordNotFoundException{
		System.out.println("deleteUser");
		Optional<Users> users = usersRepository.findById(id);
		if(users.isPresent()) {
			usersRepository.deleteById(id);
		}
		else {
			throw new RecordNotFoundException("no user");
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
