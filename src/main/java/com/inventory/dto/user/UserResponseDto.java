package com.inventory.dto.user;

import com.inventory.enums.Enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private Role role;
    private LocalDateTime createdAt;
}
