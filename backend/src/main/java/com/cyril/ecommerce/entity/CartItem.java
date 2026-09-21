package com.cyril.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "product_id" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(nullable = false)
	private Integer quantity;
}
