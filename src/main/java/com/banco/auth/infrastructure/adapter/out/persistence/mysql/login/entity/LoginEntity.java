package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.entity;

import com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "LOGIN")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "login_id", nullable = false, updatable = false)
    private UUID loginId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "clue")
    private String clue;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "login_role",
            joinColumns = @JoinColumn(name = "login_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<RoleEntity> roles;

    @Column(name = "state")
    private Boolean state;

}
