package com.example.vehicle_management.models;

import java.time.LocalDateTime;

public class Role {
    private int roleId;
    private String roleName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Role(int roleId, String roleName, LocalDateTime createDate, LocalDateTime updateDate) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Role(){

    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
