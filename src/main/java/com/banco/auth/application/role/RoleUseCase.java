package com.banco.auth.application.role;

import com.banco.auth.application.role.inPort.RoleUseCaseIn;
import com.banco.auth.domain.role.Role;
import com.banco.auth.domain.role.outPort.RoleRepositoryOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleUseCase implements RoleUseCaseIn {

    private final RoleRepositoryOut roleRepository;

    public RoleUseCase(RoleRepositoryOut roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        Role newRole = Role.createRole(role.rolName(), role.rolDescription());
        return roleRepository.createRole(newRole);
    }

    @Override
    public Role findRoleById(UUID id) {
        return roleRepository.findRoleById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAllRoles();
    }
}
