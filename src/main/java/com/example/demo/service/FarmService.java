// FarmService.java - com.example.demo.service
package com.example.demo.service;

import com.example.demo.entity.Farm;
import java.util.List;

public interface FarmService {
    Farm createFarm(Farm farm);
    List<Farm> getAllFarms();
    Farm getFarmById(Long id);
}
