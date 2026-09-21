package com.cyril.ecommerce.controller;

import com.cyril.ecommerce.dto.CartRequest;
import com.cyril.ecommerce.entity.*;
import com.cyril.ecommerce.repository.*;
import com.cyril.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	private final CartItemRepository cart;
	private final ProductRepository products;
	private final UserService users;

	public CartController(CartItemRepository cart, ProductRepository products, UserService users) {
		this.cart = cart;
		this.products = products;
		this.users = users;
	}

	@GetMapping
	public List<CartItem> all() {
		return cart.findByUser(users.current());
	}

	@PostMapping("/items")
	public CartItem add(@Valid @RequestBody CartRequest r) {
		User u = users.current();
		Product p = products.findById(r.productId()).orElseThrow();
		CartItem i = cart.findByUserAndProduct(u, p).orElse(new CartItem(null, u, p, 0));
		int q = i.getQuantity() + r.quantity();
		if (q > p.getStock())
			throw new IllegalArgumentException("Not enough stock");
		i.setQuantity(q);
		return cart.save(i);
	}

	@PutMapping("/items/{productId}")
	public CartItem update(@PathVariable Long productId, @RequestParam int quantity) {
		User u = users.current();
		Product p = products.findById(productId).orElseThrow();
		CartItem i = cart.findByUserAndProduct(u, p).orElseThrow();
		if (quantity < 1 || quantity > p.getStock())
			throw new IllegalArgumentException("Invalid quantity");
		i.setQuantity(quantity);
		return cart.save(i);
	}

	@DeleteMapping("/items/{productId}")
	public void remove(@PathVariable Long productId) {
		User u = users.current();
		Product p = products.findById(productId).orElseThrow();
		cart.findByUserAndProduct(u, p).ifPresent(cart::delete);
	}

	@DeleteMapping
	public void clear() {
		cart.deleteByUser(users.current());
	}
}
