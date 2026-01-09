package com.inventory.service;

import com.inventory.dto.user.CreateUserRequestDto;
import com.inventory.dto.user.UpdateUserRequestDto;
import com.inventory.dto.user.UserResponseDto;
import com.inventory.enums.Enums.Role;
import com.inventory.enums.Enums.AuditAction;
import com.inventory.entity.User;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.UserMapper;
import com.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    @Override
    public UserResponseDto createUser(CreateUserRequestDto dto) {
        User user = UserMapper.toCreateEntity(dto);
        user.setRole(Role.USER);

        User saved = userRepository.save(user);

        auditLogService.log(AuditAction.CREATE, "User", saved.getId(), getCurrentUserId(), "Created user: " + saved.getUsername());

        return UserMapper.toResponse(saved);
    }

    @Override
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        existing.setUsername(dto.getUsername());
        existing.setUpdatedAt(LocalDateTime.now());

        User saved = userRepository.save(existing);
        auditLogService.log(AuditAction.UPDATE, "User", saved.getId(), getCurrentUserId(), "Updated user: " + saved.getUsername());
        return UserMapper.toResponse(saved);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(user);
        auditLogService.log(AuditAction.DELETE, "User", id, getCurrentUserId(), "Deleted user");
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    private User getCurrentUserId() {
        // temp until JWT
        User user = new User();
        user.setId(1L);
        return user;
    }
}
