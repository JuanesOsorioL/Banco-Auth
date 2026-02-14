package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.mapper;

import com.banco.auth.domain.login.Login;
import com.banco.auth.domain.role.Role;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.entity.LoginEntity;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.entity.RoleEntity;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.mapper.RoleEntityMapper;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.repository.RoleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LoginEntityMapper {

    private final RoleJpaRepository roleJpaRepository;

    private final RoleEntityMapper roleEntityMapper;


    public LoginEntityMapper(RoleJpaRepository roleJpaRepository, RoleEntityMapper roleEntityMapper) {
        this.roleJpaRepository = roleJpaRepository;
        this.roleEntityMapper = roleEntityMapper;
    }

    public Login toLoginCreate(LoginEntity loginEntity) {
        if (loginEntity == null) {
            return null;
        }

        Set<Role> roles = loginEntity.getRoles().stream()
                .map(roleEntityMapper::toRole)
                .collect(Collectors.toSet());

        return new Login(
                loginEntity.getLoginId(),
                loginEntity.getUsername(),
                loginEntity.getEmail(),
                loginEntity.getClue(),
                roles,
                loginEntity.getState()
        );
    }

    public LoginEntity toLoginEntityCreate(Login login) {
        if (login == null) {
            return null;
        }

        Set<RoleEntity> roles = new HashSet<>();

        return LoginEntity.builder()
                .loginId(login.loginId())
                .username(login.username())
                .email(login.email())
                .clue(login.clue())
                .roles(roles)
                .state(login.state())
                .build();
    }

    public Login toLogin(LoginEntity loginEntity) {
        if (loginEntity == null) {
            return null;
        }

        Set<Role> setRoles = roleEntityMapper.toSetRoles(loginEntity.getRoles());


        Set<Role> roles = loginEntity.getRoles().isEmpty() ? new HashSet<>() : setRoles;

        return new Login(
                loginEntity.getLoginId(),
                loginEntity.getUsername(),
                loginEntity.getEmail(),
                loginEntity.getClue(),
                roles,
                loginEntity.getState()
        );
    }

    public Login toLoginAdd(LoginEntity loginEntity) {
        if (loginEntity == null) {
            return null;
        }

        Set<Role> roles = loginEntity.getRoles().stream()
                .map(roleEntityMapper::toRole)
                .collect(Collectors.toSet());

        return new Login(
                loginEntity.getLoginId(),
                loginEntity.getUsername(),
                loginEntity.getEmail(),
                loginEntity.getClue(),
                roles,
                loginEntity.getState()
        );
    }

    public LoginEntity toLoginEntityAdd(Login login) {
        if (login == null) {
            return null;
        }

        Set<RoleEntity> roles = login.roles().stream()
                .map(roleEntityMapper::toRoleEntity)
                .collect(Collectors.toSet());

        return LoginEntity.builder()
                .loginId(login.loginId())
                .username(login.username())
                .email(login.email())
                .clue(login.clue())
                .roles(roles)
                .state(login.state())
                .build();
    }

}
