package com.vlt.indentityservice.mapper;

import com.vlt.indentityservice.dto.request.UserCreationRequest;
import com.vlt.indentityservice.dto.request.UserUpdateRequest;
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
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToNames")
    UserResponse toUserResponse(User user);

    @Named("mapRolesToNames")
    default Set<String> mapRolesToNames(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    void updateUser(UserUpdateRequest request, @MappingTarget User user);

    List<UserResponse> toUsersResponse(List<User> users);
}
