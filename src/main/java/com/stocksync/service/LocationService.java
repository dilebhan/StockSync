package com.stocksync.service;

import com.stocksync.dto.LocationDTO;
import com.stocksync.entity.Location;
import com.stocksync.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location create(LocationDTO dto) {
        Location location = new Location();
        location.setLocationName(dto.getLocationName());
        location.setAddress(dto.getAddress());
        location.setState(dto.getState());
        location.setPostalCode(dto.getPostalCode());
        location.setCountry(dto.getCountry());
        long now = System.currentTimeMillis();
        location.setCreatedTime(now);
        location.setUpdatedTime(now);
        return locationRepository.save(location);
    }

    public Optional<Location> update(UUID id, LocationDTO dto) {
        Optional<Location> opt = locationRepository.findById(id);
        if (opt.isPresent()) {
            Location location = opt.get();
            location.setLocationName(dto.getLocationName());
            location.setAddress(dto.getAddress());
            location.setState(dto.getState());
            location.setPostalCode(dto.getPostalCode());
            location.setCountry(dto.getCountry());
            location.setUpdatedTime(System.currentTimeMillis());
            locationRepository.save(location);
            return Optional.of(location);
        }
        return Optional.empty();
    }

    public boolean delete(UUID id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Location> get(UUID id) {
        return locationRepository.findById(id);
    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }
}
