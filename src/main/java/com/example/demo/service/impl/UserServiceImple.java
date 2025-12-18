package com.example.demo.service.impl;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.framework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImple implements UserService{
    private final UserRepo userRepository;
    @AutoWired
}