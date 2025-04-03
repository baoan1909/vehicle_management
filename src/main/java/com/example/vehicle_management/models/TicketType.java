package com.example.vehicle_management.models;

import java.time.LocalDateTime;

public class TicketType {
    private int ticketTypeId;
    private String ticketTypeName;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Constructor
    public TicketType(int ticketTypeId, String ticketTypeName, String description, LocalDateTime createDate, LocalDateTime updateDate) {
        this.ticketTypeId = ticketTypeId;
        this.ticketTypeName = ticketTypeName;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public TicketType(){

    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
