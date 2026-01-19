package com.project.user.Mapper;

import com.project.user.Model.User;
import com.project.user.dto.Request.UserRequest;
import com.project.user.dto.Response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);
}
