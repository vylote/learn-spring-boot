package com.vlt.indentityservice.controller;

import com.vlt.indentityservice.dto.request.RoleRequest;
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
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
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
}
