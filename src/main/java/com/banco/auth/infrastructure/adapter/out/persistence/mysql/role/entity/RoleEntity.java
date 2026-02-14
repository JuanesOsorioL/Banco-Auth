package com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.entity;

import com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.entity.LoginEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ROLE")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rol_id", nullable = false, updatable = false)
    private UUID rolId;

    @Column(name = "name", nullable = false)
    private String rolName;

    @Column(name = "description")
    private String rolDescription;

    @ManyToMany(mappedBy = "roles")
    private Set<LoginEntity> usuarios;

    @Column(name = "state")
    private Boolean rolStatus;
}
