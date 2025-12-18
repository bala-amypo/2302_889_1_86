package com.example..demo.service;

import com.example.demo.entity.Usery;
import java.util.List;
import java.util.Optional;
public interface UserService{
    User saveUser(User user);
    Optional<User>getAllUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id,User userDetails);
    void deleteUser(Long id);

}