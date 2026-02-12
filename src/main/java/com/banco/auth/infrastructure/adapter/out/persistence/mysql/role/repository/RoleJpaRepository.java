package com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.repository;

import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, UUID> {
    //RoleEntity findByRolId(String id);

}
