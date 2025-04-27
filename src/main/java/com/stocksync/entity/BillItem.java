package com.stocksync.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "BillItemsTable")
@EntityListeners(AuditingEntityListener.class)
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BillItemID", columnDefinition = "BINARY(16)")
    private UUID billItemId;

    @ManyToOne
    @JoinColumn(name = "BillID", nullable = false)
    private Bill bill;

    @Column(name = "ItemID", columnDefinition = "BINARY(16)")
    private UUID itemId;

    @Column(name = "ItemName")
    private String itemName;

    @Column(name = "Quantity")
    private Double quantity;

    @Column(name = "UnitPrice")
    private Double unitPrice;

    @CreatedDate
    @Column(name = "CreatedTime", updatable = false)
    private Long createdTime;

    @LastModifiedDate
    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
    public UUID getBillItemId() { return billItemId; }
    public void setBillItemId(UUID billItemId) { this.billItemId = billItemId; }
    public Bill getBill() { return bill; }
    public void setBill(Bill bill) { this.bill = bill; }
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
