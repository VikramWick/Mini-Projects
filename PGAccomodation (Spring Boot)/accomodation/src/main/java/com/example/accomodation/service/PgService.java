package com.example.accomodation.service;

import com.example.accomodation.model.Owner;
import com.example.accomodation.model.PgPlace;
import com.example.accomodation.repository.PgPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PgService {

    @Autowired
    private PgPlaceRepository pgPlaceRepository;

    public List<PgPlace> getPlacesByCity(String city) {
        return pgPlaceRepository.findByCityAndIsAvailableTrue(city);
    }

    public List<PgPlace> getPlacesByLocality(String locality) {
        return pgPlaceRepository.findByLocalityContainingIgnoreCaseAndIsAvailableTrue(locality);
    }

    public PgPlace getPgDetails(Long id) {
        Optional<PgPlace> placeOpt = pgPlaceRepository.findById(id);
        if (placeOpt.isPresent()) {
            PgPlace place = placeOpt.get();
            // Increment visitor count
            place.setVisitorCount(place.getVisitorCount() + 1);
            pgPlaceRepository.save(place);
            return place;
        }
        throw new RuntimeException("PG Place not found with ID: " + id);
    }

    public Owner getOwnerDetails(Long placeId) {
        PgPlace place = getPgDetails(placeId);
        if (place.isAvailable()) {
            return place.getOwner();
        } else {
            throw new RuntimeException("Place is occupied. Owner contact details are hidden.");
        }
    }
}