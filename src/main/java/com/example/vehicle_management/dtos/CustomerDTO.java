package com.example.vehicle_management.dtos;

import java.time.LocalDate;

public class CustomerDTO {
    private int customerId;
    private String customerName;
    private  String customerPhoneNumber;
    private String customerEmail;
    private int vehicleTypeId;
    private String vehicleTypeName;
    private String licensePlate;
    private int ticketTypeId;
    private String ticketTypeName;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;

    public CustomerDTO(int customerId, String customerName, String customerEmail, String customerPhoneNumber, int vehicleTypeId, String vehicleTypeName, String licensePlate, int ticketTypeId, String ticketTypeName, LocalDate effectiveDate, LocalDate expirationDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.licensePlate = licensePlate;
        this.ticketTypeId = ticketTypeId;
        this.ticketTypeName = ticketTypeName;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
    }

    public CustomerDTO() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
