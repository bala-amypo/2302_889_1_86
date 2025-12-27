package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        User registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
