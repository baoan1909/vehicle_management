package com.example.vehicle_management.dtos;

import java.time.LocalDate;

public class ParkingFeeOfVisitorDTO {
    private int feeVisitorId;
    private double price;
    private String vehicleTypeName;
    private LocalDate startDate;

    public ParkingFeeOfVisitorDTO() {

    }


    public ParkingFeeOfVisitorDTO(int feeVisitorId, double parkingFeeOfVisitor, String vehicleTypeName, LocalDate startDate) {
        this.feeVisitorId = feeVisitorId;
        this.price = parkingFeeOfVisitor;
        this.vehicleTypeName = vehicleTypeName;
        this.startDate = startDate;
    }

    public int getFeeVisitorId() {
        return feeVisitorId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public double getParkingFeeOfVisitor() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
