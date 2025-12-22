package com.example.accomodation.service;

import com.example.accomodation.model.Owner;
import com.example.accomodation.model.PgPlace;
import com.example.accomodation.repository.OwnerRepository;
import com.example.accomodation.repository.PgPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private PgPlaceRepository pgPlaceRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public PgPlace addPlace(PgPlace place, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        
        if (owner.getAge() < 18) {
             throw new RuntimeException("Owner must be 18+ to post accommodation.");
        }

        place.setOwner(owner);
        place.setAvailable(true); // Default to available
        place.setVisitorCount(0);
        return pgPlaceRepository.save(place);
    }

    public List<PgPlace> getAllPlacesByOwner(Long ownerId) {
        return pgPlaceRepository.findByOwnerId(ownerId);
    }

    public PgPlace changeStatus(Long id) {
        PgPlace place = pgPlaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Place not found"));
        place.setAvailable(!place.isAvailable());
        return pgPlaceRepository.save(place);
    }

    public PgPlace editPlace(PgPlace updatedPlace) {
        PgPlace existingPlace = pgPlaceRepository.findById(updatedPlace.getId())
                .orElseThrow(() -> new RuntimeException("Place not found"));
        
        // Update fields
        existingPlace.setCity(updatedPlace.getCity());
        existingPlace.setLocality(updatedPlace.getLocality());
        existingPlace.setAddress(updatedPlace.getAddress());
        existingPlace.setRent(updatedPlace.getRent());
        existingPlace.setBuiltUpArea(updatedPlace.getBuiltUpArea());
        existingPlace.setRegistrationNumber(updatedPlace.getRegistrationNumber());
        
        return pgPlaceRepository.save(existingPlace);
    }

    public void deletePlace(Long id) {
        pgPlaceRepository.deleteById(id);
    }
    
    public Owner registerOwner(Owner owner) {
        if(owner.getAge() < 18) {
            throw new RuntimeException("Owner must be at least 18 years old.");
        }
        return ownerRepository.save(owner);
    }
}