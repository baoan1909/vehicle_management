package com.example.vehicle_management.services;

import com.example.vehicle_management.models.TicketType;

import java.util.List;

public interface ITicketTypeService {
    boolean addTicketType(TicketType ticketType);
    boolean updateTicketType(TicketType ticketType);
    boolean deleteTicketType(int id);
    TicketType getTicketTypeById(int id);
    List<TicketType> getAllTicketTypes();
}
