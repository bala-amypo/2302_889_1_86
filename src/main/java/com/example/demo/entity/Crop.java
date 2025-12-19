package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity

@Table(name = "crops")
public class Crop {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Max(100)
    private String name;
    @NotNull
     @Min(value = 0, message = "Value must be at least 0")
    @Max(value = 100, message = "Value must not exceed 100")
     private Double suitablePHMin;
     private Double suitablePHMax;
     private Double requiredWater;
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
