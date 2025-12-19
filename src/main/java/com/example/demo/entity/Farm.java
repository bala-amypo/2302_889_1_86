
package com.example.demo.entity;

import jakarta.persis
import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

@Entity

@Table(name = "farms")
public class Farm {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(length = 100)
    private String name;
    @NotNull
    @Size(max=10)
    private Double soilPH;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "Water level must be >= 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Water level must be <= 100")
    private Double waterLevel ;
    @NotBlank
    private String season;
    
    
    public Farm() {
    }
    public Farm(Long id, String name, Double soilPH, Double waterLevel, String season) {
        this.id = id;
        this.name = name;
        this.soilPH = soilPH;
        this.waterLevel = waterLevel;
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
    public Double getSoilPH() {
        return soilPH;
    }
    public void setSoilPH(Double soilPH) {
        this.soilPH = soilPH;
    }
    public Double getWaterLevel() {
        return waterLevel;
    }
    public void setWaterLevel(Double waterLevel) {
        this.waterLevel = waterLevel;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
}
