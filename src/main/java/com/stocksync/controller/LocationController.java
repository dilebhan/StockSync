package com.stocksync.controller;

import com.stocksync.dto.LocationDTO;
import com.stocksync.entity.Location;
import com.stocksync.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LocationDTO dto) {
        Location location = locationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody LocationDTO dto) {
        return locationService.update(id, dto)
            .<ResponseEntity<?>>map(location -> ResponseEntity.ok().body(location))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (locationService.delete(id)) {
            return ResponseEntity.ok().body("Location deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id) {
        return locationService.get(id)
            .<ResponseEntity<?>>map(location -> ResponseEntity.ok().body(location))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(locationService.getAll());
    }
}
