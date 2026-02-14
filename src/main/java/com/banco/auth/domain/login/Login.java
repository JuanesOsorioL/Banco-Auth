package com.banco.auth.domain.login;

import com.banco.auth.domain.role.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record Login(UUID loginId,
                    String username,
                    String email,
                    String clue,
                    Set<Role> roles,
                    Boolean state) {

    public Login {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email is required");
        if (clue == null || clue.isBlank()) throw new IllegalArgumentException("clue is required");
    }

    public static Login create(String username, String email, String clue) {
        return new Login(null, username, email, clue, new HashSet<>(), true);
    }

    public Login deactivate() {
        return new Login(loginId, username, email, clue, roles, false);
    }

    public Login activate() {
        return new Login(loginId, username, email, clue, roles, true);
    }

    public Login addRole(Role role) {
        Set<Role> updatedRoles = new HashSet<>(roles);
        updatedRoles.add(role);
        return new Login(loginId, username, email, clue, updatedRoles, state);
    }

}
