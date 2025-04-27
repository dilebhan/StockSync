package com.stocksync.backend.service;

import com.stocksync.backend.dto.LocationDTO;
import com.stocksync.backend.entity.Location;
import com.stocksync.backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    private LocationDTO toDTO(Location location) {
        LocationDTO dto = new LocationDTO();
        dto.setLocationId(location.getLocationId());
        dto.setLocationName(location.getLocationName());
        dto.setAddress(location.getAddress());
        dto.setState(location.getState());
        dto.setPostalCode(location.getPostalCode());
        dto.setCountry(location.getCountry());
        return dto;
    }

    private void updateEntity(Location location, LocationDTO dto) {
        location.setLocationName(dto.getLocationName());
        location.setAddress(dto.getAddress());
        location.setState(dto.getState());
        location.setPostalCode(dto.getPostalCode());
        location.setCountry(dto.getCountry());
    }

    public LocationDTO createLocation(LocationDTO dto) {
        Location location = new Location();
        updateEntity(location, dto);
        long now = Instant.now().toEpochMilli();
        location.setCreatedTime(now);
        location.setUpdatedTime(now);
        location = locationRepository.save(location);
        return toDTO(location);
    }

    public Optional<LocationDTO> updateLocation(UUID id, LocationDTO dto) {
        Optional<Location> opt = locationRepository.findById(id);
        if (opt.isPresent()) {
            Location location = opt.get();
            updateEntity(location, dto);
            location.setUpdatedTime(Instant.now().toEpochMilli());
            location = locationRepository.save(location);
            return Optional.of(toDTO(location));
        }
        return Optional.empty();
    }

    public boolean deleteLocation(UUID id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<LocationDTO> getLocation(UUID id) {
        return locationRepository.findById(id).map(this::toDTO);
    }

    public List<LocationDTO> listLocations() {
        return locationRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
