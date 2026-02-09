package com.banco.auth.aplication.login.inPort;

import com.banco.auth.domain.login.Login;

public interface LoginRepositoryIn {
    Login createlogin(Login login);

    Boolean existslogin(Login login);

    Login accesslogin(Login login);

    Login updatelogin(Login login);

    Login deactivatelogin(Login login);
}
