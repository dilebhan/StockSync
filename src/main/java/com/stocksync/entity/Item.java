package com.stocksync.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ItemID", columnDefinition = "BINARY(16)")
    private UUID itemId;

    @Column(name = "ItemName", nullable = false)
    private String itemName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "UnitPrice")
    private Double unitPrice;

    @Column(name = "CostPrice")
    private Double costPrice;

    @Column(name = "UnitValue")
    private Double unitValue;

    @Column(name = "UnitType")
    private String unitType;

    @Column(name = "CreatedTime")
    private Long createdTime;

    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
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
    public Long getCreatedTime() { return createdTime; }
    public void setCreatedTime(Long createdTime) { this.createdTime = createdTime; }
    public Long getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Long updatedTime) { this.updatedTime = updatedTime; }
}
