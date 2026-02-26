package com.vlt.indentityservice.service;

import com.vlt.indentityservice.dto.request.RoleCreationRequest;
import com.vlt.indentityservice.dto.request.RoleUpdateRequest;
import com.vlt.indentityservice.dto.response.RoleResponse;
import com.vlt.indentityservice.entity.User;
import com.vlt.indentityservice.exception.AppException;
import com.vlt.indentityservice.exception.ErrorCode;
import com.vlt.indentityservice.mapper.RoleMapper;
import com.vlt.indentityservice.repository.PermissionRepository;
import com.vlt.indentityservice.repository.RoleRepository;
import com.vlt.indentityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    UserRepository userRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleCreationRequest request) {
        if (roleRepository.existsById(request.getName()))
            throw new AppException(ErrorCode.RESOURCE_EXISTED);

        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Transactional
    public void delete(String name) {
        var role = roleRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        List<User> usersWithRoleName = userRepository.findAllByRolesName(name);
        usersWithRoleName.forEach(user ->
                user.getRoles()
                        .remove(role));
        /* (Không cần gọi userRepository.save(user) vì nhờ có @Transactional,
         Hibernate đang theo dõi và sẽ tự động lưu các thay đổi này xuống DB)*/

        roleRepository.delete(role);
    }

    @Transactional
    public RoleResponse update(RoleUpdateRequest request, String name) {
        var role = roleRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        role.setDescription(request.getDescription());

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}
