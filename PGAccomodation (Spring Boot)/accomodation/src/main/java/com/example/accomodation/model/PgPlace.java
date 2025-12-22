package com.example.accomodation.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PgPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String locality;
    private String address;
    private double rent;
    private String registrationNumber;
    private double builtUpArea;
    
    // true = Available, false = Occupied
    private boolean isAvailable;
    
    private int visitorCount;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}