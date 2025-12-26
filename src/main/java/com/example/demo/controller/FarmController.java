package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {
    
    @Autowired
    private FarmService farmService;
    
    @Autowired
    private UserService userService;
    
    public FarmController(FarmService farmService, UserService userService) {
        this.farmService = farmService;
        this.userService = userService;
    }
    
    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody FarmRequest request, Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        Farm farm = new Farm();
        farm.setName(request.getName());
        farm.setSoilPH(request.getSoilPH());
        farm.setWaterLevel(request.getWaterLevel());
        farm.setSeason(request.getSeason());
        Farm saved = farmService.createFarm(farm, userId);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping
    public ResponseEntity<List<Farm>> listFarms(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        List<Farm> farms = farmService.getFarmsByOwner(userId);
        return ResponseEntity.ok(farms);
    }
}