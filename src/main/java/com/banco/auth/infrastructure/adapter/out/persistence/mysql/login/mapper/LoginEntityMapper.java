package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.mapper;

import com.banco.auth.domain.login.Login;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.entity.LoginEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginEntityMapper {

    public Login toLogin(LoginEntity loginEntity) {
        if (loginEntity == null) {
            return null;
        }

        return new Login(
                loginEntity.getLoginId(),
                loginEntity.getUsername(),
                loginEntity.getEmail(),
                loginEntity.getClue(),
                loginEntity.getRolId(),
                loginEntity.getState()
        );
    }

    public LoginEntity toLoginEntity(Login login) {
        if (login == null) {
            return null;
        }

        return LoginEntity.builder()
                .loginId(login.loginId())
                .username(login.username())
                .email(login.email())
                .clue(login.clue())
                .rolId(login.rolId())
                .state(login.state())
                .build();
    }
}
