package com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.adapter;

import com.banco.auth.domain.role.Role;
import com.banco.auth.domain.role.outPort.RoleRepositoryOut;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.mapper.RoleEntityMapper;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.repository.RoleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RoleAdapter implements RoleRepositoryOut {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper roleEntityMapper;

    public RoleAdapter(RoleJpaRepository roleJpaRepository, RoleEntityMapper roleEntityMapper) {
        this.roleJpaRepository = roleJpaRepository;
        this.roleEntityMapper = roleEntityMapper;
    }

    @Override
    public Role createRole(Role rol) {
        return roleEntityMapper.toRole(roleJpaRepository.save(roleEntityMapper.toRoleEntity(rol)));
    }

    @Override
    public Role findRoleById(UUID id) {
        return roleEntityMapper.toRole(roleJpaRepository.getReferenceById(id));
    }

    @Override
    public List<Role> findAllRoles() {
        return roleJpaRepository.findAll().stream()
                .map(roleEntityMapper::toRole).toList();
    }
}
