package com.project.user.Service.user;

import com.project.user.Utility.ResultDto;
import com.project.user.dto.Request.UserRequest;
import com.project.user.dto.Response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    UserResponse findByEmail(String email);

    void deleteUser(String email);

    ResultDto<UserResponse> findAllUsers();
}
