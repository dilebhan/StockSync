package com.stocksync.dto;

import jakarta.validation.constraints.*;

public class ItemDTO {
    @NotBlank(message = "Item name is required")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    // ...existing code...
}
