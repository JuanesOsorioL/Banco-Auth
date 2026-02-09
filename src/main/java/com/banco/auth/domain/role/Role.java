package com.banco.auth.domain.role;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Role(
        UUID idRol,
        String name,
        String description,
        String status){
}
