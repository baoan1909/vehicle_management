package com.example.vehicle_management.dtos;

import java.time.LocalDateTime;

public class LostCardDTO {
    private int lostCardId;
    private LocalDateTime notificationTime;
    private String customerName;
    private String phoneNumber;
    private String type;


    private LocalDateTime timeOfLost;
    private double ticketPrice;
    private double lostCardFee;
    private String checkInLicensePhoto;
    private String checkInCustomerPhoto;
    private String visitorName;
    private String visitorPhoneNum;
    private String identifyCard;
    private String registrationLicense;
    private String note;

    public LostCardDTO(int lostCardId, String customerName, String cardNumber, LocalDateTime notificationTime,
                       LocalDateTime timeOfLost, double ticketPrice, double lostCardFee, String checkInLicensePhoto,
                       String checkInCustomerPhoto, String visitorName, String visitorPhoneNum, String identifyCard,
                       String registrationLicense, String note) {
        this.lostCardId = lostCardId;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
        this.notificationTime = notificationTime;
        this.timeOfLost = timeOfLost;
        this.ticketPrice = ticketPrice;
        this.lostCardFee = lostCardFee;
        this.checkInLicensePhoto = checkInLicensePhoto;
        this.checkInCustomerPhoto = checkInCustomerPhoto;
        this.visitorName = visitorName;
        this.visitorPhoneNum = visitorPhoneNum;
        this.identifyCard = identifyCard;
        this.registrationLicense = registrationLicense;
        this.note = note;
    }

    public int getLostCardId() {
        return lostCardId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public LocalDateTime getTimeOfLost() {
        return timeOfLost;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double getLostCardFee() {
        return lostCardFee;
    }

    public String getCheckInLicensePhoto() {
        return checkInLicensePhoto;
    }

    public String getCheckInCustomerPhoto() {
        return checkInCustomerPhoto;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public String getVisitorPhoneNum() {
        return visitorPhoneNum;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public String getRegistrationLicense() {
        return registrationLicense;
    }

    public String getNote() {
        return note;
    }
}
