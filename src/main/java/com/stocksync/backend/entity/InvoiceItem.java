package com.stocksync.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "InvoiceItemsTable")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InvoiceItemID", columnDefinition = "BINARY(16)")
    private UUID invoiceItemId;

    @Column(name = "InvoiceID", columnDefinition = "BINARY(16)")
    private UUID invoiceId;

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
    public UUID getInvoiceItemId() { return invoiceItemId; }
    public void setInvoiceItemId(UUID invoiceItemId) { this.invoiceItemId = invoiceItemId; }
    public UUID getInvoiceId() { return invoiceId; }
    public void setInvoiceId(UUID invoiceId) { this.invoiceId = invoiceId; }
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
