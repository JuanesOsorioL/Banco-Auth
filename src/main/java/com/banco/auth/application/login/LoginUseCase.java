package com.banco.auth.application.login;

import com.banco.auth.application.login.inPort.LoginUseCaseIn;
import com.banco.auth.application.role.inPort.RoleUseCaseIn;
import com.banco.auth.domain.login.Login;
import com.banco.auth.domain.login.outPort.LoginRepositoryOut;
import com.banco.auth.domain.role.Role;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LoginUseCase implements LoginUseCaseIn {

    private final LoginRepositoryOut loginRepository;
    private final RoleUseCaseIn roleUseCaseIn;

    public LoginUseCase(LoginRepositoryOut loginRepository, RoleUseCaseIn roleUseCaseIn) {
        this.loginRepository = loginRepository;
        this.roleUseCaseIn = roleUseCaseIn;
    }

    private Login updateRoles(UUID id, Set<UUID> roles, boolean isAddOperation) {

        final Login loginExists = this.getLoginById(id);
        if (loginExists == null) {
            throw new RuntimeException("Login no encontrado con el ID: " + id);
        }

        Set<Role> filteredRoles = roles.stream()
                .map(roleUseCaseIn::findRoleById)
                .filter(role -> isAddOperation ? !loginExists.roles().contains(role) : loginExists.roles().contains(role))
                .collect(Collectors.toSet());

        Login updatedLogin = isAddOperation ? loginExists.addRoles(filteredRoles) : loginExists.removeRole(filteredRoles);

        return loginRepository.addOrRemoveRole(updatedLogin);
    }


    @Override
    public Login addRole(UUID id, Set<UUID> roles) {

        return updateRoles(id, roles, true);
    }

    @Override
    public Login removeRole(UUID id, Set<UUID> roles) {
        return updateRoles(id, roles, false);
    }

    private Login changeLoginStatus(UUID id, Function<Login, Login> statusChange) {
        //logger.debug("Changing status for role with ID: {}", id);
        Login changeStatusRole = this.getLoginById(id);

        Login updatedLogin = statusChange.apply(changeStatusRole);
        return loginRepository.saveChange(updatedLogin);
    }

    @Override
    public Login loginEnabled(UUID id) {
        return changeLoginStatus(id, Login::enabled);
    }

    @Override
    public Login loginDisabled(UUID id) {
        return changeLoginStatus(id, Login::disabled);
    }


    @Override
    public Login register(Login login) {
        Login NewLogin = Login.create(login.username(), login.email(), login.clue());
        return loginRepository.register(NewLogin);
    }

    @Override
    public List<Login> getAllRegister() {
        return loginRepository.getAllRegister();
    }


    @Override
    public Login getLoginById(UUID id) {
        return loginRepository.getloginById(id)
                .orElseThrow(() -> {
                    // logger.warn("Role not found with ID: {}", id);
                    return new RuntimeException("Login not found with id: " + id);
                });
    }

    @Override
    public Login UpdateLogin(Login login) {
        System.out.println("login = " + login);
        // logger.info("Updating role with ID: {}", role.rolId());
        // DataIntegrityViolationException
        Login existLogin = this.getLoginById(login.loginId());
        System.out.println("login = " + login);
        System.out.println("login = " + existLogin);
        try {

            Login updatedLogin = loginRepository.saveChange(Login.update(login.loginId(), login.username(), login.email(), existLogin.clue(), existLogin.roles(), existLogin.state()));
            // logger.info("Role updated successfully with ID: {}", updatedRole.rolId());
            return updatedLogin;
        } catch (DataIntegrityViolationException e) {
            // logger.error("Error updating role, name already exists: {}", role.rolName(), e);
            throw new IllegalArgumentException("El username o el email ya existe, por favor elija otro.");
        }
    }
}
