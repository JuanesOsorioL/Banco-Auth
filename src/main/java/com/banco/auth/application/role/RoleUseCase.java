package com.banco.auth.application.role;

import com.banco.auth.application.role.inPort.RoleUseCaseIn;
import com.banco.auth.domain.logger.Logger;
import com.banco.auth.domain.role.Role;
import com.banco.auth.domain.role.outPort.RoleRepositoryOut;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
public class RoleUseCase implements RoleUseCaseIn {

    private final RoleRepositoryOut roleRepository;
    private final Logger logger;

    public RoleUseCase(RoleRepositoryOut roleRepository, Logger logger) {
        this.roleRepository = roleRepository;
        this.logger = logger;
    }


    @Override
    public Role createRole(Role role) {
        logger.info("Creating a new role with name: {}", role.rolName());
        Role newRole = role.createRole(role.rolName(), role.rolDescription());
        try {
            Role savedRole = roleRepository.createRole(newRole);
            logger.info("Role created successfully with ID: {}", savedRole.rolId());
            return savedRole;
        } catch (DataIntegrityViolationException e) {
            logger.error("Error creating role, name already exists: {}", role.rolName(), e);
            throw new IllegalArgumentException("El nombre del rol ya existe, por favor elija otro.");
        }
    }

    @Override
    public Role findRoleById(UUID id) {
        logger.debug("Looking for role with ID: {}", id);
        return roleRepository.findRoleById(id)
                .orElseThrow(() -> {
                    logger.warn("Role not found with ID: {}", id);
                    return new RuntimeException("Role not found with id: " + id);
                });
    }

    @Override
    public List<Role> findAllRoles() {
        logger.debug("Retrieving all roles");
        return roleRepository.findAllRoles();
    }

    @Override
    public Role statusTrue(UUID id) {
        logger.info("Activating role with ID: {}", id);
        return changeRoleStatus(id, Role::roleActivate);
    }

    @Override
    public Role statusFalse(UUID id) {
        logger.info("Deactivating role with ID: {}", id);
        return changeRoleStatus(id, Role::roleDeactivate);
    }

    @Override
    public Role updateRole(Role role) {
        logger.info("Updating role with ID: {}", role.rolId());
        // DataIntegrityViolationException
        Role existRole = this.findRoleById(role.rolId());
        try {
            Role updatedRole = roleRepository.saveChange(Role.updateRole(existRole.rolId(), role.rolName(), role.rolDescription(),existRole.rolStatus()));
            logger.info("Role updated successfully with ID: {}", updatedRole.rolId());
            return updatedRole;
        } catch (DataIntegrityViolationException e) {
            logger.error("Error updating role, name already exists: {}", role.rolName(), e);
            throw new IllegalArgumentException("El nombre del rol ya existe, por favor elija otro.");
        }
    }

    private Role changeRoleStatus(UUID id, Function<Role, Role> statusChange) {
        logger.debug("Changing status for role with ID: {}", id);
        Role changeStatusRole = this.findRoleById(id);

        Role updatedRole = statusChange.apply(changeStatusRole);
        return roleRepository.saveChange(updatedRole);
    }
}
