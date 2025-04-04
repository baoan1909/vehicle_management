package com.example.vehicle_management.models;

import java.time.LocalDateTime;

public class VehicleType {
    private int vehicleTypeId;
    private String vehicleTypeName;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public VehicleType(int vehicleTypeId, String vehicleTypeName, String description, LocalDateTime createDate, LocalDateTime updateDate) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public VehicleType(int vehicleTypeId, String vehicleTypeName, String description) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.description = description;
    }


    public VehicleType() {

    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

