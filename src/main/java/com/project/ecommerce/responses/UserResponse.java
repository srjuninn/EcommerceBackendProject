package com.project.ecommerce.responses;

import com.project.ecommerce.enums.RolesEnum;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email
) {
}
