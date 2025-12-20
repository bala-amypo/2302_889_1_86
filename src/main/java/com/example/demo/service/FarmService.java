package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Farm;

public interface FarmService {
    Farm save(Farm farm);
    List<Farm> findAll();
    Farm findById(Long id);
    
}







// package com.example.demo.service;
// import com.example.demo.entity.Farm;
// import java.util.List;
// public interface FarmService{
//     Farm createFarm(Farm farm,Long ownerId);
//     List<Farm>getFarmsByOwner(Long ownerId);
//     Farm getFarmById(Long farmId);
// }