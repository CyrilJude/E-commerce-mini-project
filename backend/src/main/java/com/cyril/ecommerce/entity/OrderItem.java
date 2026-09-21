package com.cyril.ecommerce.entity;
import com.fasterxml.jackson.annotation.JsonIgnore; import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="order_items") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderItem { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @JsonIgnore @ManyToOne(optional=false) @JoinColumn(name="order_id") private Order order; @ManyToOne(optional=false) @JoinColumn(name="product_id") private Product product; @Column(nullable=false) private Integer quantity; @Column(nullable=false) private Double price; }
