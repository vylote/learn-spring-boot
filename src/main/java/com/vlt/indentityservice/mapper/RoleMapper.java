package com.vlt.indentityservice.mapper;

import com.vlt.indentityservice.dto.request.RoleCreationRequest;
import com.vlt.indentityservice.dto.request.RoleUpdateRequest;
import com.vlt.indentityservice.dto.response.RoleResponse;
import com.vlt.indentityservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreationRequest roleCreationRequest);
    RoleResponse toRoleResponse(Role role);
    @Mapping(target = "permissions", ignore = true)
    void updateRole(RoleUpdateRequest request, @MappingTarget Role role);
}
