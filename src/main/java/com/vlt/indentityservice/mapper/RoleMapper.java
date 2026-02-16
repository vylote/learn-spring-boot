package com.vlt.indentityservice.mapper;

import com.vlt.indentityservice.dto.request.RoleRequest;
import com.vlt.indentityservice.dto.response.RoleResponse;
import com.vlt.indentityservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);
    RoleResponse toRoleResponse(Role role);
}
