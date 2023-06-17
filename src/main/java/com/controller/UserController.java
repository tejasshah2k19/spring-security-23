package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.UserRepository;

@RestController
@RequestMapping("/api/user/") 
public class UserController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/users")
	public ResponseEntity<?> getAllUsersSorted() {
		List<UserEntity> users = userRepo.findAll(Sort.by(Sort.Direction.DESC,"firstName"));
		return ResponseEntity.ok(users);
	}
}
