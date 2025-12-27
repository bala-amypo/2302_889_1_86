package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CropRepository cropRepository;
    private final FertilizerRepository fertilizerRepository;
    
    private static final Set<String> VALID_SEASONS = Set.of("Kharif", "Rabi", "Summer");
    private static final Pattern NPK_PATTERN = Pattern.compile("^\\d+-\\d+-\\d+$");

    @Override
    public Crop addCrop(Crop crop) {
        if (crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min must be less than or equal to PH max");
        }
        if (!VALID_SEASONS.contains(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }
        return cropRepository.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        if (!NPK_PATTERN.matcher(fertilizer.getNpkRatio()).matches()) {
            throw new BadRequestException("Invalid NPK format");
        }
        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public List<Crop> findSuitableCrops(Double ph, Double waterLevel, String season) {
        return cropRepository.findSuitableCrops(ph, waterLevel, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        return cropNames.stream()
                .flatMap(cropName -> fertilizerRepository.findByCropName(cropName).stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
