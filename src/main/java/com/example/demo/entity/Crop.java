package com.example.demo.entity;

import com.example.demo.exception.BadRequestException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "crops")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Double suitablePHMin;

    @NotNull
    @Column(nullable = false)
    private Double suitablePHMax;

    @NotNull
    @Column(nullable = false)
    private Double requiredWater;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String season;

    @PrePersist
    @PreUpdate
    public void validate() {
        if (suitablePHMin == null || suitablePHMax == null ||
                suitablePHMin < 2.0 || suitablePHMax > 10.0 ||
                suitablePHMin > suitablePHMax) {
            throw new BadRequestException("PH min must be <= max");
        }
        if (season == null ||
                !(season.equals("Kharif") || season.equals("Rabi") || season.equals("Summer"))) {
            throw new BadRequestException("Invalid season");
        }
    }
}
