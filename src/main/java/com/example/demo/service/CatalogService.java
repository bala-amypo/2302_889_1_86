// CatalogService.java - com.example.demo.service
package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import java.util.List;

public interface CatalogService {
    Crop createCrop(Crop crop);
    List<Crop> getAllCrops();
    Crop getCrop(Long id);
    Fertilizer createFertilizer(Fertilizer fertilizer);
    List<Fertilizer> getAllFertilizers();
    Fertilizer getFertilizer(Long id);
}
