package com.banco.auth.infrastructure.adapter.in.controllers.login.mapper;

import com.banco.auth.domain.login.Login;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginAllResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.role.dto.RoleResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.role.mapper.RoleMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LoginResponseAssembler {

    private final RoleMapper roleMapper;
    private final LoginMapper loginMapper;

    public LoginResponseAssembler(RoleMapper roleMapper, LoginMapper loginMapper) {
        this.roleMapper = roleMapper;
        this.loginMapper = loginMapper;
    }

    public LoginAllResponseDto getLoginAllResponseDto(Login login) {
        Set<RoleResponseDto> rolId = login.roles().stream()
                .map(roleMapper::toRoleResponseDto)
                .collect(Collectors.toSet());

        LoginAllResponseDto response = loginMapper.toLoginAllResponseDto(login);
        response = new LoginAllResponseDto(response.loginId(), response.username(), response.email(), rolId, response.state());
        return response;
    }
}
