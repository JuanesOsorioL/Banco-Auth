package com.banco.auth.infrastructure.adapter.in.controllers.login.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record LoginAddRoleRequestDto(@NotEmpty(message = "USR_002")
                                     Set<UUID> rolIds) {
}
