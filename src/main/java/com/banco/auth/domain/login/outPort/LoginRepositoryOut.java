package com.banco.auth.domain.login.outPort;

import com.banco.auth.domain.login.Login;


public interface LoginRepositoryOut {
    Login createlogin(Login login);

    Boolean existslogin(Login login);

    Login accesslogin(Login login);

    Login updatelogin(Login login);

    Login deactivatelogin(Login login);
}
