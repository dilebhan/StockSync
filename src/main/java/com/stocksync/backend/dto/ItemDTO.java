package com.stocksync.backend.dto;

import java.util.List;
import java.util.UUID;

public class ItemDTO {
    private UUID itemId;
    private String itemName;
    private String description;
    private Double unitPrice;
    private Double costPrice;
    private Double unitValue;
    private String unitType;
    private List<ItemLocationMappingDTO> locations;

    public UUID getItemId() { return itemId; }
    public void setItemId(UUID itemId) { this.itemId = itemId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public Double getCostPrice() { return costPrice; }
    public void setCostPrice(Double costPrice) { this.costPrice = costPrice; }
    public Double getUnitValue() { return unitValue; }
    public void setUnitValue(Double unitValue) { this.unitValue = unitValue; }
    public String getUnitType() { return unitType; }
    public void setUnitType(String unitType) { this.unitType = unitType; }
    public List<ItemLocationMappingDTO> getLocations() { return locations; }
    public void setLocations(List<ItemLocationMappingDTO> locations) { this.locations = locations; }
}
