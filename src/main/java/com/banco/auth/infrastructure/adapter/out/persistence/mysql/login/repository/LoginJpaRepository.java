package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.repository;

import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginJpaRepository extends JpaRepository<LoginEntity, UUID> {
}
