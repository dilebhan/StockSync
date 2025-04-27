package com.stocksync.dto;

import jakarta.validation.constraints.*;

public class ContactDTO {
    @NotBlank(message = "Contact name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Invalid phone number format")
    private String phone;

    // ...existing code...
}
