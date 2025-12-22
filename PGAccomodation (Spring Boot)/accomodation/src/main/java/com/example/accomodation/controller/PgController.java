package com.example.accomodation.controller;

import com.example.accomodation.model.Owner;
import com.example.accomodation.model.PgPlace;
import com.example.accomodation.service.PgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg")
public class PgController {

    @Autowired
    private PgService pgService;

    // 1. Retrieve available PG places in a specific city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<PgPlace>> getPgByCity(@PathVariable String city) {
        return ResponseEntity.ok(pgService.getPlacesByCity(city));
    }

    // 2. List out available PG places in a specific locality
    @GetMapping("/{locality}")
    public ResponseEntity<List<PgPlace>> getPgByLocality(@PathVariable String locality) {
        return ResponseEntity.ok(pgService.getPlacesByLocality(locality));
    }

    // 3. Retrieve a specific PG place detail by id
    @GetMapping("/details/{id}")
    public ResponseEntity<PgPlace> getPgDetails(@PathVariable Long id) {
        return ResponseEntity.ok(pgService.getPgDetails(id));
    }

    // 4. Retrieve the owner details of a specific PG
    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getOwnerDetails(@PathVariable Long id) {
        // Logic handled in service: throws exception if occupied
        return ResponseEntity.ok(pgService.getOwnerDetails(id));
    }
}