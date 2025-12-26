package com.example.demo.controller;

import com.example.demo.dto.CropRequest;
import com.example.demo.dto.FertilizerRequest;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    private boolean isAdmin(Authentication auth) {
        if (auth == null) return false;
        for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ROLE_ADMIN".equals(ga.getAuthority()) || "ADMIN".equals(ga.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    @PostMapping("/crop")
    public ResponseEntity<?> addCrop(@Valid @RequestBody CropRequest req,
                                     Authentication auth) {
        if (!isAdmin(auth)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Crop crop = Crop.builder()
                .name(req.getName())
                .suitablePHMin(req.getSuitablePHMin())
                .suitablePHMax(req.getSuitablePHMax())
                .requiredWater(req.getRequiredWater())
                .season(req.getSeason())
                .build();
        Crop saved = catalogService.addCrop(crop);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/fertilizer")
    public ResponseEntity<?> addFertilizer(@Valid @RequestBody FertilizerRequest req,
                                           Authentication auth) {
        if (!isAdmin(auth)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Fertilizer f = Fertilizer.builder()
                .name(req.getName())
                .npkRatio(req.getNpkRatio())
                .recommendedForCrops(req.getRecommendedForCrops())
                .build();
        Fertilizer saved = catalogService.addFertilizer(f);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/crops/suitable")
    public ResponseEntity<List<Crop>> findCrops(@RequestParam Double ph,
                                                @RequestParam Double water,
                                                @RequestParam String season) {
        return ResponseEntity.ok(catalogService.findSuitableCrops(ph, water, season));
    }

    @GetMapping("/fertilizers/by-crop")
    public ResponseEntity<List<Fertilizer>> findFerts(@RequestParam String name) {
        List<Fertilizer> list = catalogService.findFertilizersForCrops(
                Collections.singletonList(name));
        return ResponseEntity.ok(list);
    }
}
