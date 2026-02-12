package com.banco.auth.infrastructure.adapter.in.controllers.role.dto;

import java.util.UUID;

public record RoleResponseDto(UUID rolId,
                              String rolName,
                              String rolDescription,
                              Boolean rolStatus) {
}
