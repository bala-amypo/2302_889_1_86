package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmRequest {
    @NotBlank
    private String name;
    
    @NotNull
    private Double soilPH;
    
    @NotNull
    private Double waterLevel;
    
    @NotBlank
    private String season;
}
