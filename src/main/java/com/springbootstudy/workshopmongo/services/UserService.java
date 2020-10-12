package com.springbootstudy.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootstudy.workshopmongo.domain.User;
import com.springbootstudy.workshopmongo.dto.UserDto;
import com.springbootstudy.workshopmongo.repositories.UserRepository;
import com.springbootstudy.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		
		Optional<User> user = userRepository.findById(id);		
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));		
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}
