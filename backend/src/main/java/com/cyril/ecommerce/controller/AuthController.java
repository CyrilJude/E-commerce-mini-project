package com.cyril.ecommerce.controller;

import com.cyril.ecommerce.dto.*;
import com.cyril.ecommerce.entity.User;
import com.cyril.ecommerce.repository.UserRepository;
import com.cyril.ecommerce.security.JwtService;
import com.cyril.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final UserRepository repo;
	private final PasswordEncoder encoder;
	private final JwtService jwt;
	private final UserService service;

	public AuthController(UserRepository repo, PasswordEncoder encoder, JwtService jwt, UserService service) {
		this.repo = repo;
		this.encoder = encoder;
		this.jwt = jwt;
		this.service = service;
	}

	@PostMapping("/register")
	public AuthResponse register(@Valid @RequestBody RegisterRequest r) {
		User u = service.register(new User(null, r.email(), r.password(), r.name(), r.phone(), r.address()));
		return new AuthResponse(jwt.generate(u.getEmail()), u.getId(), u.getName(), u.getEmail());
	}

	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody AuthRequest r) {
		User u = repo.findByEmail(r.email())
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		if (!encoder.matches(r.password(), u.getPassword()))
			throw new IllegalArgumentException("Invalid email or password");
		return new AuthResponse(jwt.generate(u.getEmail()), u.getId(), u.getName(), u.getEmail());
	}
}
