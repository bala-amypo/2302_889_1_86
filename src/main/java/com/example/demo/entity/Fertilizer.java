package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double soilPH;
    private Double waterLevel;
    private String season;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    
    public Farm() {}
    
    public Farm(String name, Double soilPH, Double waterLevel, String season, User owner) {
        this.name = name;
        this.soilPH = soilPH;
        this.waterLevel = waterLevel;
        this.season = season;
        this.owner = owner;
    }
    
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
    
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}