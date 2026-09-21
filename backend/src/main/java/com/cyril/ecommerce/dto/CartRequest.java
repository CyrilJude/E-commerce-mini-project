package com.cyril.ecommerce.dto;

import jakarta.validation.constraints.*;

public record CartRequest(@NotNull Long productId, @Min(1) int quantity) {
}
