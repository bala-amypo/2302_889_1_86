package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.User;

public interface UserService {

    User saveUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    // User updateUser(Long id, User user);

    void deleteUser(Long id);

    User findByEmail(String email);   // 👈 MUST be implemented
}

