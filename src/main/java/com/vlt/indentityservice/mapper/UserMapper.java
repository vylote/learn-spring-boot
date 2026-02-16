package com.vlt.indentityservice.mapper;

import com.vlt.indentityservice.dto.request.UserRequest;
import com.vlt.indentityservice.dto.response.UserResponse;
import com.vlt.indentityservice.entity.Role;
import com.vlt.indentityservice.entity.User;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// THÊM: unmappedTargetPolicy = ReportingPolicy.IGNORE
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest request);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToNames")
    UserResponse toUserResponse(User user);

    @Named("mapRolesToNames")
    //vi day la 1 interface, k cho phep khai bao than ham, java8 cho phep viet thang code logic vao trong interface
    default Set<String> mapRolesToNames(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "roles", ignore = true)
    void updateUser(UserRequest request, @MappingTarget User user);

    List<UserResponse> toUsersResponse(List<User> users);
}
