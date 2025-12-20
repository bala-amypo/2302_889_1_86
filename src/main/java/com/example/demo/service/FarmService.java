// FarmService.java - com.example.demo.service
package com.example.demo.service;

import com.example.demo.entity.Farm;
import java.util.List;

public interface FarmService {
    Farm createFarm(Farm farm, Long ownerId);
    List<Farm> getFarmsByOwner(Long ownerId);
    Farm getFarmById(Long farmId);
}



// package com.example.demo.service;
// import com.example.demo.entity.Farm;
// import java.util.List;
// public interface FarmService{
//     Farm createFarm(Farm farm,Long ownerId);
//     List<Farm>getFarmsByOwner(Long ownerId);
//     Farm getFarmById(Long farmId);
// }