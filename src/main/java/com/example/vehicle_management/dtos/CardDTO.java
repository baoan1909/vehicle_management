package com.example.vehicle_management.dtos;

public class CardDTO {
    private int cardId;
    private String cardNumber;
    private String type;
    private String vehicleTypeName;
    private boolean isCreated;
    private boolean isUsed;

    public CardDTO(int cardId, String cardNumber, String type, String vehicleTypeName, boolean isCreated, boolean isUsed) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.type = type;
        this.vehicleTypeName = vehicleTypeName;
        this.isCreated = isCreated;
        this.isUsed = isUsed;
    }

    public int getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getType() {
        return type;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
