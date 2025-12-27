package com.example.demo.service;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;

import java.util.List;

public interface FarmService extends FarmRepository {

    Farm createFarm(Farm farm, Long ownerId);

    List<Farm> getFarmsByOwner(Long ownerId);

    Farm getFarmById(Long farmId);
}
