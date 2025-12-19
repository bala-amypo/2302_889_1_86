
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

@Entity

@Table(name = "crops")
public class Crop {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
     @Size(max = 100)
    private String name;
    @NotNull
     @DecimalMin(value = "2.0", inclusive = true, message = "Minimum pH must be >= 2.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Minimum pH must be <= 10.0")
    
     private Double suitablePHMin;
     @NotNull
    @DecimalMin(value = "2.0", inclusive = true, message = "Maximum pH must be >= 2.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Maximum pH must be <= 10.0")
     private Double suitablePHMax;
     
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "Water requirement must be >= 0")
    @DecimalMax(value = "1000.0", inclusive = true, message = "Water requirement must be <= 1000")
     private Double requiredWater;
     @NotBlank
     private String season;

     public Crop() {
     }

     public Crop(Long id, String name, Double suitablePHMin, Double suitablePHMax, Double requiredWater, String season) {
        this.id = id;
        this.name = name;
        this.suitablePHMin = suitablePHMin;
        this.suitablePHMax = suitablePHMax;
        this.requiredWater = requiredWater;
        this.season = season;
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public Double getSuitablePHMin() {
         return suitablePHMin;
     }

     public void setSuitablePHMin(Double suitablePHMin) {
         this.suitablePHMin = suitablePHMin;
     }

     public Double getSuitablePHMax() {
         return suitablePHMax;
     }

     public void setSuitablePHMax(Double suitablePHMax) {
         this.suitablePHMax = suitablePHMax;
     }

     public Double getRequiredWater() {
         return requiredWater;
     }

     public void setRequiredWater(Double requiredWater) {
         this.requiredWater = requiredWater;
     }

     public String getSeason() {
         return season;
     }

     public void setSeason(String season) {
         this.season = season;
     }

     
     
     
    
}
