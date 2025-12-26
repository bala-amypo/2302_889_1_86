// CropRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    @Query("select c from Crop c where c.suitablePHMin <= ?1 and c.suitablePHMax >= ?1 and c.season = ?2")
    List<Crop> findSuitableCrops(Double soilPH, String season);

    @Query("select c from Crop c where c.suitablePHMin <= ?1 and c.suitablePHMax >= ?1 and c.requiredWater <= ?2 and c.season = ?3")
    List<Crop> findSuitableCrops(Double soilPH, Double water, String season);
}
