package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.adapter;

import com.banco.auth.domain.login.Login;
import com.banco.auth.domain.login.outPort.LoginRepositoryOut;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.mapper.LoginEntityMapper;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.repository.LoginJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginAdapter implements LoginRepositoryOut {

    private final LoginJpaRepository loginJpaRepository;
    private final LoginEntityMapper loginEntityMapper;


    public LoginAdapter(LoginJpaRepository loginJpaRepository, LoginEntityMapper loginEntityMapper) {
        this.loginJpaRepository = loginJpaRepository;
        this.loginEntityMapper = loginEntityMapper;
    }


    @Override
    public Login register(Login login) {
        return loginEntityMapper.toLogin(loginJpaRepository.save(loginEntityMapper.toLoginEntity(login)));
    }

    @Override
    public List<Login> getAllRegister() {
        return loginJpaRepository.findAll().stream()
                .map(loginEntityMapper::toLogin).toList();
    }
}
