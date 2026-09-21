package com.cyril.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, length = 1000)
	private String description;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private Integer stock;
	@Column(length = 1000)
	private String imageUrl;
}
