package com.banco.auth.application.login.inPort;

import com.banco.auth.domain.login.Login;

import java.util.List;

public interface LoginUseCaseIn {
    Login register(Login login);

    List<Login> getAllRegister();

}
