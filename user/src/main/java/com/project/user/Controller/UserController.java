package com.project.user.Controller;

import com.project.user.Service.user.UserService;
import com.project.user.Utility.ResponseBuilder;
import com.project.user.Utility.ResponseStructure;
import com.project.user.Utility.ResultDto;
import com.project.user.dto.Request.UserRequest;
import com.project.user.dto.Response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/user")
    public ResponseEntity<ResponseStructure<UserResponse>> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "User Created !!", userResponse);
    }

    @GetMapping("/v1/user")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(@RequestParam String email){
        UserResponse userResponse = userService.findByEmail(email);
        return ResponseBuilder.success(HttpStatus.OK, "User Fetched !!", userResponse);
    }

    @DeleteMapping("/v1/user")
    public ResponseEntity<ResponseStructure<String>> safeDeleteUser(@RequestParam String email){
        userService.deleteUser(email);
        return ResponseBuilder.success(HttpStatus.OK, "User Deleted !!", email);
    }

    @GetMapping("/v1/user/all")
    public ResponseEntity<ResponseStructure<ResultDto<UserResponse>>> findAllUsers(){
        ResultDto<UserResponse> resultDto = userService.findAllUsers();
        return ResponseBuilder.success(HttpStatus.OK, "All Users Fetched !!", resultDto);
    }
}
