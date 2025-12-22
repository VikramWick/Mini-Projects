package com.example.accomodation.controller;

import com.example.accomodation.model.Owner;
import com.example.accomodation.model.PgPlace;
import com.example.accomodation.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // Helper endpoint to register an owner
    @PostMapping("/register")
    public ResponseEntity<Owner> registerOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok(ownerService.registerOwner(owner));
    }

    // 5. Add a new PG place
    @PostMapping("/places/add/{ownerId}")
    public ResponseEntity<PgPlace> addPlace(@RequestBody PgPlace place, @PathVariable Long ownerId) {
        return ResponseEntity.ok(ownerService.addPlace(place, ownerId));
    }

    // 6. Retrieve all PG places added by the owner
    @GetMapping("/places")
    public ResponseEntity<List<PgPlace>> getAllPlaces(@RequestParam Long ownerId) {
        return ResponseEntity.ok(ownerService.getAllPlacesByOwner(ownerId));
    }

    // 7. Change the status of PG place (Available or Not Available)
    @PatchMapping("/places/{id}")
    public ResponseEntity<PgPlace> changeStatus(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.changeStatus(id));
    }

    // 8. Edit Place details
    @PutMapping("/places/edit")
    public ResponseEntity<PgPlace> editPlace(@RequestBody PgPlace place) {
        return ResponseEntity.ok(ownerService.editPlace(place));
    }

    // 9. Delete Place Details
    @DeleteMapping("/places/delete")
    public ResponseEntity<String> deletePlace(@RequestParam Long id) {
        ownerService.deletePlace(id);
        return ResponseEntity.ok("Place deleted successfully");
    }
}