package com.cyril.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record PaymentRequest(@NotNull Long orderId, boolean success) {
}
