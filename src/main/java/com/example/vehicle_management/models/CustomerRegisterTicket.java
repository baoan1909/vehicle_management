package com.example.vehicle_management.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerRegisterTicket {
    private int customerRegisterTicketId;
    private int cardId;
    private int customerId;
    private int feeCustomerId;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private String licensePlate;
    private int vehicleTypeId;
    private int ticketTypeId;
    private double price;

    public CustomerRegisterTicket(int customerRegisterTicketId, int cardId, int customerId, int feeCustomerId, LocalDate effectiveDate, LocalDate expirationDate, String licensePlate, int vehicleTypeId, int ticketTypeId, double price) {
        this.customerRegisterTicketId = customerRegisterTicketId;
        this.cardId = cardId;
        this.customerId = customerId;
        this.feeCustomerId = feeCustomerId;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.licensePlate = licensePlate;
        this.vehicleTypeId = vehicleTypeId;
        this.ticketTypeId = ticketTypeId;
        this.price = price;
    }

    public CustomerRegisterTicket() {
    }

    public int getCustomerRegisterTicketId() {
        return customerRegisterTicketId;
    }
    public void setCustomerRegisterTicketId(int customerRegisterTicketId) {
        this.customerRegisterTicketId = customerRegisterTicketId;
    }

    public int getCardId() {
        return cardId;
    }



    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFeeCustomerId() {
        return feeCustomerId;
    }

    public void setFeeCustomerId(int feeCustomerId) {
        this.feeCustomerId = feeCustomerId;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
