package com.banco.auth.infrastructure.adapter.in.controllers.role.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDto(@NotBlank(message = "USR_007")
                             String rolName,

                             @NotBlank(message = "USR_008")
                             String rolDescription

) {
}
