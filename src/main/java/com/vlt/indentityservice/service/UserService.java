package com.vlt.indentityservice.service;

import com.vlt.indentityservice.dto.request.UserCreationRequest;
import com.vlt.indentityservice.dto.request.UserUpdateRequest;
import com.vlt.indentityservice.dto.response.UserResponse;
import com.vlt.indentityservice.entity.User;
import com.vlt.indentityservice.exceptiion.AppException;
import com.vlt.indentityservice.exceptiion.ErrorCode;
import com.vlt.indentityservice.mapper.UserMapper;
import com.vlt.indentityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsUserByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);

        User user = userMapper.toUser(request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers() {
        return userMapper.toUsersResponse(userRepository.findAll());
    }

    public UserResponse getUser(Long id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public List<UserResponse> getUsersByUsername(String username) {
        List<User> users = userRepository.findByUsernameContaining(username);
        if (users.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return userMapper.toUsersResponse(users);
    }

    public void deleteUser(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userRepository.deleteById(id);
    }

    public UserResponse updateUser(UserUpdateRequest request, long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(request, user);

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
