package com.vlt.indentityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vlt.indentityservice.dto.request.PermissionRequest;
import com.vlt.indentityservice.dto.response.PermissionResponse;
import com.vlt.indentityservice.entity.Permission;
import com.vlt.indentityservice.exception.AppException;
import com.vlt.indentityservice.exception.ErrorCode;
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

    public PermissionResponse create(PermissionRequest request) {
        if (permissionRepository.existsById(request.getName())) {
            throw new AppException(ErrorCode.RESOURCE_EXISTED);
        }

        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();

        return permissions.stream()
                .map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String name) {
        var permission = permissionRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        permissionRepository.delete(permission);
    }

    public PermissionResponse getPermission(String name) {
        var permission = permissionRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
        return permissionMapper.toPermissionResponse(permission);
    }

    public PermissionResponse update(String name, PermissionRequest request) {
        var permission = permissionRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        permissionMapper.updatePermission(request, permission);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }
}
