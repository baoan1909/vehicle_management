package com.example.vehicle_management.dtos;

import java.time.LocalDate;

public class CustomerDTO {
    private int customerRegisterTicketId;
    private int cardId;
    private String cardNumber;
    private int customerId;
    private String customerName;
    private String gender;
    private String address;
    private LocalDate dateOfBirth;
    private  String customerPhoneNumber;
    private String customerEmail;
    private String identifyCard;
    private int feeCustomerId;
    private int vehicleTypeId;
    private String vehicleTypeName;
    private String licensePlate;
    private int ticketTypeId;
    private String ticketTypeName;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private double price;

    public CustomerDTO(int customerRegisterTicketId, int cardId, String cardNumber,int customerId, String customerName, String gender, String address,LocalDate dateOfBirth, String customerEmail,String identifyCard, int feeCustomerId,String customerPhoneNumber, int vehicleTypeId, String vehicleTypeName, String licensePlate, int ticketTypeId, String ticketTypeName, LocalDate effectiveDate, LocalDate expirationDate, double price) {
        this.customerRegisterTicketId = customerRegisterTicketId;
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.customerEmail = customerEmail;
        this.identifyCard = identifyCard;
        this.feeCustomerId = feeCustomerId;
        this.customerPhoneNumber = customerPhoneNumber;
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.licensePlate = licensePlate;
        this.ticketTypeId = ticketTypeId;
        this.ticketTypeName = ticketTypeName;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }

    public CustomerDTO() {
    }

    public int getCustomerRegisterTicketId() {
        return customerRegisterTicketId;
    }

    public int getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getFeeCustomerId() {
        return feeCustomerId;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getIdentifyCard() {
        return identifyCard;
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

    public double getPrice() {
        return price;
    }
}
