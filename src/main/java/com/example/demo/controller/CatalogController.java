package com.example.demo.controller;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/crop")
    public Crop addCrop(@RequestBody Crop crop) {
        return catalogService.addCrop(crop);
    }
    @PostMapping("/fertilizer")
    public Fertilizer addFertilizer(@RequestBody Fertilizer fertilizer) {
        return catalogService.addFertilizer(fertilizer);
    }

    @GetMapping("/crops/suitable")
    public List<Crop> getSuitableCrops(
            @RequestParam Double ph,
            @RequestParam Double water,
            @RequestParam String season
    ) {
        return catalogService.findSuitableCrops(ph, water, season);
    }

    // GET fertilizers by crop
    @GetMapping("/fertilizers/by-crop")
    public List<Fertilizer> getFertilizersByCrop(@RequestParam String name) {
        List<String> cropNames = Arrays.asList(name.split(","));
        return catalogService.findFertilizersForCrops(cropNames);
    }
}
