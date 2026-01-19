package com.project.user.Service.user;

import com.project.user.Exception.ResourceNotFoundException;
import com.project.user.Mapper.UserMapper;
import com.project.user.Model.User;
import com.project.user.Repository.UserRepository;
import com.project.user.Utility.ResultDto;
import com.project.user.dto.Request.UserRequest;
import com.project.user.dto.Response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User saved = userRepository.save(userMapper.toUser(userRequest));
        return userMapper.toUserResponse(saved);
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found !!"));

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found !!"));

        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public ResultDto<UserResponse> findAllUsers() {
        List<UserResponse> list = new ArrayList<>();

        for(User user : userRepository.findAll()){
            list.add(userMapper.toUserResponse(user));
        }

        ResultDto<UserResponse> resultDto = new ResultDto<>();

        resultDto.setList(list);
        resultDto.setTotal(list.size());

        return resultDto;
    }
}
