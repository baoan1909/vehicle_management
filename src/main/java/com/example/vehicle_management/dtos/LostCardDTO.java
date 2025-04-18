package com.example.vehicle_management.dtos;

import java.time.LocalDateTime;

public class LostCardDTO {
    private int lostCardId;
    private LocalDateTime notificationTime;
    private String customerName;
    private String phoneNumber;
    private String type;
    private String vehicleTypeName;
    private int vehicleId;

    public LostCardDTO(int lostCardId, LocalDateTime notificationTime, String customerName, String phoneNumber, String type, String vehicleTypeName, int vehicleId) {
        this.lostCardId = lostCardId;
        this.notificationTime = notificationTime;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.vehicleTypeName = vehicleTypeName;
        this.vehicleId = vehicleId;
    }

    public int getLostCardId() {
        return lostCardId;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public int getVehicleId() {return vehicleId;}
}
