package com.vlt.indentityservice.mapper;

import org.mapstruct.Mapper;

import com.vlt.indentityservice.dto.request.PermissionRequest;
import com.vlt.indentityservice.dto.response.PermissionResponse;
import com.vlt.indentityservice.entity.Permission;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    @Mapping(target = "name", ignore = true)
    void updatePermission(PermissionRequest request, @MappingTarget Permission permission);
}
