package com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.mapper;

import com.banco.auth.domain.role.Role;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityMapper {
    public RoleEntity toRoleEntity(Role role) {
        if (role == null) {
            return null;
        }

        return RoleEntity.builder()
                .rolId(role.rolId())
                .rolName(role.rolName())
                .rolDescription(role.rolDescription())
                .rolStatus(role.rolStatus())
                .build();
    }

    public Role toRole(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return new Role(
                roleEntity.getRolId(),
                roleEntity.getRolName(),
                roleEntity.getRolDescription(),
                roleEntity.getRolStatus()
        );
    }
}
