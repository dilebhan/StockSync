package com.stocksync.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "StockAlert")
public class StockAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StockAlertId", columnDefinition = "BINARY(16)")
    private UUID stockAlertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ItemID", referencedColumnName = "ItemID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LocationID", referencedColumnName = "LocationID")
    private Location location;

    @Column(name = "CurrentQuantity")
    private Double currentQuantity;

    @Column(name = "MinimumQuantity")
    private Double minimumQuantity;

    @Column(name = "CreatedTime")
    private LocalDateTime createdTime;

    @Column(name = "isRead")
    private boolean isRead;

    // Update getters and setters for new relationships
    public UUID getStockAlertId() { return stockAlertId; }
    public void setStockAlertId(UUID stockAlertId) { this.stockAlertId = stockAlertId; }
    
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    
    public Double getCurrentQuantity() { return currentQuantity; }
    public void setCurrentQuantity(Double currentQuantity) { this.currentQuantity = currentQuantity; }
    
    public Double getMinimumQuantity() { return minimumQuantity; }
    public void setMinimumQuantity(Double minimumQuantity) { this.minimumQuantity = minimumQuantity; }
    
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    
    public boolean isRead() { return isRead; }
    public void setRead(boolean isRead) { this.isRead = isRead; }

    // Helper methods to maintain backward compatibility
    public UUID getItemId() { return item != null ? item.getItemId() : null; }
    public UUID getLocationId() { return location != null ? location.getLocationId() : null; }
}

