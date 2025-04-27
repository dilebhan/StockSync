package com.stocksync.dto;

import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.util.UUID;

public class ContactDTO {
    private UUID contactId;

    @NotBlank
    private String contactName;

    @Email
    private String email;

    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    // Optional for DTO, but present for completeness
    private Integer type;

    // Getters
    public UUID getContactId() { return contactId; }
    public String getContactName() { return contactName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getZipCode() { return zipCode; }
    public Integer getType() { return type; }

    // Setters
    public void setContactId(UUID contactId) { this.contactId = contactId; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setCountry(String country) { this.country = country; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public void setType(Integer type) { this.type = type; }
}
