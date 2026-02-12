package com.banco.auth.application.role.inPort;

import com.banco.auth.domain.role.Role;

import java.util.List;
import java.util.UUID;


public interface RoleUseCaseIn {
    Role createRole(Role rol);

    Role findRoleById(UUID id);

    List<Role> findAllRoles();

}
