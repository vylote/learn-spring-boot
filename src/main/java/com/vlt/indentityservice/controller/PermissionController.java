package com.vlt.indentityservice.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.vlt.indentityservice.dto.request.PermissionRequest;
import com.vlt.indentityservice.dto.response.ApiResponse;
import com.vlt.indentityservice.dto.response.PermissionResponse;
import com.vlt.indentityservice.service.PermissionService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping("/create")
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }  

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/delete/{name}")
    public ApiResponse<String> delete(@PathVariable String name) {
        permissionService.delete(name);
        return ApiResponse.<String>builder()
                .result("Permission deleted")
                .build();
    }

    @GetMapping("/{name}")
    public ApiResponse<PermissionResponse> getPermission(@PathVariable String name) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.getPermission(name))
                .build();
    }

    @PutMapping("/update/{name}")
    public ApiResponse<PermissionResponse> update(@PathVariable String name, @RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.update(name, request))
                .build();
    }
}
