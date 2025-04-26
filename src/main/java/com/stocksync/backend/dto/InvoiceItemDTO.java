package com.stocksync.backend.dto;

import java.util.UUID;

public class InvoiceItemDTO {
    private UUID invoiceItemId;
    private UUID itemId;
    private String itemName;
    private Double quantity;
    private Double unitPrice;

    public UUID getInvoiceItemId() { return invoiceItemId; }
    public void setInvoiceItemId(UUID invoiceItemId) { this.invoiceItemId = invoiceItemId; }
    public UUID getItemId() { return itemId; }
    public void setItemId(UUID itemId) { this.itemId = itemId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
}
