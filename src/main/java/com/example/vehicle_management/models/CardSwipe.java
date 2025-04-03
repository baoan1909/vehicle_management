package com.example.vehicle_management.models;

import java.time.LocalDateTime;

public class CardSwipe {
    private int cardSwipeId;
    private String licensePlate;
    private int cardId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String checkInImagePath;
    private String imagePathOut;
    private double price;
    private int vehicleTypeId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public CardSwipe(int cardSwipeId, String licensePlate, int cardId, LocalDateTime checkInTime, LocalDateTime checkOutTime, String checkInImagePath, String imagePathOut, double price, int vehicleTypeId, LocalDateTime createDate, LocalDateTime updateDate) {
        this.cardSwipeId = cardSwipeId;
        this.licensePlate = licensePlate;
        this.cardId = cardId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.checkInImagePath = checkInImagePath;
        this.imagePathOut = imagePathOut;
        this.price = price;
        this.vehicleTypeId = vehicleTypeId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public CardSwipe() {
    }

    public int getCardSwipeId() {
        return cardSwipeId;
    }

    public void setCardSwipeId(int cardSwipeId) {
        this.cardSwipeId = cardSwipeId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getCheckInImagePath() {
        return checkInImagePath;
    }

    public void setCheckInImagePath(String checkInImagePath) {
        this.checkInImagePath = checkInImagePath;
    }

    public String getImagePathOut() {
        return imagePathOut;
    }

    public void setImagePathOut(String imagePathOut) {
        this.imagePathOut = imagePathOut;
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
