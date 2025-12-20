// CatalogServiceImpl.java - com.example.demo.service.impl
package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    
    private final CropRepository cropRepository;
    private final FertilizerRepository fertilizerRepository;

    @Autowired
    public CatalogServiceImpl(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
        this.cropRepository = cropRepository;
        this.fertilizerRepository = fertilizerRepository;
    }

    @Override
    public Crop createCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    @Override
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    @Override
    public Crop getCrop(Long id) {
        return cropRepository.findById(id).orElse(null);
    }

    @Override
    public Fertilizer createFertilizer(Fertilizer fertilizer) {
        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public List<Fertilizer> getAllFertilizers() {
        return fertilizerRepository.findAll();
    }

    @Override
    public Fertilizer getFertilizer(Long id) {
        return fertilizerRepository.findById(id).orElse(null);
    }
}
