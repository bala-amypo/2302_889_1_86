package com.example..demo.service;

import com.example.demo.entity.UserEntity;
import java.util.List;
import java.util.Optional;
public interface UserService{
    UserEntity saveUser(UserEntity user);
    Optional<UserEntity>getAllUserById(Long id);
    List<UserEntity> getAllUsers();
    UserEntity updateUser(Long id,UserEntity userDetails);
    void deleteUser(Long id);
    
}