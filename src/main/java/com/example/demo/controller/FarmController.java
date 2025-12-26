package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public ResponseEntity<?> createFarm(@Valid @RequestBody FarmRequest req,
                                        Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        Farm farm = Farm.builder()
                .name(req.getName())
                .soilPH(req.getSoilPH())
                .waterLevel(req.getWaterLevel())
                .season(req.getSeason())
                .build();
        Farm saved = farmService.createFarm(farm, userId);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<?> listFarms(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        List<Farm> farms = farmService.getFarmsByOwner(userId);
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarm(@PathVariable Long farmId) {
        return ResponseEntity.ok(farmService.getFarmById(farmId));
    }
}
