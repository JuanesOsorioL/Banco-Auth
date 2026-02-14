package com.banco.auth.domain.role;

import java.util.Objects;
import java.util.UUID;

public record Role(
        UUID rolId,
        String rolName,
        String rolDescription,
        Boolean rolStatus) {

    public Role {
        if (rolName == null || rolName.isBlank()) throw new IllegalArgumentException("name requerido");
        if (rolDescription == null) throw new IllegalArgumentException("Description requerido");
    }

    public static Role createRole(String rolName, String rolDescription) {
        return new Role(null, rolName, rolDescription, true);
    }

    public Role roleDeactivate() {
        return new Role(rolId, rolName, rolDescription, false);
    }

    public Role roleActivate() {
        return new Role(rolId, rolName, rolDescription, true);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(rolId, role.rolId) && Objects.equals(rolName, role.rolName) && Objects.equals(rolStatus, role.rolStatus) && Objects.equals(rolDescription, role.rolDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolId, rolName, rolDescription, rolStatus);
    }
}
