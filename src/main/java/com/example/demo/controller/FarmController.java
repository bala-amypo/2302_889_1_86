package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm, Authentication auth) {
        Long userId = Long.parseLong(auth.getName()); // assuming userId stored in auth
        return farmService.createFarm(farm, userId);
    }
    @GetMapping
    public List<Farm> listFarms(Authentication auth) {
        Long userId = Long.parseLong(auth.getName());
        return farmService.getFarmsByOwner(userId);
    }
    @GetMapping("/{farmId}")
    public Farm getFarm(@PathVariable Long farmId) {
        return farmService.getFarmById(farmId);
    }
}
