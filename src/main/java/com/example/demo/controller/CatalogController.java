package com.example.demo.controller;

import com.example.demo.dto.CropRequest;
import com.example.demo.dto.FertilizerRequest;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    
    @Autowired
    private CatalogService catalogService;
    
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
    
    @PostMapping("/crops")
    public ResponseEntity<Crop> addCrop(@RequestBody CropRequest request, Authentication auth) {
        if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        Crop crop = new Crop(
            request.getName(),
            request.getSuitablePHMin(),
            request.getSuitablePHMax(),
            request.getRequiredWater(),
            request.getSeason()
        );
        Crop saved = catalogService.addCrop(crop);
        return ResponseEntity.ok(saved);
    }
    
    @PostMapping("/fertilizers")
    public ResponseEntity<Fertilizer> addFertilizer(@RequestBody FertilizerRequest request, Authentication auth) {
        if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        Fertilizer fertilizer = new Fertilizer(
            request.getName(),
            request.getNpkRatio(),
            request.getRecommendedForCrops()
        );
        Fertilizer saved = catalogService.addFertilizer(fertilizer);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/crops")
    public ResponseEntity<List<Crop>> findCrops(@RequestParam Double ph, @RequestParam Double waterLevel, @RequestParam String season) {
        List<Crop> crops = catalogService.findSuitableCrops(ph, waterLevel, season);
        return ResponseEntity.ok(crops);
    }
    
    @GetMapping("/fertilizers")
    public ResponseEntity<List<Fertilizer>> findFerts(@RequestParam String crop) {
        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(List.of(crop));
        return ResponseEntity.ok(fertilizers);
    }
}