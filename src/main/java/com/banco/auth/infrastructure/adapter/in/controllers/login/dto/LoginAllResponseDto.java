package com.banco.auth.infrastructure.adapter.in.controllers.login.dto;

import com.banco.auth.infrastructure.adapter.in.controllers.role.dto.RoleResponseDto;

import java.util.Set;
import java.util.UUID;

public record LoginAllResponseDto(
        UUID loginId,
        String username,
        String email,
        Set<RoleResponseDto> rolId,
        String state
) {
}
