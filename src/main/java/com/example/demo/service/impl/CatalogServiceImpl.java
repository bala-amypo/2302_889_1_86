// CatalogServiceImpl.java
package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepository;
    private final FertilizerRepository fertilizerRepository;
    private static final Pattern NPK_PATTERN = Pattern.compile("\\d+-\\d+-\\d+");

    public CatalogServiceImpl(CropRepository cropRepository,
                              FertilizerRepository fertilizerRepository) {
        this.cropRepository = cropRepository;
        this.fertilizerRepository = fertilizerRepository;
    }

    @Override
    public Crop addCrop(Crop crop) {
        if (crop.getSuitablePHMin() != null && crop.getSuitablePHMax() != null
                && crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min");
        }
        if (!ValidationUtil.validSeason(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }
        return cropRepository.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        if (fertilizer.getNpkRatio() == null ||
                !NPK_PATTERN.matcher(fertilizer.getNpkRatio()).matches()) {
            throw new BadRequestException("NPK");
        }
        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public List<Crop> findSuitableCrops(Double soilPH, Double waterLevel, String season) {
        return cropRepository.findSuitableCrops(soilPH, waterLevel, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        if (cropNames == null || cropNames.isEmpty()) {
            return Collections.emptyList();
        }
        // very simple: query by first crop name
        return fertilizerRepository.findByCropName(cropNames.get(0).toLowerCase());
    }
}
