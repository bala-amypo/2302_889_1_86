package com.example.demo.repository;
import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    @Query("SELECT c FROM Crop c WHERE " +
           "(:soilPH BETWEEN c.suitablePHMin AND c.suitablePHMax) " +
           "AND c.requiredWater <= :waterLevel " +
           "AND c.season = :season")
    List<Crop> findSuitableCrops(@Param("soilPH") Double soilPH, 
                                 @Param("waterLevel") Double waterLevel, 
                                 @Param("season") String season);
}
