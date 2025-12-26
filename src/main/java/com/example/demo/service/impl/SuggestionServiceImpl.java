package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Farm;
import com.example.demo.entity.Fertilizer;
import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository suggestionRepository;

    public SuggestionServiceImpl(FarmService farmService,
                                 CatalogService catalogService,
                                 SuggestionRepository suggestionRepository) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        // Get farm details
        Farm farm = farmService.getFarmById(farmId);
        
        // Find suitable crops based on farm conditions
        List<Crop> crops = catalogService.findSuitableCrops(
            farm.getSoilPH(), 
            farm.getWaterLevel(), 
            farm.getSeason()
        );
        
        // Extract crop names
        List<String> cropNames = crops.stream()
            .map(Crop::getName)
            .collect(Collectors.toList());
        
        // Find fertilizers for those crops
        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(cropNames);
        List<String> fertilizerNames = fertilizers.stream()
            .map(Fertilizer::getName)
            .collect(Collectors.toList());
        
        // Create suggestion
        Suggestion suggestion = Suggestion.builder()
            .farm(farm.getId())
            .suggestedCrops(String.join(", ", cropNames))
            .suggestedFertilizers(String.join(", ", fertilizerNames))
            .build();
            
        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return suggestionRepository.findById(id).orElse(null);
    }
}
