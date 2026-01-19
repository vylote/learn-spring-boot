package com.vlt.indentityservice.controller;

import com.vlt.indentityservice.dto.response.ApiResponse;
import com.vlt.indentityservice.dto.request.UserCreationRequest;
import com.vlt.indentityservice.dto.request.UserUpdateRequest;
import com.vlt.indentityservice.dto.response.UserResponse;
import com.vlt.indentityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable("id") Long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(id))
                .build();
    }

    @GetMapping("/username/{username}")
    public ApiResponse<List<UserResponse>> getUsersByUsername(@PathVariable("username") String username) {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsersByUsername(username))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .result("User deleted")
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request, @PathVariable("id") long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(request, id))
                .build();
    }
}
