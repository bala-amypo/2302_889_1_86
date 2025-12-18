package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suggestions")
@RequiredArgsConstructor
public class SuggestionController {

    private final SuggestionService suggestionService;

    @PostMapping("/{farmId}")
    public Suggestion generateSuggestion(@PathVariable Long farmId) {
        return suggestionService.generateSuggestion(farmId);
    }
    @GetMapping("/{suggestionId}")
    public Suggestion getSuggestion(@PathVariable Long suggestionId) {
        return suggestionService.getSuggestion(suggestionId);
    }

    @GetMapping("/farm/{farmId}")
    public List<Suggestion> listFarmSuggestions(@PathVariable Long farmId) {
        return suggestionService.getSuggestionsByFarm(farmId);
    }
}
package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm, @RequestParam Long ownerId) {
        return farmService.createFarm(farm, ownerId);
    }

    @GetMapping
    public List<Farm> getFarmsByOwner(@RequestParam Long ownerId) {
        return farmService.getFarmsByOwner(ownerId);
    }

    @GetMapping("/{farmId}")
    public Farm getFarmById(@PathVariable Long farmId) {
        return farmService.getFarmById(farmId);
    }
}

