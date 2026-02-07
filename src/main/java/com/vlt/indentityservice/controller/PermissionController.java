package com.vlt.indentityservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService = null;

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
}
