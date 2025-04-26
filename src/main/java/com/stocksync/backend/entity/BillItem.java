package com.stocksync.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "BillItemsTable")
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BillItemID", columnDefinition = "BINARY(16)")
    private UUID billItemId;

    @Column(name = "BillID", columnDefinition = "BINARY(16)")
    private UUID billId;

    @Column(name = "ItemID", columnDefinition = "BINARY(16)")
    private UUID itemId;

    @Column(name = "ItemName")
    private String itemName;

    @Column(name = "Quantity")
    private Double quantity;

    @Column(name = "UnitPrice")
    private Double unitPrice;

    @Column(name = "CreatedTime")
    private Long createdTime;

    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
    public UUID getBillItemId() { return billItemId; }
    public void setBillItemId(UUID billItemId) { this.billItemId = billItemId; }
    public UUID getBillId() { return billId; }
    public void setBillId(UUID billId) { this.billId = billId; }
    public UUID getItemId() { return itemId; }
    public void setItemId(UUID itemId) { this.itemId = itemId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public Long getCreatedTime() { return createdTime; }
    public void setCreatedTime(Long createdTime) { this.createdTime = createdTime; }
    public Long getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Long updatedTime) { this.updatedTime = updatedTime; }
}
