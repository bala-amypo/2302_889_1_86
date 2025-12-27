package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
@Tag(name = "Farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Farm> createFarm(@Valid @RequestBody FarmRequest request, Authentication auth) {
        Long userId = Long.valueOf(auth.getPrincipal().toString());
        Farm farm = Farm.builder()
                .name(request.getName())
                .soilPH(request.getSoilPH())
                .waterLevel(request.getWaterLevel())
                .season(request.getSeason())
                .build();
        Farm createdFarm = farmService.createFarm(farm, userId);
        return ResponseEntity.ok(createdFarm);
    }

    @GetMapping
    public ResponseEntity<List<Farm>> listFarms(Authentication auth) {
        Long userId = Long.valueOf(auth.getPrincipal().toString());
        List<Farm> farms = farmService.getFarmsByOwner(userId);
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarm(@PathVariable Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        return ResponseEntity.ok(farm);
    }
}
