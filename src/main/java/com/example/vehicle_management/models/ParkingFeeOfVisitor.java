package com.example.vehicle_management.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParkingFeeOfVisitor {
    private int feeVisitorId;
    private double price;
    private int vehicleTypeId;
    private LocalDate startDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Constructor
    public ParkingFeeOfVisitor(int feeVisitorId, double price, int vehicleTypeId, LocalDate startDate, LocalDateTime createDate, LocalDateTime updateDate) {
        this.feeVisitorId = feeVisitorId;
        this.price = price;
        this.vehicleTypeId = vehicleTypeId;
        this.startDate = startDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public ParkingFeeOfVisitor(){

    }

    public int getFeeVisitorId() {
        return feeVisitorId;
    }

    public void setFeeVisitorId(int feeVisitorId) {
        this.feeVisitorId = feeVisitorId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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
