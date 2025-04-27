package com.stocksync.backend.service;

import com.stocksync.backend.dto.ItemDTO;
import com.stocksync.backend.dto.ItemLocationMappingDTO;
import com.stocksync.backend.entity.Item;
import com.stocksync.backend.entity.LocationMapping;
import com.stocksync.backend.repository.ItemRepository;
import com.stocksync.backend.repository.LocationMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private LocationMappingRepository locationMappingRepository;

    private ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setItemId(item.getItemId());
        dto.setItemName(item.getItemName());
        dto.setDescription(item.getDescription());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setCostPrice(item.getCostPrice());
        dto.setUnitValue(item.getUnitValue());
        dto.setUnitType(item.getUnitType());
        List<LocationMapping> mappings = locationMappingRepository.findByItemId(item.getItemId());
        List<ItemLocationMappingDTO> locationDTOs = mappings.stream().map(mapping -> {
            ItemLocationMappingDTO lmdto = new ItemLocationMappingDTO();
            lmdto.setLocationId(mapping.getLocationId());
            lmdto.setAvailableQuantity(mapping.getAvailableQuantity());
            lmdto.setMinimumQuantity(mapping.getMinimumQuantity());
            return lmdto;
        }).collect(Collectors.toList());
        dto.setLocations(locationDTOs);
        return dto;
    }

    private void updateEntity(Item item, ItemDTO dto) {
        item.setItemName(dto.getItemName());
        item.setDescription(dto.getDescription());
        item.setUnitPrice(dto.getUnitPrice());
        item.setCostPrice(dto.getCostPrice());
        item.setUnitValue(dto.getUnitValue());
        item.setUnitType(dto.getUnitType());
    }

    public ItemDTO createItem(ItemDTO dto) {
        Item item = new Item();
        updateEntity(item, dto);
        long now = Instant.now().toEpochMilli();
        item.setCreatedTime(now);
        item.setUpdatedTime(now);
        item = itemRepository.save(item);

        if (dto.getLocations() != null) {
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
        return toDTO(item);
    }

    public Optional<ItemDTO> updateItem(UUID id, ItemDTO dto) {
        Optional<Item> opt = itemRepository.findById(id);
        if (opt.isPresent()) {
            Item item = opt.get();
            updateEntity(item, dto);
            item.setUpdatedTime(Instant.now().toEpochMilli());
            item = itemRepository.save(item);

            // Remove old mappings and add new ones
            locationMappingRepository.deleteByItemId(id);
            if (dto.getLocations() != null) {
                long now = Instant.now().toEpochMilli();
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
            return Optional.of(toDTO(item));
        }
        return Optional.empty();
    }

    public boolean deleteItem(UUID id) {
        if (itemRepository.existsById(id)) {
            locationMappingRepository.deleteByItemId(id);
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ItemDTO> getItem(UUID id) {
        return itemRepository.findById(id).map(this::toDTO);
    }

    public List<ItemDTO> listItems() {
        return itemRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
