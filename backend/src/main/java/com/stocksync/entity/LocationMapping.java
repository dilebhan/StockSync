package com.stocksync.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "LocationMappingTable")
public class LocationMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MappingID", columnDefinition = "BINARY(16)")
    private UUID mappingId;

    @Column(name = "ItemID", columnDefinition = "BINARY(16)")
    private UUID itemId;

    @Column(name = "LocationID", columnDefinition = "BINARY(16)")
    private UUID locationId;

    @Column(name = "AvailableQuantity")
    private Double availableQuantity;

    @Column(name = "MinimumQuantity")
    private Double minimumQuantity;

    @Column(name = "CreatedTime")
    private Long createdTime;

    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
    public UUID getMappingId() { return mappingId; }
    public void setMappingId(UUID mappingId) { this.mappingId = mappingId; }
    public UUID getItemId() { return itemId; }
    public void setItemId(UUID itemId) { this.itemId = itemId; }
    public UUID getLocationId() { return locationId; }
    public void setLocationId(UUID locationId) { this.locationId = locationId; }
    public Double getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(Double availableQuantity) { this.availableQuantity = availableQuantity; }
    public Double getMinimumQuantity() { return minimumQuantity; }
    public void setMinimumQuantity(Double minimumQuantity) { this.minimumQuantity = minimumQuantity; }
    public Long getCreatedTime() { return createdTime; }
    public void setCreatedTime(Long createdTime) { this.createdTime = createdTime; }
    public Long getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Long updatedTime) { this.updatedTime = updatedTime; }
}
