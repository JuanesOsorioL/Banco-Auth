package com.banco.auth.domain.login;

import java.util.UUID;

public record Login(UUID loginId, String username, String email, String clue, String rolId, Boolean state) {

    public Login {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email is required");
        if (clue == null || clue.isBlank()) throw new IllegalArgumentException("clue is required");
        if (rolId == null || rolId.isBlank()) throw new IllegalArgumentException("rolId is required");
    }

    public static Login create(String username, String email, String clue, String rolId) {
        return new Login(null, username, email, clue, rolId, true);
    }

    public Login deactivate() {
        return new Login(loginId, username, email, clue, rolId, false);
    }

    public Login activate() {
        return new Login(loginId, username, email, clue, rolId, true);
    }

    public Login changeRole(String rolId) {
        return new Login(loginId, username, email, clue, rolId, state);
    }

}
