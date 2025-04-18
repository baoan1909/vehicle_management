package com.example.vehicle_management.dtos;

public class CardDTO {
    private int cardId;
    private String cardNumber;
    private String type;
    private String vehicleTypeName;
    private int isCreated;
    private int isUsed;
    private int vehicleTypeId;
    private int tickettypeId;
    private String ticketTypeName;

    public CardDTO(int cardId, String cardNumber, String type, String vehicleTypeName, int isCreated, int isUsed, int vehicleTypeId, int tickettypeId, String ticketTypeName) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.type = type;
        this.vehicleTypeName = vehicleTypeName;
        this.isCreated = isCreated;
        this.isUsed = isUsed;
        this.vehicleTypeId = vehicleTypeId;
        this.tickettypeId = tickettypeId;
        this.ticketTypeName = ticketTypeName;
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
