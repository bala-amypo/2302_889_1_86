package com.example.demo.repository;

import com.example.demo.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    
    // TEST EXPECTS: findByFarmId(Long) - use @Query to fix JPA naming
    @Query("SELECT s FROM Suggestion s WHERE s.farm = :farmId")
    List<Suggestion> findByFarmId(@Param("farmId") Long farmId);
    
    Optional<Suggestion> findById(Long id);
}
