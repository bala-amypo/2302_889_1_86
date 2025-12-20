// // CatalogServiceImpl.java - com.example.demo.service.impl
// package com.example.demo.service.impl;

// import com.example.demo.entity.Crop;
// import com.example.demo.entity.Fertilizer;
// import com.example.demo.repository.CropRepository;
// import com.example.demo.repository.FertilizerRepository;
// import com.example.demo.service.CatalogService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// @Transactional
// public class CatalogServiceImpl implements CatalogService {
    
//     private final CropRepository cropRepository;
//     private final FertilizerRepository fertilizerRepository;

//     @Autowired
//     public CatalogServiceImpl(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
//         this.cropRepository = cropRepository;
//         this.fertilizerRepository = fertilizerRepository;
//     }

//     @Override
//     public Crop addCrop(Crop crop) {
//         return cropRepository.save(crop);
//     }

//     @Override
//     public Fertilizer addFertilizer(Fertilizer fertilizer) {
//         return fertilizerRepository.save(fertilizer);
//     }

//     @Override
//     public List<Crop> findSuitableCrops(Double ph, Double waterLevel, String season) {
//         return cropRepository.findSuitableCrops(ph, waterLevel, season);
//     }

//     @Override
//     public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
//         return cropNames.stream()
//             .flatMap(cropName -> fertilizerRepository.findFertilizersByRecommendedCrops(cropName).stream())
//             .distinct()
//             .collect(Collectors.toList());
//     }
// }
