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

    public CustomerDTO(int customerRegisterTicketId, int cardId, String cardNumber,int customerId, String customerName, String gender, String address,LocalDate dateOfBirth,String identifyCard, int feeCustomerId,String customerPhoneNumber, int vehicleTypeId, String vehicleTypeName, String licensePlate, int ticketTypeId, String ticketTypeName, LocalDate effectiveDate, LocalDate expirationDate, double price) {
        this.customerRegisterTicketId = customerRegisterTicketId;
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
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

    public CustomerDTO(int customerId, String customerName, LocalDate dateOfBirth, String gender,
                       String customerPhoneNumber, String address, String identifyCard,
                       int cardId, int feeCustomerId, LocalDate effectiveDate,
                       LocalDate expirationDate, String licensePlate, int ticketTypeId,
                       int vehicleTypeId, double price) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.customerPhoneNumber = customerPhoneNumber;
        this.address = address;
        this.identifyCard = identifyCard;
        this.cardId = cardId;
        this.feeCustomerId = feeCustomerId;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.licensePlate = licensePlate;
        this.ticketTypeId = ticketTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.price = price;
    }

    public CustomerDTO() {
    }

    public void setCustomerRegisterTicketId(int customerRegisterTicketId) {
        this.customerRegisterTicketId = customerRegisterTicketId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    public void setFeeCustomerId(int feeCustomerId) {
        this.feeCustomerId = feeCustomerId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setPrice(double price) {
        this.price = price;
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
