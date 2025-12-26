package com.example.demo.service;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    
    @Autowired
    private FarmService farmService;
    
    @Autowired
    private CatalogService catalogService;
    
    @Autowired
    private SuggestionRepository suggestionRepository;
    
    public SuggestionServiceImpl(FarmService farmService, CatalogService catalogService, SuggestionRepository suggestionRepository) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.suggestionRepository = suggestionRepository;
    }
    
    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        List<Crop> suitableCrops = catalogService.findSuitableCrops(farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());
        
        String cropNames = suitableCrops.stream()
            .map(Crop::getName)
            .collect(Collectors.joining(","));
        
        List<String> cropNamesList = suitableCrops.stream()
            .map(Crop::getName)
            .collect(Collectors.toList());
            
        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(cropNamesList);
        String fertilizerNames = fertilizers.stream()
            .map(Fertilizer::getName)
            .collect(Collectors.joining(","));
        
        Suggestion suggestion = new Suggestion(
            farm,
            cropNames,
            fertilizerNames
        );
            
        return suggestionRepository.save(suggestion);
    }
    
    @Override
    public Suggestion getSuggestion(Long id) {
        return suggestionRepository.findById(id).orElse(null);
    }
}