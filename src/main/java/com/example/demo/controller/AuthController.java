package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Object register(@RequestBody Object user) {
        return userService.register((com.example.demo.entity.User) user);
    }

    @PostMapping("/login")
    public Object login(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}
