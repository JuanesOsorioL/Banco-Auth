package com.banco.auth.domain.role.outPort;

import com.banco.auth.domain.role.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepositoryOut {
    Role createRole(Role rol);

    Optional<Role> findRoleById(UUID id);

    List<Role> findAllRoles();

    Role saveChange(Role rol);

}
