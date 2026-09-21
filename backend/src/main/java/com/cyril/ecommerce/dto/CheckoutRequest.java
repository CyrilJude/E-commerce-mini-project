package com.cyril.ecommerce.dto; import jakarta.validation.constraints.NotBlank; public record CheckoutRequest(@NotBlank String shippingAddress){}
