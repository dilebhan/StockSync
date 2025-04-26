package com.stocksync.backend.dto;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

public class BillDTO {
    private UUID billId;
    private UUID contactId;
    private String billNumber;
    private LocalDate billDate;
    private LocalDate dueDate;
    private String billingAddress;
    private Double totalAmount;
    private String status;
    private List<BillItemDTO> items;

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
    public List<BillItemDTO> getItems() { return items; }
    public void setItems(List<BillItemDTO> items) { this.items = items; }
}
