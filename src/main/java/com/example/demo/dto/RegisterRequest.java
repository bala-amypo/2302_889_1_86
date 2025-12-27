package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String name;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String password;
}
