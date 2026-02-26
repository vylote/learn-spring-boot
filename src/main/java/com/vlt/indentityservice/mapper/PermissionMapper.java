package com.vlt.indentityservice.mapper;

import com.vlt.indentityservice.dto.request.PermissionUpdateRequest;
import org.mapstruct.Mapper;

import com.vlt.indentityservice.dto.request.PermissionCreationRequest;
import com.vlt.indentityservice.dto.response.PermissionResponse;
import com.vlt.indentityservice.entity.Permission;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    @Mapping(target = "name", ignore = true)
    void updatePermission(PermissionUpdateRequest request, @MappingTarget Permission permission);
}
