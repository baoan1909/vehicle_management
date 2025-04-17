package com.example.vehicle_management.dtos;

public class AccountDTO {
    private int accountId;
    private String userName;
    private String fullName;
    private String email;
    private String roleName;
    private int status;

    public AccountDTO(int accountId, String userName,String fullName, String email, String roleName, int status) {
        this.accountId = accountId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.roleName = roleName;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getStatus() {
        return status;
    }
}
