package com.vlt.indentityservice.service;

import com.vlt.indentityservice.dto.request.UserCreationRequest;
import com.vlt.indentityservice.dto.request.UserUpdateRequest;
import com.vlt.indentityservice.entity.User;
import com.vlt.indentityservice.exceptiion.AppException;
import com.vlt.indentityservice.exceptiion.ErrorCode;
import com.vlt.indentityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();
        if (userRepository.existsUserByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITED);
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public List<User> getUsersByUsername(String username) {
        List<User> users = userRepository.findByUsernameContaining(username);
        if (users.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return users;
    }

    public void deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
    }

    public void updateUser(UserUpdateRequest request, long id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).get();

            user.setPassword(request.getPassword());
            user.setLastName(request.getLastName());
            user.setFirstName(request.getFirstName());
            user.setDob(request.getDob());
            userRepository.save(user);
        } else {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
