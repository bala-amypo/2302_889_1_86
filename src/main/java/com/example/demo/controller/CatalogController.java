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
        return catalogService.addCrop(crop);
    }

    @GetMapping("/crops")
    public List<Crop> getAllCrops() {
        return catalogService.getAllCrops();
    }

    @GetMapping("/crops/{id}")
    public Crop getCrop(@PathVariable Long id) {
        return catalogService.getCrop(id);
    }

    @PutMapping("/crops/{id}")
    public Crop updateCrop(@PathVariable Long id, @RequestBody Crop crop) {
        return catalogService.updateCrop(id, crop);
    }

    @DeleteMapping("/crops/{id}")
    public void deleteCrop(@PathVariable Long id) {
        catalogService.deleteCrop(id);
    }

    @PostMapping("/fertilizers")
    public Fertilizer createFertilizer(@RequestBody Fertilizer fertilizer) {
        return catalogService.addFertilizer(fertilizer);
    }

    @GetMapping("/fertilizers")
    public List<Fertilizer> getAllFertilizers() {
        return catalogService.getAllFertilizers();
    }

    @GetMapping("/fertilizers/{id}")
    public Fertilizer getFertilizer(@PathVariable Long id) {
        return catalogService.getFertilizer(id);
    }

    @PutMapping("/fertilizers/{id}")
    public Fertilizer updateFertilizer(@PathVariable Long id, @RequestBody Fertilizer fertilizer) {
        return catalogService.updateFertilizer(id, fertilizer);
    }

    @DeleteMapping("/fertilizers/{id}")
    public void deleteFertilizer(@PathVariable Long id) {
        catalogService.deleteFertilizer(id);
    }
}
