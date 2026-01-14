package com.inventory.service;

import com.inventory.dto.user.CreateUserRequestDto;
import com.inventory.dto.user.UpdateUserRequestDto;
import com.inventory.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(CreateUserRequestDto dto);

    UserResponseDto updateUser(Long id, UpdateUserRequestDto dto);

    void deleteUser(Long id);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();
}
