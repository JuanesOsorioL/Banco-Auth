package com.banco.auth.infrastructure.adapter.out.persistence.mysql.login.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "login")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "loginId", nullable = false, updatable = false)
    private UUID loginId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "clue")
    private String clue;

    @Column(name = "rolId")
    private String rolId;

    @Column(name = "state")
    private Boolean state;
}
