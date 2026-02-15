package com.banco.auth.application.login;

import com.banco.auth.application.login.inPort.LoginUseCaseIn;
import com.banco.auth.application.role.inPort.RoleUseCaseIn;
import com.banco.auth.domain.login.Login;
import com.banco.auth.domain.login.outPort.LoginRepositoryOut;
import com.banco.auth.domain.role.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoginUseCase implements LoginUseCaseIn {

    private final LoginRepositoryOut loginRepository;
    private final RoleUseCaseIn roleUseCaseIn;

    public LoginUseCase(LoginRepositoryOut loginRepository, RoleUseCaseIn roleUseCaseIn) {
        this.loginRepository = loginRepository;
        this.roleUseCaseIn = roleUseCaseIn;
    }

    @Override
    public Login register(Login login) {
        Login NewLogin = Login.create(login.username(), login.email(), login.clue());
        return loginRepository.register(NewLogin);
    }

    @Override
    public List<Login> getAllRegister() {
        return loginRepository.getAllRegister();
    }

    @Override
    public Login addRole(UUID id, Set<UUID> roles) {

        Login login = loginRepository.getloginById(id);
        if (login == null) {
            throw new RuntimeException("Login no encontrado con el ID: " + id);
        }

        Set<Role> setRoles = roles.stream()
                .map(roleUseCaseIn::findRoleById)
                .filter(role -> !login.roles().contains(role))
                .collect(Collectors.toSet());
        login.roles().addAll(setRoles);
        return loginRepository.addRole(login);
    }

    @Override
    public Login removeRole(UUID id, Set<UUID> roles) {
        Login login = loginRepository.getloginById(id);
        if (login == null) {
            throw new RuntimeException("Login no encontrado con el ID: " + id);
        }
        Set<Role> rolesToRemove = roles.stream()
                .map(roleUseCaseIn::findRoleById)
                .filter(role -> login.roles().contains(role))
                .collect(Collectors.toSet());

        login.roles().removeAll(rolesToRemove);

        return loginRepository.removeRole(login);
    }
}
