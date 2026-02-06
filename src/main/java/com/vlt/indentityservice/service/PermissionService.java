package com.vlt.indentityservice.service;

import org.springframework.stereotype.Service;

import com.vlt.indentityservice.dto.request.PermissionRequest;
import com.vlt.indentityservice.dto.response.PermissionResponse;
import com.vlt.indentityservice.entity.Permission;
import com.vlt.indentityservice.mapper.PermissionMapper;
import com.vlt.indentityservice.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.tPermissionResponse(permission);
    }
}
