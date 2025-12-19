
// package com.example.demo.service.impl;

// import com.example.demo.entity.Farm;
// import com.example.demo.entity.User;
// import com.example.demo.repository.FarmRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.FarmService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;
// import java.util.List;
// import static org.springframework.http.HttpStatus.NOT_FOUND;

// @Service
// @RequiredArgsConstructor
// public class FarmServiceImpl implements FarmService {

//     private final FarmRepository farmRepository;
//     private final UserRepository userRepository;

//     @Override
//     public Farm createFarm(Farm farm, Long ownerId) {
//         User owner = userRepository.findById(ownerId)
//                 .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
//         farm.setOwner(owner);
//         return farmRepository.save(farm);
//     }

//     @Override
//     public List<Farm> getFarmsByOwner(Long ownerId){
//         return farmRepository.findByOwnerId(ownerId);
//     }

//     @Override
//     public Farm getFarmById(Long farmId) {
//         return farmRepository.findById(farmId)
//                 .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
//     }
// }

