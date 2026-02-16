package com.vlt.indentityservice.controller;

import com.vlt.indentityservice.dto.response.ApiResponse;
import com.vlt.indentityservice.dto.request.UserRequest;
import com.vlt.indentityservice.dto.response.UserResponse;
import com.vlt.indentityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/my-info")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable("id") String id) {
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
    public ApiResponse<String> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .result("User deleted")
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserRequest request, @PathVariable("id") String id) {
        // --- ĐẶT TRẠM GÁC LOG Ở ĐÂY ---
        log.info("=== BẮT ĐẦU KIỂM TRA QUYỀN GỌI API UPDATE ===");
        log.info("Đang cố gắng update User có ID: {}", id);
        // Trích xuất thông tin người dùng đang gọi API từ Token
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Tên người gọi (Username): {}", authentication.getName());
        log.info("Quyền hạn đang có (Authorities): {}", authentication.getAuthorities());
        log.info("==============================================");
        // ------------------------------
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(request, id))
                .build();
    }

    @PutMapping("/update/my-info")
    public ApiResponse<UserResponse> updateMyInfo(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateMyInfo(request))
                .build();
    }
}
