package com.project.ecommerce.requests;

import com.project.ecommerce.enums.RolesEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UserRequest(
        UUID id,
        @NotBlank(message = "Nome não pode ser vazio")
        String name,
        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Senha não pode ser vazia")
        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
        String password,
        RolesEnum roles
) {
}
