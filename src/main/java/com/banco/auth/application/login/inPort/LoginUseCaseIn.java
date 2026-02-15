package com.banco.auth.application.login.inPort;

import com.banco.auth.domain.login.Login;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface LoginUseCaseIn {
    Login register(Login login);

    List<Login> getAllRegister();

    Login addRole(UUID id, Set<UUID> roles);

    Login removeRole(UUID id, Set<UUID> roles);

}
