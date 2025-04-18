package com.example.vehicle_management.models;

import java.time.LocalDateTime;

public class Account {
    private int accountId;
    private String userName;
    private String hashPassword;
    private int customerId;
    private String email;
    private int roleId;
    private int status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Account(int accountId, String userName, String hashPassword, int customerId, String email, int roleId, int status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.accountId = accountId;
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.customerId = customerId;
        this.email = email;
        this.roleId = roleId;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
    public Account(int accountId, String userName, String hashPassword, int customerId, String email, int roleId, int status) {
        this.accountId = accountId;
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.customerId = customerId;
        this.email = email;
        this.roleId = roleId;
        this.status = status;
    }

    public Account() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
