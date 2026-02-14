package com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.mapper;

import com.banco.auth.domain.role.Role;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    RoleEntity toRoleEntity(Role role);

    Role toRole(RoleEntity roleEntity);

    Set<Role> toSetRoles(Set<RoleEntity> roles);

}
