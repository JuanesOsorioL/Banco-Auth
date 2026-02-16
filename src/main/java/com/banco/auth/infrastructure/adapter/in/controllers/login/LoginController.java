package com.banco.auth.infrastructure.adapter.in.controllers.login;

import com.banco.auth.application.login.inPort.LoginUseCaseIn;
import com.banco.auth.domain.login.Login;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.*;
import com.banco.auth.infrastructure.adapter.in.controllers.login.mapper.LoginMapper;
import com.banco.auth.infrastructure.adapter.in.controllers.login.mapper.LoginResponseAssembler;
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
    private final LoginResponseAssembler loginResponseAssembler;

    public LoginController(LoginUseCaseIn loginUseCaseIn, LoginMapper loginMapper, RoleMapper roleMapper, LoginResponseAssembler loginResponseAssembler) {
        this.loginUseCaseIn = loginUseCaseIn;
        this.loginMapper = loginMapper;
        this.roleMapper = roleMapper;
        this.loginResponseAssembler = loginResponseAssembler;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> create(@RequestBody @Valid LoginRequestDto login) {
        return ResponseEntity.ok(loginMapper.toLoginResponseDto(loginUseCaseIn.register(loginMapper.toLogin(login))));
    }

    @PostMapping("/add/{loginId}/roles")
    public ResponseEntity<LoginAllResponseDto> addRole(@PathVariable UUID loginId, @Valid @RequestBody LoginAddRoleRequestDto request) {
        Set<UUID> roles = request.rolIds();

        Login login = loginUseCaseIn.addRole(loginId, roles);

        LoginAllResponseDto response = loginResponseAssembler.getLoginAllResponseDto(login);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/remove/{loginId}/roles")
    public ResponseEntity<LoginAllResponseDto> removeRole(@PathVariable UUID loginId, @Valid @RequestBody LoginAddRoleRequestDto request) {
        Set<UUID> roles = request.rolIds();

        Login login = loginUseCaseIn.removeRole(loginId, roles);

        LoginAllResponseDto response = loginResponseAssembler.getLoginAllResponseDto(login);

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<LoginAllResponseDto>> getAllLogin() {

        List<Login> logins = loginUseCaseIn.getAllRegister();

        List<LoginAllResponseDto> response = logins.stream()
                .map(login -> {
                    Set<RoleResponseDto> roleDtos = login.roles().stream()
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

    @PostMapping("/update")
    public ResponseEntity<LoginAllResponseDto> updateLogin(@Valid @RequestBody LoginUpdateRequestDto request) {
        return ResponseEntity.ok(loginMapper.toLoginAllResponseDto(loginUseCaseIn.UpdateLogin(loginMapper.toLogin(request))));
    }


    @GetMapping("/enabled/{loginId}")
    public ResponseEntity<LoginAllResponseDto> enabledRole(@Valid @PathVariable UUID loginId) {
        return ResponseEntity.ok(loginMapper.toLoginAllResponseDto(loginUseCaseIn.loginEnabled(loginId)));
    }

    @GetMapping("/disabled/{loginId}")
    public ResponseEntity<LoginAllResponseDto> disableRole(@Valid @PathVariable UUID loginId) {
        return ResponseEntity.ok(loginMapper.toLoginAllResponseDto(loginUseCaseIn.loginDisabled(loginId)));
    }

}
//new HashSet<>()