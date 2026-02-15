package com.banco.auth.infrastructure.adapter.in.controllers.role;

import com.banco.auth.application.role.inPort.RoleUseCaseIn;
import com.banco.auth.infrastructure.adapter.in.controllers.role.dto.RoleRequestDto;
import com.banco.auth.infrastructure.adapter.in.controllers.role.dto.RoleResponseDto;
import com.banco.auth.infrastructure.adapter.in.controllers.role.mapper.RoleMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleUseCaseIn roleUseCaseIn;
    private final RoleMapper roleMapper;

    public RoleController(RoleUseCaseIn roleUseCaseIn, RoleMapper roleMapper) {
        this.roleUseCaseIn = roleUseCaseIn;
        this.roleMapper = roleMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDto> create(@Valid @RequestBody RoleRequestDto role) {
        return ResponseEntity.ok(roleMapper.toRoleResponseDto(roleUseCaseIn.createRole(roleMapper.toRole(role))));
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable UUID id) {
        return ResponseEntity.ok(roleMapper.toRoleResponseDto(roleUseCaseIn.findRoleById(id)));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleUseCaseIn.findAllRoles().stream()
                .map(roleMapper::toRoleResponseDto).toList());
    }

    @GetMapping("/enabled/{loginId}")
    public ResponseEntity<RoleResponseDto> enabledRole(@Valid @PathVariable UUID loginId) {
        return ResponseEntity.ok(roleMapper.toRoleResponseDto(roleUseCaseIn.statusTrue(loginId)));
    }

    @GetMapping("/disabled/{loginId}")
    public ResponseEntity<RoleResponseDto> disableRole(@Valid @PathVariable UUID loginId) {
        return ResponseEntity.ok(roleMapper.toRoleResponseDto(roleUseCaseIn.statusFalse(loginId)));
    }

    @PutMapping("/update")
    public ResponseEntity<RoleResponseDto> disableRole(@Valid @RequestBody RoleRequestDto role) {
        return ResponseEntity.ok(roleMapper.toRoleResponseDto(roleUseCaseIn.updateRole(roleMapper.toRole(role))));
    }
}