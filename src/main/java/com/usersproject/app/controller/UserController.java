package com.usersproject.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usersproject.app.entity.User;
import com.usersproject.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		//ResponseEntity<?> requestResponse = ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
		Map<String, User> ejemplo = new HashMap<String, User>();
		ejemplo.put("mensaje: ", user);
		ResponseEntity<?> requestResponse = ResponseEntity.status(HttpStatus.CREATED).body(ejemplo);
		return(requestResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable(value = "id") Long userId) {
		ResponseEntity<?> requestResponse;
		Optional<User> oUser = userService.findById(userId);
		if(!oUser.isPresent()) {
			requestResponse = ResponseEntity.notFound().build();
		} else {
			requestResponse = ResponseEntity.ok(oUser);
		}
		return(requestResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User userDetails, @PathVariable(value = "id") Long userId) {
		ResponseEntity<?> requestResponse;
		Optional<User> oUser = userService.findById(userId);
		if(!oUser.isPresent()) {
			requestResponse = ResponseEntity.notFound().build();
		} else {
			oUser.get().setName(userDetails.getName());
			oUser.get().setSurname(userDetails.getSurname());
			oUser.get().setEmail(userDetails.getEmail());
			oUser.get().setEnabled(userDetails.getEnabled());
			requestResponse = ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
		}
		return(requestResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		ResponseEntity<?> requestResponse;
		Optional<User> oUser = userService.findById(userId);
		if(!oUser.isPresent()) {
			requestResponse = ResponseEntity.notFound().build();
		} else {
			userService.deleteById(userId);
			requestResponse = ResponseEntity.ok().build();
		}
		return(requestResponse);
	}
	
	@GetMapping
	public List<User> listUsers() {
		List<User> users = StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return(users);
	}
}