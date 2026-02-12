package com.banco.auth.infrastructure.adapter.in.controllers.login.dto;

import java.util.UUID;

public record LoginResponseDto(
        UUID loginId,
        String username,
        String email,
        String rolId,
        String state
) {
}
