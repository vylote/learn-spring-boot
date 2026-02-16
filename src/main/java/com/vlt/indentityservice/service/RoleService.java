package com.vlt.indentityservice.service;

import com.vlt.indentityservice.dto.request.RoleRequest;
import com.vlt.indentityservice.dto.response.RoleResponse;
import com.vlt.indentityservice.entity.Role;
import com.vlt.indentityservice.exception.AppException;
import com.vlt.indentityservice.exception.ErrorCode;
import com.vlt.indentityservice.mapper.PermissionMapper;
import com.vlt.indentityservice.mapper.RoleMapper;
import com.vlt.indentityservice.repository.PermissionRepository;
import com.vlt.indentityservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        if (roleRepository.existsById(request.getName()))
            throw new AppException(ErrorCode.RESOURCE_EXISTED);

        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public void delete(String name) {
        var role = roleRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        roleRepository.delete(role);
    }
}
