package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}










// package com.example.demo.repository;

// import com.example.demo.entity.Farm;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface FarmRepository extends JpaRepository<Farm, Long> {

//     List<Farm> findByOwnerId(Long ownerId);
// }
