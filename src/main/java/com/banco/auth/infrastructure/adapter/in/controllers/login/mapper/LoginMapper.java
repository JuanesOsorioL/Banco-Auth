package com.banco.auth.infrastructure.adapter.in.controllers.login.mapper;

import com.banco.auth.domain.login.Login;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginAllResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginRequestDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    Login toLogin(LoginRequestDto login);

    LoginResponseDto toLoginResponseDto(Login login);

    LoginAllResponseDto toLoginAllResponseDto(Login login);
}
