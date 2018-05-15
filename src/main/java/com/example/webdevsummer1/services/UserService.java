package com.example.webdevsummer1.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer1.models.User;
import com.example.webdevsummer1.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PostMapping("/api/register")
	public User registerUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/register/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {
		Iterable<User> users = repository.findUserByUsername(username);
		Iterator<User> data = users.iterator();
		if(data.hasNext()) {
			return data.next();
		}
		return null;
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@PostMapping("/api/login")
	public List<User> login(@RequestBody User user) {
		return (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
	}
	
	
	
}