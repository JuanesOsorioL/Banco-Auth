package com.banco.auth.domain.login.outPort;

import com.banco.auth.domain.login.Login;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoginRepositoryOut {
    Login register(Login login);

    List<Login> getAllRegister();

    Optional<Login> getloginById(UUID id);

    Login addOrRemoveRole(Login login);

    Login saveChange(Login login);

}
