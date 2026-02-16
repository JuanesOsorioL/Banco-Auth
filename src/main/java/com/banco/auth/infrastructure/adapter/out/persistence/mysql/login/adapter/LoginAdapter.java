package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.adapter;

import com.banco.auth.domain.login.Login;
import com.banco.auth.domain.login.outPort.LoginRepositoryOut;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.mapper.LoginEntityMapper;
import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.repository.LoginJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoginAdapter implements LoginRepositoryOut {

    private final LoginJpaRepository loginJpaRepository;
    private final LoginEntityMapper loginEntityMapper;


    public LoginAdapter(LoginJpaRepository loginJpaRepository, LoginEntityMapper loginEntityMapper) {
        this.loginJpaRepository = loginJpaRepository;
        this.loginEntityMapper = loginEntityMapper;
    }

    private Login getLogin(Login login) {
        return loginEntityMapper.toLoginCreate(loginJpaRepository.save(loginEntityMapper.toLoginEntityCreate(login)));
    }

    @Override
    public Login saveChange(Login login) {
        return getLogin(login);
    }

    @Override
    public Login register(Login login) {
        return getLogin(login);
    }

    @Override
    public List<Login> getAllRegister() {
        return loginJpaRepository.findAll().stream()
                .map(loginEntityMapper::toLoginCreate
                ).toList();
    }

    @Override
    public Optional<Login> getloginById(UUID id) {
        return loginJpaRepository.findById(id)
                .map(loginEntityMapper::toLogin);
    }

    @Override
    public Login addOrRemoveRole(Login login) {
        return loginEntityMapper.toLoginAddOrRemove(loginJpaRepository.save(loginEntityMapper.toLoginEntityAdd(login)));
    }

}
