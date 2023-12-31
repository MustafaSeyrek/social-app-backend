package com.example.socialapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.entities.User;
import com.example.socialapp.responses.UserResponse;
import com.example.socialapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public User createUser(@RequestBody User newUser) {
		return userService.saveOneUser(newUser);
	}

	@GetMapping("/{userId}")
	public UserResponse getUserById(@PathVariable Long userId) {
		return new UserResponse(userService.getUserById(userId));
	}

	@PutMapping("/{userId}")
	public User updateUserById(@PathVariable Long userId, @RequestBody User newUser) {
		return userService.updateUserById(userId, newUser);
	}

	@DeleteMapping("/{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		userService.deleteById(userId);
	}
	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Long userId){
		return userService.getUserActivity(userId);
	}

}
