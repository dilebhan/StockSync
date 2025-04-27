package com.stocksync.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class InvoiceItemDTO {
    private UUID invoiceItemId;
    private UUID itemId;
    private String itemName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;

    public UUID getInvoiceItemId() { return invoiceItemId; }
    public void setInvoiceItemId(UUID invoiceItemId) { this.invoiceItemId = invoiceItemId; }

    public UUID getItemId() { return itemId; }
    public void setItemId(UUID itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
