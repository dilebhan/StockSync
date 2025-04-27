package com.stocksync.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name = "BillsTable")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BillID", columnDefinition = "BINARY(16)")
    private UUID billId;

    @Column(name = "ContactID", columnDefinition = "BINARY(16)")
    private UUID contactId;

    @Column(name = "BillNumber")
    private String billNumber;

    @Column(name = "BillDate")
    private LocalDate billDate;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "BillingAddress", columnDefinition = "TEXT")
    private String billingAddress;

    @Column(name = "TotalAmount")
    private Double totalAmount;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedTime")
    private Long createdTime;

    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
    public UUID getBillId() { return billId; }
    public void setBillId(UUID billId) { this.billId = billId; }
    public UUID getContactId() { return contactId; }
    public void setContactId(UUID contactId) { this.contactId = contactId; }
    public String getBillNumber() { return billNumber; }
    public void setBillNumber(String billNumber) { this.billNumber = billNumber; }
    public LocalDate getBillDate() { return billDate; }
    public void setBillDate(LocalDate billDate) { this.billDate = billDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getCreatedTime() { return createdTime; }
    public void setCreatedTime(Long createdTime) { this.createdTime = createdTime; }
    public Long getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Long updatedTime) { this.updatedTime = updatedTime; }
}
