// FarmController.java - com.example.demo.controller
package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/farms")
@Tag(name = "Farms")
public class FarmController {
    
    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody FarmRequest req, Authentication auth) {
        Farm farm = new Farm();
        farm.setName(req.getName());
        farm.setSoilPH(req.getSoilPH());
        farm.setWaterLevel(req.getWaterLevel());
        farm.setSeason(req.getSeason());
        
        String email = (String) auth.getPrincipal();
        User user = userService.findByEmail(email); // Assuming userService injected
        Farm createdFarm = farmService.createFarm(farm, user.getId());
        return ResponseEntity.ok(createdFarm);
    }

    @GetMapping
    public ResponseEntity<List<Farm>> listFarms(Authentication auth) {
        String email = (String) auth.getPrincipal();
        User user = userService.findByEmail(email);
        List<Farm> farms = farmService.getFarmsByOwner(user.getId());
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarm(@PathVariable Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        return ResponseEntity.ok(farm);
    }
}
