package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    // ✅ EXISTING method (used by services)
    @Query("""
        SELECT c FROM Crop c
        WHERE c.suitablePHMin <= :ph
          AND c.suitablePHMax >= :ph
          AND c.requiredWater <= :water
          AND c.season = :season
    """)
    List<Crop> findSuitableCrops(@Param("ph") Double ph,
                                 @Param("water") Double water,
                                 @Param("season") String season);

    // ✅ ADD THIS method (used by tests)
    @Query("""
        SELECT c FROM Crop c
        WHERE c.suitablePHMin <= :ph
          AND c.suitablePHMax >= :ph
          AND c.season = :season
    """)
    List<Crop> findSuitableCrops(@Param("ph") Double ph,
                                 @Param("season") String season);
}
