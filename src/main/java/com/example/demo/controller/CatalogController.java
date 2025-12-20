// // CatalogController.java - com.example.demo.controller
// package com.example.demo.controller;

// import com.example.demo.dto.CropRequest;
// import com.example.demo.dto.FertilizerRequest;
// import com.example.demo.entity.Crop;
// import com.example.demo.entity.Fertilizer;
// import com.example.demo.service.CatalogService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
// import org.springframework.web.bind.annotation.*;
// import io.swagger.v3.oas.annotations.tags.Tag;

// import java.util.List;

// @RestController
// @RequestMapping("/catalog")
// @Tag(name = "Catalog")
// public class CatalogController {
    
//     private final CatalogService catalogService;

//     @Autowired
//     public CatalogController(CatalogService catalogService) {
//         this.catalogService = catalogService;
//     }

//     @PostMapping("/crop")
//     public ResponseEntity<Crop> addCrop(@RequestBody CropRequest req, Authentication auth) {
//         // ADMIN check
//         String role = (String) auth.getAuthorities().iterator().next().getAuthority();
//         if (!role.equals("ROLE_ADMIN")) {
//             return ResponseEntity.status(403).build();
//         }
        
//         Crop crop = new Crop();
//         crop.setName(req.getName());
//         crop.setSuitablePHMin(req.getSuitablePHMin());
//         crop.setSuitablePHMax(req.getSuitablePHMax());
//         crop.setRequiredWater(req.getRequiredWater());
//         crop.setSeason(req.getSeason());
        
//         Crop createdCrop = catalogService.addCrop(crop);
//         return ResponseEntity.ok(createdCrop);
//     }

//     @PostMapping("/fertilizer")
//     public ResponseEntity<Fertilizer> addFertilizer(@RequestBody FertilizerRequest req, Authentication auth) {
//         // ADMIN check
//         String role = (String) auth.getAuthorities().iterator().next().getAuthority();
//         if (!role.equals("ROLE_ADMIN")) {
//             return ResponseEntity.status(403).build();
//         }
        
//         Fertilizer fertilizer = new Fertilizer();
//         fertilizer.setName(req.getName());
//         fertilizer.setNpkRatio(req.getNpkRatio());
//         fertilizer.setRecommendedForCrops(req.getRecommendedForCrops());
        
//         Fertilizer createdFertilizer = catalogService.addFertilizer(fertilizer);
//         return ResponseEntity.ok(createdFertilizer);
//     }

//     @GetMapping("/crops/suitable")
//     public ResponseEntity<List<Crop>> getSuitableCrops(
//             @RequestParam Double ph,
//             @RequestParam Double water,
//             @RequestParam String season) {
//         List<Crop> crops = catalogService.findSuitableCrops(ph, water, season);
//         return ResponseEntity.ok(crops);
//     }

//     @GetMapping("/fertilizers/by-crop")
//     public ResponseEntity<List<Fertilizer>> getFertilizersByCrop(@RequestParam String name) {
//         List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(List.of(name));
//         return ResponseEntity.ok(fertilizers);
//     }
// }
