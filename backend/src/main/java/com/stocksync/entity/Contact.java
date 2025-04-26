package com.stocksync.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ContactID", columnDefinition = "BINARY(16)")
    private UUID contactId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Type", nullable = false)
    private Integer type; // 1 = Customer, 2 = Vendor

    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "CreatedTime")
    private Long createdTime;

    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
    public UUID getContactId() { return contactId; }
    public void setContactId(UUID contactId) { this.contactId = contactId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Long getCreatedTime() { return createdTime; }
    public void setCreatedTime(Long createdTime) { this.createdTime = createdTime; }
    public Long getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Long updatedTime) { this.updatedTime = updatedTime; }
}
