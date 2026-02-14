package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.adapter;

import com.banco.auth.domain.login.Login;
import com.banco.auth.domain.login.outPort.LoginRepositoryOut;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.mapper.LoginEntityMapper;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.repository.LoginJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
        return loginEntityMapper.toLoginCreate(loginJpaRepository.save(loginEntityMapper.toLoginEntityCreate(login)));
    }

    @Override
    public List<Login> getAllRegister() {
        return loginJpaRepository.findAll().stream()
                .map(loginEntityMapper::toLoginCreate
                ).toList();
    }

    @Override
    public Login getloginById(UUID id) {
        return loginEntityMapper.toLogin(loginJpaRepository.getReferenceById(id));
    }

    @Override
    public Login addRole(Login login) {
        return loginEntityMapper.toLoginAdd(loginJpaRepository.save(loginEntityMapper.toLoginEntityAdd(login)));
    }
}
