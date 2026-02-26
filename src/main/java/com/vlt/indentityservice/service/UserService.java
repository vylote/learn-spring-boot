package com.vlt.indentityservice.service;

import com.vlt.indentityservice.dto.request.UserCreationRequest;
import com.vlt.indentityservice.dto.request.UserUpdateRequest;
import com.vlt.indentityservice.dto.response.UserResponse;
import com.vlt.indentityservice.entity.Role;
import com.vlt.indentityservice.entity.User;
import com.vlt.indentityservice.enums.PredefinedRole;
import com.vlt.indentityservice.exception.AppException;
import com.vlt.indentityservice.exception.ErrorCode;
import com.vlt.indentityservice.mapper.UserMapper;
import com.vlt.indentityservice.repository.RoleRepository;
import com.vlt.indentityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse create(UserCreationRequest request) {
        if (userRepository.existsUserByUsername(request.getUsername()))
            throw new AppException(ErrorCode.RESOURCE_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = mapRoles(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getMyInfo() {
        // 1. Lấy username từ context (do Filter đã giải mã token và nhét vào đây)
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("In method getUsers");
        return userMapper.toUsersResponse(userRepository.findAll());
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        log.info("In method getUser by id");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND)));
    }

    public List<UserResponse> getUsersByUsername(String username) {
        List<User> users = userRepository.findByUsernameContaining(username);
        if (users.isEmpty()) {
            throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
        }
        return userMapper.toUsersResponse(users);
    }

    public void delete(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        userRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse update(UserUpdateRequest request, String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));       

        userMapper.updateUser(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = mapRoles(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateMyInfo(UserUpdateRequest request) {
        // 1. Lấy username từ context (do Filter đã giải mã token và nhét vào đây)
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        userMapper.updateUser(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = mapRoles(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    Set<Role> mapRoles(Set<String> roleNames) {
        var roles = new HashSet<Role>();
        if (roleNames != null && !roleNames.isEmpty()) {
            roles.addAll(roleRepository.findAllById(roleNames));
        }
        roleRepository.findById(PredefinedRole.USER.name())
                .ifPresent(roles::add);

        return roles;
    }
}
