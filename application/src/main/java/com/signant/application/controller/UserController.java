package com.signant.application.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signant.application.model.Users;
import com.signant.application.repository.UserRepository;

@RestController
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	Collection<Users> getAllUsers() {
		return userRepository.findAll();
	};
	
	@GetMapping("/users/getByEmail")
	@ResponseBody
	public Optional<Users> getGroup(@RequestParam String email) {
		Optional<Users> user= userRepository.findByEmail(email);
		return user;
		
	};
	@PostMapping("/users")
    ResponseEntity<Users> createGroup(@Validated @RequestBody Users user) throws URISyntaxException {
        log.info("Request to create a User: {}", user);
        Users result = userRepository.save(user);
        return ResponseEntity.created(new URI("/users/" + result.getId()))
                .body(result);
    };

}
