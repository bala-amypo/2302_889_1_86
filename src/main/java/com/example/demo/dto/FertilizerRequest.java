package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FertilizerRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String npkRatio;

    @NotBlank
    private String recommendedForCrops;
}
