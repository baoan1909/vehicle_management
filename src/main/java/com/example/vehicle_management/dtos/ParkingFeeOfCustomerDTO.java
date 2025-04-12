package com.example.vehicle_management.dtos;

import java.time.LocalDate;

public class ParkingFeeOfCustomerDTO {
    private int feeCustomerId;
    private String ticketTypeName;
    private String vehicleTypeName;
    private double price;
    private LocalDate startDate;

    public ParkingFeeOfCustomerDTO() {
    }

    public ParkingFeeOfCustomerDTO(int FeeCustomerId, String ticketTypeName, String vehicleTypeName, double price, LocalDate startDate) {
        this.feeCustomerId = FeeCustomerId;
        this.ticketTypeName = ticketTypeName;
        this.vehicleTypeName = vehicleTypeName;
        this.price = price;
        this.startDate = startDate;
    }

    public int getFeeCustomerId() {
        return feeCustomerId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public double getPrice() {
        return price;
    }
}

