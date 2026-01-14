package com.inventory.mapper;

import com.inventory.dto.user.CreateUserRequestDto;
import com.inventory.dto.user.UserResponseDto;
import com.inventory.entity.User;

public class UserMapper {

    public static User toCreateEntity(CreateUserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
