package com.project.ecommerce.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank
        String name,
        @Email
        String email,
        @Size(min = 8, max = 20)
        String password
) {
}
