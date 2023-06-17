package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.UserRepository;

@RestController
@RequestMapping("/api/public/")
public class SessionController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("signup")
	public ResponseEntity<?> signup(@RequestBody UserEntity user) {
		userRepo.save(user);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("users")
	public ResponseEntity<?> getAllUsers(){
		List<UserEntity> users = userRepo.findAll();
		return ResponseEntity.ok(users);
	}
}
