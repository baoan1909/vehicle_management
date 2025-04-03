package com.example.vehicle_management.models;

import java.time.LocalDateTime;

public class Account {
    private int accountId;
    private String userName;
    private String hashPassword;
    private int customerId;
    private int roleId;
    private boolean status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Account(int accountId, String userName, String hashPassword, int customerId, int roleId, boolean status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.accountId = accountId;
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.customerId = customerId;
        this.roleId = roleId;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Account() {

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
