package com.cyril.ecommerce;

import com.cyril.ecommerce.entity.Product;
import com.cyril.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedProducts(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product(null, "Wireless Headphones", "Noise-cancelling over-ear headphones", "Electronics", 2999.0, 25, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=900&q=80"));
                repository.save(new Product(null, "Smart Watch", "Fitness and notification smart watch", "Electronics", 4499.0, 18, "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=900&q=80"));
                repository.save(new Product(null, "Running Shoes", "Lightweight everyday running shoes", "Fashion", 2499.0, 30, "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=900&q=80"));
                repository.save(new Product(null, "Backpack", "Water-resistant laptop backpack", "Accessories", 1599.0, 22, "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?auto=format&fit=crop&w=900&q=80"));
                repository.save(new Product(null, "Mechanical Keyboard", "Compact RGB mechanical keyboard", "Electronics", 3799.0, 15, "https://images.unsplash.com/photo-1587829741301-dc798b83add3?auto=format&fit=crop&w=900&q=80"));
                repository.save(new Product(null, "Classic Hoodie", "Comfortable cotton-blend hoodie", "Fashion", 1299.0, 40, "https://images.unsplash.com/photo-1556821840-3a63f95609a7?auto=format&fit=crop&w=900&q=80"));
            }
        };
    }
}
