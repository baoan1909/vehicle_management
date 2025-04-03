package com.example.vehicle_management.models;
import java.time.LocalDateTime;

public class Card {
    private int cardId;
    private String cardNumber;
    private String type;
    private int vehicleTypeId;
    private boolean isCreated;
    private boolean isUsed;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Card(int cardId, String cardNumber, String type, int vehicleTypeId, boolean isCreated, boolean isUsed, LocalDateTime createDate, LocalDateTime updateDate) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.type = type;
        this.vehicleTypeId = vehicleTypeId;
        this.isCreated = isCreated;
        this.isUsed = isUsed;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
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
