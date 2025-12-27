package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String password;
}
