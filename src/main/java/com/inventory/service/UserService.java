package com.inventory.service;

import com.inventory.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user, String performedBy);

    User updateUser(Long id, User updatedUser, String performedBy);

    void deleteUser(Long id, String performedBy);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();
}
