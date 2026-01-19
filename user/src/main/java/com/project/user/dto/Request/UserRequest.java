package com.project.user.dto.Request;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class UserRequest {
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private String address;
}
