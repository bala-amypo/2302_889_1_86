package com.example.demo.controller;

import com.example.demo.dto.CropRequest;
import com.example.demo.dto.FertilizerRequest;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@Tag(name = "Catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @PostMapping("/crop")
    public ResponseEntity<Crop> addCrop(@Valid @RequestBody CropRequest request, Authentication auth) {
        if (!auth.getAuthorities().toString().contains("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Crop crop = Crop.builder()
                .name(request.getName())
                .suitablePHMin(request.getSuitablePHMin())
                .suitablePHMax(request.getSuitablePHMax())
                .requiredWater(request.getRequiredWater())
                .season(request.getSeason())
                .build();
        Crop createdCrop = catalogService.addCrop(crop);
        return ResponseEntity.ok(createdCrop);
    }

    @PostMapping("/fertilizer")
    public ResponseEntity<Fertilizer> addFertilizer(@Valid @RequestBody FertilizerRequest request, Authentication auth) {
        if (!auth.getAuthorities().toString().contains("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Fertilizer fertilizer = Fertilizer.builder()
                .name(request.getName())
                .npkRatio(request.getNpkRatio())
                .recommendedForCrops(request.getRecommendedForCrops())
                .build();
        Fertilizer createdFertilizer = catalogService.addFertilizer(fertilizer);
        return ResponseEntity.ok(createdFertilizer);
    }

    @GetMapping("/crops/suitable")
    public ResponseEntity<List<Crop>> findCrops(
            @RequestParam Double ph,
            @RequestParam Double water,
            @RequestParam String season) {
        List<Crop> crops = catalogService.findSuitableCrops(ph, water, season);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/fertilizers/by-crop")
    public ResponseEntity<List<Fertilizer>> findFerts(@RequestParam String name) {
        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(List.of(name));
        return ResponseEntity.ok(fertilizers);
    }
}
