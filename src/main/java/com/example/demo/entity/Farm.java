
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "farms")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private Double soilPH;

    @NotNull
    private Double waterLevel;

    @NotBlank
    private String season;

    public Farm() {}
    public Farm(Long id, String name, Double soilPH, Double waterLevel, String season) {
        this.id = id;
        this.name = name;
        this.soilPH = soilPH;
        this.waterLevel = waterLevel;
        this.season = season;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getSoilPH() { return soilPH; }
    public void setSoilPH(Double soilPH) { this.soilPH = soilPH; }

    public Double getWaterLevel() { return waterLevel; }
    public void setWaterLevel(Double waterLevel) { this.waterLevel = waterLevel; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
}
