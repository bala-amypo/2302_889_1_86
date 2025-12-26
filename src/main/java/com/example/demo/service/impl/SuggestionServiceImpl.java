package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Farm;
import com.example.demo.entity.Fertilizer;
import com.example.demo.entity.Suggestion;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.SuggestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmRepository farmRepository;
    private final CatalogService catalogService;
    private final SuggestionRepository suggestionRepository;

    public SuggestionServiceImpl(FarmRepository farmRepository,
                                 CatalogService catalogService,
                                 SuggestionRepository suggestionRepository) {
        this.farmRepository = farmRepository;
        this.catalogService = catalogService;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        List<Crop> crops = catalogService.findSuitableCrops(
                farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());

        List<String> cropNames = crops.stream()
                .map(Crop::getName)
                .collect(Collectors.toList());

        List<Fertilizer> ferts = catalogService.findFertilizersForCrops(cropNames);

        String cropCsv = String.join(",",
                cropNames);

        String fertCsv = ferts.stream()
                .map(Fertilizer::getName)
                .collect(Collectors.joining(","));

        Suggestion suggestion = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(cropCsv)
                .suggestedFertilizers(fertCsv)
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
