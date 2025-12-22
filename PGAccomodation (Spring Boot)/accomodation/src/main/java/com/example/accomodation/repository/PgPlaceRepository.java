package com.example.accomodation.repository;

import com.example.accomodation.model.PgPlace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PgPlaceRepository extends JpaRepository<PgPlace, Long> {
    List<PgPlace> findByCityAndIsAvailableTrue(String city);
    List<PgPlace> findByLocalityContainingIgnoreCaseAndIsAvailableTrue(String locality);
    List<PgPlace> findByOwnerId(Long ownerId);
}