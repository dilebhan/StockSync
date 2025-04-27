package com.stocksync.backend.controller;

import com.stocksync.backend.dto.LocationDTO;
import com.stocksync.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO dto) {
        return ResponseEntity.ok(locationService.createLocation(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable UUID id, @RequestBody LocationDTO dto) {
        return locationService.updateLocation(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID id) {
        if (locationService.deleteLocation(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable UUID id) {
        return locationService.getLocation(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<LocationDTO> listLocations() {
        return locationService.listLocations();
    }
}
