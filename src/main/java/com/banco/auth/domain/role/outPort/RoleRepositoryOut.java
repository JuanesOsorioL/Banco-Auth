package com.banco.auth.domain.role.outPort;

import com.banco.auth.domain.role.Role;

import java.util.UUID;

public interface RoleRepositoryOut {
    Role getRolById(UUID id);

    Role createRol(Role rol);

    Role updateRol(Role rol);

    Role deactivateRolById(UUID id);
}
