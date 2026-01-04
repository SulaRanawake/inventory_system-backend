package com.inventory.service;

import com.inventory.entity.AuditAction;
import com.inventory.entity.User;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    @Override
    public User createUser(User user, String performedBy) {
        User saved = userRepository.save(user);
        auditLogService.log(AuditAction.CREATE, "User", saved.getId(), performedBy, "Created user: " + saved.getUsername());
        return saved;
    }

    @Override
    public User updateUser(Long id, User updatedUser, String performedBy) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        existing.setUsername(updatedUser.getUsername());
        existing.setRole(updatedUser.getRole());
        existing.setUpdatedAt(updatedUser.getUpdatedAt());
        User saved = userRepository.save(existing);
        auditLogService.log(AuditAction.UPDATE, "User", saved.getId(), performedBy, "Updated user: " + saved.getUsername());
        return saved;
    }

    @Override
    public void deleteUser(Long id, String performedBy) {
        userRepository.deleteById(id);
        auditLogService.log(AuditAction.DELETE, "User", id, performedBy, "Deleted user");
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
