package com.example.vehicle_management.models;

import java.time.LocalDateTime;

public class LostCard {
    private int lostCardId;
    private int customerId;
    private int cardId;
    private LocalDateTime notificationTime;
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
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public LostCard(int lostCardId, int customerId, int cardId, LocalDateTime notificationTime, LocalDateTime timeOfLost, double ticketPrice, double lostCardFee, String checkInLicensePhoto, String checkInCustomerPhoto, String visitorName, String visitorPhoneNum, String identifyCard, String registrationLicense, String note, LocalDateTime createDate, LocalDateTime updateDate) {
        this.lostCardId = lostCardId;
        this.customerId = customerId;
        this.cardId = cardId;
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
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public LostCard(int lostCardId, int customerId, int cardId, LocalDateTime notificationTime, LocalDateTime timeOfLost, double ticketPrice, double lostCardFee, String checkInLicensePhoto, String checkInCustomerPhoto, String visitorName, String visitorPhoneNum, String identifyCard, String registrationLicense, String note) {
        this.lostCardId = lostCardId;
        this.customerId = customerId;
        this.cardId = cardId;
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

    public LostCard() {
    }

    public int getLostCardId() {
        return lostCardId;
    }

    public void setLostCardId(int lostCardId) {
        this.lostCardId = lostCardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    public LocalDateTime getTimeOfLost() {
        return timeOfLost;
    }

    public void setTimeOfLost(LocalDateTime timeOfLost) {
        this.timeOfLost = timeOfLost;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getLostCardFee() {
        return lostCardFee;
    }

    public void setLostCardFee(double lostCardFee) {
        this.lostCardFee = lostCardFee;
    }

    public String getCheckInLicensePhoto() {
        return checkInLicensePhoto;
    }

    public void setCheckInLicensePhoto(String checkInLicensePhoto) {
        this.checkInLicensePhoto = checkInLicensePhoto;
    }

    public String getCheckInCustomerPhoto() {
        return checkInCustomerPhoto;
    }

    public void setCheckInCustomerPhoto(String checkInCustomerPhoto) {
        this.checkInCustomerPhoto = checkInCustomerPhoto;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorPhoneNum() {
        return visitorPhoneNum;
    }

    public void setVisitorPhoneNum(String visitorPhoneNum) {
        this.visitorPhoneNum = visitorPhoneNum;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    public String getRegistrationLicense() {
        return registrationLicense;
    }

    public void setRegistrationLicense(String registrationLicense) {
        this.registrationLicense = registrationLicense;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
