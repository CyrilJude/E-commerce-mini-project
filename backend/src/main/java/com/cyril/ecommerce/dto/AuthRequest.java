package com.cyril.ecommerce.dto;

import jakarta.validation.constraints.*;

public record AuthRequest(@Email @NotBlank String email, @NotBlank @Size(min = 6) String password) {
}
