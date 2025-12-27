package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {
    private final FarmRepository farmRepository;
    private final CatalogService catalogService;
    private final SuggestionRepository suggestionRepository;

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new RuntimeException("Farm not found"));
        
        List suitableCrops = catalogService.findSuitableCrops(
                farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());
        List<String> cropNames = suitableCrops.stream()
                .map(crop -> ((Crop) crop).getName())
                .collect(Collectors.toList());
        
        List fertilizers = catalogService.findFertilizersForCrops(cropNames);
        List<String> fertilizerNames = fertilizers.stream()
                .map(fert -> ((Fertilizer) fert).getName())
                .collect(Collectors.toList());
        
        Suggestion suggestion = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(String.join(",", cropNames))
                .suggestedFertilizers(String.join(",", fertilizerNames))
                .build();
        
        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new RuntimeException("Suggestion not found"));
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return suggestionRepository.findByFarmId(farmId);
    }
}
