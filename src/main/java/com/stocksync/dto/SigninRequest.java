package com.stocksync.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SigninRequest {
    @NotBlank
    @Email
    private String userEmailId;
    
    @NotBlank
    private String userPassword;

    public String getUserEmailId() { return userEmailId; }
    public void setUserEmailId(String userEmailId) { this.userEmailId = userEmailId; }
    
    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
}

