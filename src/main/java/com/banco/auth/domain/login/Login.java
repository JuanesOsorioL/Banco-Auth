package com.banco.auth.domain.login;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Login (UUID loginId , String username, String email, String clue, String rolId, String state ) {

}
