package com.banco.auth.domain.role;

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
        return new Role(rolId, rolName, rolDescription,false);
    }

    public Role roleActivate() {
        return new Role(rolId, rolName, rolDescription, true);
    }


}
