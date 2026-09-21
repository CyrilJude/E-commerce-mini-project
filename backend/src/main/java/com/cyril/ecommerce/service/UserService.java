package com.cyril.ecommerce.service;

import com.cyril.ecommerce.entity.User;
import com.cyril.ecommerce.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository repo;
	private final PasswordEncoder encoder;

	public UserService(UserRepository repo, PasswordEncoder encoder) {
		this.repo = repo;
		this.encoder = encoder;
	}

	public User current() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return repo.findByEmail(email).orElseThrow();
	}

	public User register(User user) {
		if (repo.findByEmail(user.getEmail()).isPresent())
			throw new IllegalArgumentException("Email already registered");
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

	public User update(User user) {
		return repo.save(user);
	}
}
