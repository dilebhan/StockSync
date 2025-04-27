package com.stocksync.service;

import com.stocksync.dto.ItemDTO;
import com.stocksync.dto.ItemLocationMappingDTO;
import com.stocksync.entity.Item;
import com.stocksync.entity.LocationMapping;
import com.stocksync.repository.ItemRepository;
import com.stocksync.repository.LocationMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private LocationMappingRepository locationMappingRepository;

    public Item create(ItemDTO dto) {
        Item item = new Item();
        item.setItemName(dto.getItemName());
        item.setDescription(dto.getDescription());
        item.setUnitPrice(dto.getUnitPrice());
        item.setCostPrice(dto.getCostPrice());
        item.setUnitValue(dto.getUnitValue());
        item.setUnitType(dto.getUnitType());
        long now = System.currentTimeMillis();
        item.setCreatedTime(now);
        item.setUpdatedTime(now);
        Item saved = itemRepository.save(item);

        if (dto.getLocations() != null) {
            for (ItemLocationMappingDTO mappingDTO : dto.getLocations()) {
                LocationMapping mapping = new LocationMapping();
                mapping.setItemId(saved.getItemId());
                mapping.setLocationId(mappingDTO.getLocationId());
                mapping.setAvailableQuantity(mappingDTO.getAvailableQuantity());
                mapping.setMinimumQuantity(mappingDTO.getMinimumQuantity());
                mapping.setCreatedTime(now);
                mapping.setUpdatedTime(now);
                locationMappingRepository.save(mapping);
            }
        }
        return saved;
    }

    public Optional<Item> update(UUID id, ItemDTO dto) {
        Optional<Item> opt = itemRepository.findById(id);
        if (opt.isPresent()) {
            Item item = opt.get();
            item.setItemName(dto.getItemName());
            item.setDescription(dto.getDescription());
            item.setUnitPrice(dto.getUnitPrice());
            item.setCostPrice(dto.getCostPrice());
            item.setUnitValue(dto.getUnitValue());
            item.setUnitType(dto.getUnitType());
            item.setUpdatedTime(System.currentTimeMillis());
            itemRepository.save(item);

            // Remove old mappings and add new ones
            List<LocationMapping> oldMappings = locationMappingRepository.findByItemId(id);
            locationMappingRepository.deleteAll(oldMappings);

            if (dto.getLocations() != null) {
                long now = System.currentTimeMillis();
                for (ItemLocationMappingDTO mappingDTO : dto.getLocations()) {
                    LocationMapping mapping = new LocationMapping();
                    mapping.setItemId(item.getItemId());
                    mapping.setLocationId(mappingDTO.getLocationId());
                    mapping.setAvailableQuantity(mappingDTO.getAvailableQuantity());
                    mapping.setMinimumQuantity(mappingDTO.getMinimumQuantity());
                    mapping.setCreatedTime(now);
                    mapping.setUpdatedTime(now);
                    locationMappingRepository.save(mapping);
                }
            }
            return Optional.of(item);
        }
        return Optional.empty();
    }

    public boolean delete(UUID id) {
        if (itemRepository.existsById(id)) {
            List<LocationMapping> mappings = locationMappingRepository.findByItemId(id);
            locationMappingRepository.deleteAll(mappings);
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Item> get(UUID id) {
        return itemRepository.findById(id);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }
}
