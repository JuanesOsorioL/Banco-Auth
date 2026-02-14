package com.banco.auth.domain.login.outPort;

import com.banco.auth.domain.login.Login;

import java.util.List;
import java.util.UUID;

public interface LoginRepositoryOut {
    Login register(Login login);

    List<Login> getAllRegister();

    Login getloginById(UUID id);

    Login addRole(Login login);

}
