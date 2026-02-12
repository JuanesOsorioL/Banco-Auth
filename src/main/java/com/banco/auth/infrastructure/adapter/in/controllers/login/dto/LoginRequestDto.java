package com.banco.auth.infrastructure.adapter.in.controllers.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @NotBlank(message = "USR_001")
        String username,

        @NotBlank(message = "USR_002")
        @Email(message = "USR_003")
        String email,

        @NotBlank(message = "USR_004")
        @Size(min = 8, max = 64, message = "USR_005")
        String clue,

        @NotBlank(message = "USR_006")
        String rolId
) {
}
