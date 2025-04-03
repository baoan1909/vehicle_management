package com.example.vehicle_management.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParkingFeeOfCustomer {
    private int feeCustomerId;
    private int ticketTypeId;
    private int vehicleTypeId;
    private double price;
    private String description;
    private LocalDate startDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Constructor
    public ParkingFeeOfCustomer(int feeCustomerId, int ticketTypeId, int vehicleTypeId, double price, String description, LocalDate startDate, LocalDateTime createDate, LocalDateTime updateDate) {
        this.feeCustomerId = feeCustomerId;
        this.ticketTypeId = ticketTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // No-args constructor
    public ParkingFeeOfCustomer() {
    }

    public int getFeeCustomerId() {
        return feeCustomerId;
    }

    public void setFeeCustomerId(int feeCustomerId) {
        this.feeCustomerId = feeCustomerId;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
