package com.banco.auth.application.login;

import com.banco.auth.application.login.inPort.LoginUseCaseIn;
import com.banco.auth.domain.login.Login;
import com.banco.auth.domain.login.outPort.LoginRepositoryOut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUseCase implements LoginUseCaseIn {

    private final LoginRepositoryOut loginRepository;

    public LoginUseCase(LoginRepositoryOut loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login register(Login login) {
        Login NewLogin = Login.create(login.username(), login.email(), login.clue(), login.rolId());
        return loginRepository.register(NewLogin);
    }

    @Override
    public List<Login> getAllRegister() {
        return loginRepository.getAllRegister();
    }
}
