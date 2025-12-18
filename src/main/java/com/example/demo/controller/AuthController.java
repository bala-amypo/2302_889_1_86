package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncprder;
import org.springframework.web.bind.annotataion.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController{
    @Autowired
    private UserService userservice;

    @Autowired
    private BCryptPasswordEncorder passwordEncorder;
    @PostMapping("/register")
    public User register(@ReuestBody User user){
        user.setPassword(passwordEncorder.encorde(user.getPassword()));
        return userService.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User request){
        User user=userService.findByEmail(request.getEmail());
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            R
        }
    }

}