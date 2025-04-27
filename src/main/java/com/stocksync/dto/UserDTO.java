package com.stocksync.dto;

public class UserDTO {
    private Long userId;
    private String userName;
    private String userEmailId;
    private String userStatus;

    // Getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getUserEmailId() { return userEmailId; }
    public void setUserEmailId(String userEmailId) { this.userEmailId = userEmailId; }
    
    public String getUserStatus() { return userStatus; }
    public void setUserStatus(String userStatus) { this.userStatus = userStatus; }
}
