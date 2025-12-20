package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // @Override
    // public User updateUser(Long id, User user) {
    //     User existingUser = userRepository.findById(id).orElse(null);
    //     if (existingUser != null) {
    //         existingUser.setName(user.getName());
    //         existingUser.setEmail(user.getEmail());
    //         return userRepository.save(existingUser);
    //     }
    //     return null;
    // }

    // @Override
    // public void deleteUser(Long id) {
    //     userRepository.deleteById(id);
    // }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
