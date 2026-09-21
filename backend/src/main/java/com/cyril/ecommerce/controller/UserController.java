package com.cyril.ecommerce.controller;

import com.cyril.ecommerce.entity.User;
import com.cyril.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/me")
	public User me() {
		User u = service.current();
		u.setPassword(null);
		return u;
	}

	@PutMapping("/me")
	public User update(@RequestBody User in) {
		User u = service.current();
		u.setName(in.getName());
		u.setPhone(in.getPhone());
		u.setAddress(in.getAddress());
		User saved = service.update(u);
		saved.setPassword(null);
		return saved;
	}
}
