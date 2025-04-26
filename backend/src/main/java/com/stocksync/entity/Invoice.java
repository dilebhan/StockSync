package com.stocksync.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name = "InvoicesTable")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InvoiceID", columnDefinition = "BINARY(16)")
    private UUID invoiceId;

    @Column(name = "ContactID", columnDefinition = "BINARY(16)")
    private UUID contactId;

    @Column(name = "InvoiceNumber")
    private String invoiceNumber;

    @Column(name = "InvoiceDate")
    private LocalDate invoiceDate;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "BillingAddress", columnDefinition = "TEXT")
    private String billingAddress;

    @Column(name = "ShippingAddress", columnDefinition = "TEXT")
    private String shippingAddress;

    @Column(name = "TotalAmount")
    private Double totalAmount;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedTime")
    private Long createdTime;

    @Column(name = "UpdatedTime")
    private Long updatedTime;

    // Getters and setters
    public UUID getInvoiceId() { return invoiceId; }
    public void setInvoiceId(UUID invoiceId) { this.invoiceId = invoiceId; }
    public UUID getContactId() { return contactId; }
    public void setContactId(UUID contactId) { this.contactId = contactId; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getCreatedTime() { return createdTime; }
    public void setCreatedTime(Long createdTime) { this.createdTime = createdTime; }
    public Long getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Long updatedTime) { this.updatedTime = updatedTime; }
}
