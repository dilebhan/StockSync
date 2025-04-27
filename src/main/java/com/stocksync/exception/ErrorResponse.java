package com.stocksync.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private int status;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Getters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public Map<String, String> getErrors() { return errors; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
