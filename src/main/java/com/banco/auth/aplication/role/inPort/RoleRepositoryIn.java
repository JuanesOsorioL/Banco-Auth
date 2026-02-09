package com.banco.auth.aplication.role.inPort;

import com.banco.auth.domain.role.Role;

import java.util.UUID;

public interface RoleRepositoryIn {
    Role getRolById(UUID id);

    Role createRol(Role rol);

    Role updateRol(Role rol);

    Role deactivateRolById(UUID id);
}
