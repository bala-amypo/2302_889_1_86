package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepository;
    private final FertilizerRepository fertilizerRepository;

    public CatalogServiceImpl(CropRepository cropRepository,
                              FertilizerRepository fertilizerRepository) {
        this.cropRepository = cropRepository;
        this.fertilizerRepository = fertilizerRepository;
    }

    @Override
    public Crop addCrop(Crop crop) {
        if (crop.getSuitablePHMin() == null || crop.getSuitablePHMax() == null
                || crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min must be <= max");
        }
        if (!ValidationUtil.validSeason(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }
        return cropRepository.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        if (fertilizer.getNpkRatio() == null ||
                !fertilizer.getNpkRatio().matches("\\d+-\\d+-\\d+")) {
            throw new BadRequestException("Invalid NPK format");
        }
        return fertilizerRepository.save(fertilizer);
    }
    @Override
    public List<Crop> findSuitableCrops(Double ph, Double waterLevel, String season) {
        return cropRepository.findSuitableCrops(ph, waterLevel, season);
    }
    @Override
    public List<Crop> findSuitableCrops(Double ph, String season) {
        return cropRepository.findSuitableCrops(ph, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        if (cropNames == null || cropNames.isEmpty()) {
            return List.of();
        }
        Set<Fertilizer> result = new LinkedHashSet<>();
        for (String name : cropNames) {
            result.addAll(fertilizerRepository.findByCropName(name));
        }
        return new ArrayList<>(result);
    }
}
