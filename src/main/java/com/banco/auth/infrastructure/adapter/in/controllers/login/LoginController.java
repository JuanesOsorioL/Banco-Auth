package com.banco.auth.infrastructure.adapter.in.controllers.login;

import com.banco.auth.application.login.inPort.LoginUseCaseIn;
import com.banco.auth.domain.login.Login;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginAddRoleRequestDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginAllResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginRequestDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.mapper.LoginMapper;
import com.banco.auth.infrastructure.adapter.in.controllers.role.dto.RoleResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.role.mapper.RoleMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginUseCaseIn loginUseCaseIn;
    private final LoginMapper loginMapper;
    private final RoleMapper roleMapper;

    public LoginController(LoginUseCaseIn loginUseCaseIn, LoginMapper loginMapper, RoleMapper roleMapper) {
        this.loginUseCaseIn = loginUseCaseIn;
        this.loginMapper = loginMapper;
        this.roleMapper = roleMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> create(@RequestBody @Valid LoginRequestDto login) {
        return ResponseEntity.ok(loginMapper.toLoginResponseDto(loginUseCaseIn.register(loginMapper.toLogin(login))));
    }

    @PostMapping("/{loginId}/roles")
    public ResponseEntity<LoginAllResponseDto> addRole(@PathVariable UUID loginId, @Valid @RequestBody LoginAddRoleRequestDto request) {
        Set<UUID> roles = request.rolIds();

        Login login = loginUseCaseIn.addRole(loginId, roles);

        Set<RoleResponseDto> rolId = login.roles().stream()
                .map(roleMapper::toRoleResponseDto)
                .collect(Collectors.toSet());

        LoginAllResponseDto response = loginMapper.toLoginAllResponseDto(login);
        response = new LoginAllResponseDto(response.loginId(), response.username(), response.email(), rolId, response.state());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LoginAllResponseDto>> getAllLogin() {

        List<Login> logins = loginUseCaseIn.getAllRegister();

        List<LoginAllResponseDto> response = logins.stream()
                .peek(v -> System.out.println("mira" + v))
                .map(login -> {
                    Set<RoleResponseDto> roleDtos = login.roles().stream()
                            .peek(a -> System.out.println("valor interno" + a))
                            .map(roleMapper::toRoleResponseDto)
                            .collect(Collectors.toSet());

                    LoginAllResponseDto loginDto = loginMapper.toLoginAllResponseDto(login);
                    return new LoginAllResponseDto(
                            loginDto.loginId(),
                            loginDto.username(),
                            loginDto.email(),
                            roleDtos,
                            loginDto.state()
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
