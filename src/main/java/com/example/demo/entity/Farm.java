package com.example.demo.entity;

import com.example.demo.exception.BadRequestException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "farms")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Double soilPH;

    @NotNull
    @Column(nullable = false)
    private Double waterLevel;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String season;
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
       @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @PrePersist
    @PreUpdate
    public void validate() {
        if (soilPH == null || soilPH < 3.0 || soilPH > 10.0) {
            throw new IllegalArgumentException("Invalid soil pH");
        }
        if (season == null ||
                !(season.equals("Kharif") || season.equals("Rabi") || season.equals("Summer"))) {
            throw new BadRequestException("Invalid season");
        }
    }
}


// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Table(name = "farms")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Farm {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String name;

//     @Column(name = "soil_ph")
//     private Double soilPH;

//     @Column(name = "water_level")
//     private Double waterLevel;

//     @Column(nullable = false)
//     private String season;

//     // ✅ REQUIRED by services & tests
//     @Column(name = "owner_id", nullable = false)
//     private Long ownerId;
// }
