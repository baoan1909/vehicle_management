package com.example.vehicle_management.dtos;

public class CardDTO {
    private int cardId;
    private String cardNumber;
    private String type;
    private String vehicleTypeName;
    private int isCreated;
    private int isUsed;

    public CardDTO(int cardId, String cardNumber, String type, String vehicleTypeName, int isCreated, int isUsed) {
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

    public int getIsCreated() {
        return isCreated;
    }

    public int getIsUsed() {
        return isUsed;
    }
}
