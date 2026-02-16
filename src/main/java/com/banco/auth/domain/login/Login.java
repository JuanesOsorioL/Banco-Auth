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
        if (clue == null || clue.isBlank()) throw new IllegalArgumentException("clue is required hhh");
    }

    public static Login create(String username, String email, String clue) {
        return new Login(null, username, email, clue, new HashSet<>(), true);
    }

    public static Login update(UUID loginId,String username, String email, String clue, Set<Role> roles, Boolean state) {
        return new Login(loginId, username, email, clue, roles, state);
    }

    public Login disabled() {
        return new Login(loginId, username, email, clue, roles, false);
    }

    public Login enabled() {
        return new Login(loginId, username, email, clue, roles, true);
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginId=" + loginId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", clue='" + clue + '\'' +
                ", roles=" + roles +
                ", state=" + state +
                '}';
    }

    public Login addRoles(Set<Role> newRoles) {
        HashSet<Role> updated = new HashSet<>(roles);
        updated.addAll(newRoles);
        return new Login(loginId, username,  email,  clue,updated,  state);
    }

    public Login removeRole(Set<Role> removeRoles) {
        HashSet<Role>  updated = new HashSet<>(roles);
        updated.removeAll(removeRoles);
        return new Login(loginId, username,  email,  clue,updated,  state);
    }

}
