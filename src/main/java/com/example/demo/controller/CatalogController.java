// CatalogController.java - com.example.demo.controller
package com.example.demo.controller;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/crops")
    public Crop createCrop(@RequestBody Crop crop) {
        return catalogService.createCrop(crop);
    }

    @GetMapping("/crops")
    public List<Crop> getAllCrops() {
        return catalogService.getAllCrops();
    }

    @GetMapping("/crops/{id}")
    public Crop getCrop(@PathVariable Long id) {
        return catalogService.getCrop(id);
    }

    @PostMapping("/fertilizers")
    public Fertilizer createFertilizer(@RequestBody Fertilizer fertilizer) {
        return catalogService.createFertilizer(fertilizer);
    }

    @GetMapping("/fertilizers")
    public List<Fertilizer> getAllFertilizers() {
        return catalogService.getAllFertilizers();
    }

    @GetMapping("/fertilizers/{id}")
    public Fertilizer getFertilizer(@PathVariable Long id) {
        return catalogService.getFertilizer(id);
    }
}
