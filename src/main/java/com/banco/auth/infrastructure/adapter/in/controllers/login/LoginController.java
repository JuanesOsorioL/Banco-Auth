package com.banco.auth.infrastructure.adapter.in.controllers.login;

import com.banco.auth.application.login.inPort.LoginUseCaseIn;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginRequestDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.dto.LoginResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.login.mapper.LoginMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginUseCaseIn loginUseCaseIn;
    private final LoginMapper loginMapper;

    public LoginController(LoginUseCaseIn loginUseCaseIn, LoginMapper loginMapper) {
        this.loginUseCaseIn = loginUseCaseIn;
        this.loginMapper = loginMapper;
    }


    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> create(@Valid @RequestBody LoginRequestDto login) {
        return ResponseEntity.ok(loginMapper.toLoginResponseDto(loginUseCaseIn.register(loginMapper.toLogin(login))));
    }

    @GetMapping
    public ResponseEntity<List<LoginResponseDto>> getAllLogin() {
        return ResponseEntity.ok(loginUseCaseIn.getAllRegister().stream()
                .map(loginMapper::toLoginResponseDto).toList());
    }
}
