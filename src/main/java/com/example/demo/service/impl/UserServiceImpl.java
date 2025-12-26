package com.example.demo.service;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {
    
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    
    public FarmServiceImpl(FarmRepository farmRepository, UserRepository userRepository) {
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public Farm createFarm(Farm farm, Long ownerId) {
        User owner = userRepository.findById(ownerId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Add validation for pH range
        if (farm.getSoilPH() != null && (farm.getSoilPH() < 3.0 || farm.getSoilPH() > 10.0)) {
            throw new IllegalArgumentException("Soil pH must be between 3.0 and 10.0");
        }
        
        farm.setOwner(owner);
        return farmRepository.save(farm);
    }
    
    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Farm not found"));
    }
    
    @Override
    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepository.findByOwnerId(ownerId);
    }
}