// // AuthController.java - com.example.demo.controller
// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;
// import io.swagger.v3.oas.annotations.tags.Tag;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication")
// public class AuthController {
    
//     private final UserService userService;
//     private final JwtTokenProvider jwtTokenProvider;
//     private final PasswordEncoder passwordEncoder;

//     @Autowired
//     public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, 
//                          PasswordEncoder passwordEncoder) {
//         this.userService = userService;
//         this.jwtTokenProvider = jwtTokenProvider;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<User> register(@RequestBody RegisterRequest req) {
//         User user = new User();
//         user.setName(req.getName());
//         user.setEmail(req.getEmail());
//         user.setPassword(req.getPassword());
//         User registeredUser = userService.registerUser(user);
//         return ResponseEntity.ok(registeredUser);
//     }

//     @PostMapping("/login")
//     public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest req) {
//         User user = userService.findByEmail(req.getEmail());
//         if (user != null && passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//             String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getRole());
//             Map<String, String> response = new HashMap<>();
//             response.put("token", token);
//             return ResponseEntity.ok(response);
//         }
//         return ResponseEntity.badRequest().build();
//     }
// }
