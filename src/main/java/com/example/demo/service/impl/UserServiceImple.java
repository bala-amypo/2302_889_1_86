package com.example.demo.service.impl;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.framework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImple implements UserService{
    private final UserRepository userrep;
    @AutoWired
    public UserServiceImple(UserRepository userrepo){
        this.userrepo=userrepo;
    }
    @Override

}