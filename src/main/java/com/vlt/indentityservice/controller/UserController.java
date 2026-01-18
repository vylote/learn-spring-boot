package com.vlt.indentityservice.controller;

import com.vlt.indentityservice.dto.request.ApiResponse;
import com.vlt.indentityservice.dto.request.UserCreationRequest;
import com.vlt.indentityservice.dto.request.UserUpdateRequest;
import com.vlt.indentityservice.entity.User;
import com.vlt.indentityservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/username/{username}")
    List<User> getUsersByUsername(@PathVariable("username") String username) {
        return userService.getUsersByUsername(username);
    }

    @DeleteMapping("/delete/{id}")
    String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "User deleted";
    }

    @PutMapping("/update/{id}")
    String updateUser(@RequestBody @Valid UserUpdateRequest request, @PathVariable("id") long id) {
        userService.updateUser(request, id);
        return "User updated";
    }
}
