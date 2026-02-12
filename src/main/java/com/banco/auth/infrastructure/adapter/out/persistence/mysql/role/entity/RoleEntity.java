package com.banco.auth.infrastructure.adapter.out.persistence.mysql.role.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rolId", nullable = false, updatable = false)
    private UUID rolId;

    @Column(name = "name")
    private String rolName;

    @Column(name = "description")
    private String rolDescription;

    @Column(name = "state")
    private Boolean rolStatus;
}
