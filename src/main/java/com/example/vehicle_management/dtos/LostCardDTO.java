package com.example.vehicle_management.dtos;

import java.time.LocalDateTime;

public class LostCardDTO {
    private int lostCardId;
    private LocalDateTime notificationTime;
    private String customerName;
    private String phoneNumber;
    private String type;
    private String vehicleTypeName;

    public LostCardDTO(int lostCardId, LocalDateTime notificationTime, String customerName, String phoneNumber, String type, String vehicleTypeName) {
        this.lostCardId = lostCardId;
        this.notificationTime = notificationTime;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.vehicleTypeName = vehicleTypeName;
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
}
