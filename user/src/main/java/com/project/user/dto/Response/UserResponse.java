package com.project.user.dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({
        "userId",
        "name",
        "email",
        "password",
        "address",
        "createdAt",
        "updatedAt"
})
public class UserResponse {
    private UUID userId;
    private String name;
    private String email;
    private String address;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
