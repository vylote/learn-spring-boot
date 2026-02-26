package com.vlt.indentityservice.controller;

import com.vlt.indentityservice.dto.request.RoleCreationRequest;
import com.vlt.indentityservice.dto.request.RoleUpdateRequest;
import com.vlt.indentityservice.dto.response.ApiResponse;
import com.vlt.indentityservice.dto.response.RoleResponse;
import com.vlt.indentityservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping("/create")
    public ApiResponse<RoleResponse> create(@RequestBody RoleCreationRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> delete(@PathVariable("id") String name) {
        roleService.delete(name);
        return ApiResponse.<String>builder()
                .result("role deleted")
                .build();
    }

    @PutMapping("/update/{name}")
    public ApiResponse<RoleResponse> update(@PathVariable("name") String name, @RequestBody RoleUpdateRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.update(request, name))
                .build();
    }
}
