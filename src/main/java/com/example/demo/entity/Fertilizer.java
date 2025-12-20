// Fertilizer.java - com.example.demo.entity
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "fertilizers")
public class Fertilizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String npkRatio;

    @NotBlank
    @Size(max = 500)
    private String recommendedForCrops;

    // Default Constructor
    public Fertilizer() {}

    // All Args Constructor
    public Fertilizer(Long id, String name, String npkRatio, String recommendedForCrops) {
        this.id = id;
        this.name = name;
        this.npkRatio = npkRatio;
        this.recommendedForCrops = recommendedForCrops;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNpkRatio() { return npkRatio; }
    public void setNpkRatio(String npkRatio) { this.npkRatio = npkRatio; }

    public String getRecommendedForCrops() { return recommendedForCrops; }
    public void setRecommendedForCrops(String recommendedForCrops) { 
        this.recommendedForCrops = recommendedForCrops; 
    }
}
