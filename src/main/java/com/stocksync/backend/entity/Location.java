package com.stocksync.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LocationID", columnDefinition = "BINARY(16)")
    private UUID locationId;

    @Column(name = "LocationName", nullable = false)
    private String locationName;

    @Column(name = "Address")
    private String address;

    @Column(name = "State")
    private String state;

    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Country")
    private String country;

    @Column(name = "CreatedTime")
    private Long createdTime;

    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
    public UUID getLocationId() { return locationId; }
    public void setLocationId(UUID locationId) { this.locationId = locationId; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public Long getCreatedTime() { return createdTime; }
    public void setCreatedTime(Long createdTime) { this.createdTime = createdTime; }
    public Long getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Long updatedTime) { this.updatedTime = updatedTime; }
}
