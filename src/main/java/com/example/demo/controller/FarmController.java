package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService service;

    public FarmController(FarmService service) {
        this.service = service;
    }

    @PostMapping
    public Farm create(@RequestBody Farm farm) {
        return service.save(farm);
    }

    @GetMapping
    public List<Farm> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Farm get(@PathVariable Long id) {
        return service.findById(id);
    }

    
}


// package com.example.demo.controller;

// import com.example.demo.entity.Farm;
// import com.example.demo.service.FarmService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/farms")
// public class FarmController {

//     private final FarmService farmService;

//     public FarmController(FarmService farmService) {
//         this.farmService = farmService;
//     }

//     @PostMapping
//     public Farm createFarm(@RequestBody Farm farm, @RequestParam Long ownerId) {
//         return farmService.createFarm(farm, ownerId);
//     }

//     @GetMapping
//     public List<Farm> listFarms(@RequestParam Long ownerId) {
//         return farmService.getFarmsByOwner(ownerId);
//     }

//     @GetMapping("/{farmId}")
//     public Farm getFarm(@PathVariable Long farmId) {
//         return farmService.getFarmById(farmId);
//     }
// }
