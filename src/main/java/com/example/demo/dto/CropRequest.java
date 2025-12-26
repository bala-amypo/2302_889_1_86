package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropRequest {

    @NotBlank
    private String name;

    @NotNull
    private Double suitablePHMin;

    @NotNull
    private Double suitablePHMax;

    @NotNull
    private Double requiredWater;

    @NotBlank
    private String season;
}
