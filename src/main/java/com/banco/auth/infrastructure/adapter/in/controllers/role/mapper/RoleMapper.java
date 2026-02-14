package com.banco.auth.infrastructure.adapter.in.controllers.role.mapper;

import com.banco.auth.domain.role.Role;
import com.banco.auth.infrastructure.adapter.in.controllers.role.dto.RoleRequestDto;
import com.banco.auth.infrastructure.adapter.in.controllers.role.dto.RoleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleRequestDto roleRequestDto);

    RoleResponseDto toRoleResponseDto(Role role);

}
