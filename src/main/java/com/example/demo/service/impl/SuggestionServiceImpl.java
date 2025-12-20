// SuggestionServiceImpl.java - com.example.demo.service.impl
package com.example.demo.service.impl;

import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {
    
    private final SuggestionRepository suggestionRepository;
    private final CatalogService catalogService;

    @Autowired
    public SuggestionServiceImpl(SuggestionRepository suggestionRepository,
                               CatalogService catalogService) {
        this.suggestionRepository = suggestionRepository;
        this.catalogService = catalogService;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        List<Crop> suitableCrops = catalogService.findSuitableCrops(7.0, 50.0, "Kharif");
        List<String> cropNames = suitableCrops.stream()
            .map(Crop::getName)
            .collect(Collectors.toList());
        
        List<Fertilizer> fertilizers = catalogService.findFertilizersForCrops(cropNames);
        List<String> fertilizerNames = fertilizers.stream()
            .map(Fertilizer::getName)
            .collect(Collectors.toList());
        
        Suggestion suggestion = new Suggestion();
        suggestion.setSuggestedCrops(String.join(",", cropNames));
        suggestion.setSuggestedFertilizers(String.join(",", fertilizerNames));
        
        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return suggestionRepository.findById(suggestionId).orElse(null);
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return suggestionRepository.findByFarmId(farmId);
    }
}
