package com.example.vehicle_management.dtos;

import java.time.LocalDateTime;

public class CardSwipeDTO {
    private int cardSwipeId;
    private String licensePlate;
    private int cardId;
    private String type;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String checkInImagePath;
    private String imagePathOut;
    private double price;
    private String vehicleTypeName;
    private int vehicleTypeId;
    private int tickettypeId;
    private String ticketTypeName;

    public CardSwipeDTO(int cardSwipeId, String licensePlate, int cardId, String type, LocalDateTime checkInTime, LocalDateTime checkOutTime,
                        String checkInImagePath, String imagePathOut, double price, String vehicleTypeName, int vehicleTypeId, int tickettypeId, String ticketTypeName) {
        this.cardSwipeId = cardSwipeId;
        this.licensePlate = licensePlate;
        this.cardId = cardId;
        this.type = type;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.checkInImagePath = checkInImagePath;
        this.imagePathOut = imagePathOut;
        this.price = price;
        this.vehicleTypeName = vehicleTypeName;
        this.vehicleTypeId = vehicleTypeId;
        this.tickettypeId = tickettypeId;
        this.ticketTypeName = ticketTypeName;
    }

    public int getCardSwipeId() {
        return cardSwipeId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getCardId() {
        return cardId;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public String getCheckInImagePath() {
        return checkInImagePath;
    }

    public String getImagePathOut() {
        return imagePathOut;
    }

    public double getPrice() {
        return price;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public int getTickettypeId() {
        return tickettypeId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }
}
