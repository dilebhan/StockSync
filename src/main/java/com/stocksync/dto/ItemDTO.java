package com.stocksync.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ItemDTO {
    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @Min(0)
    private Integer quantity;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
