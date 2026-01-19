package com.vlt.indentityservice.mapper;

import com.vlt.indentityservice.dto.request.UserCreationRequest;
import com.vlt.indentityservice.dto.request.UserUpdateRequest;
import com.vlt.indentityservice.dto.response.UserResponse;
import com.vlt.indentityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(UserUpdateRequest request, @MappingTarget User user);

    List<UserResponse> toUsersResponse(List<User> users);
}
