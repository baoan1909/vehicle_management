package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.repositories.ITicketTypeRepository;
import com.example.vehicle_management.services.ITicketTypeService;

import java.util.List;

public class TicketTypeServiceImpl implements ITicketTypeService {
    private final ITicketTypeRepository iTicketTypeRepository;
    public TicketTypeServiceImpl(ITicketTypeRepository repository) {
        this.iTicketTypeRepository = repository;
    }

    @Override
    public boolean addTicketType(TicketType ticketType) {
        return false;
    }

    @Override
    public boolean updateTicketType(TicketType ticketType) {
        return false;
    }

    @Override
    public boolean deleteTicketType(int id) {
        return false;
    }

    @Override
    public TicketType getTicketTypeById(int id) {
        return null;
    }

    @Override
    public List<TicketType> getAllTicketTypes() {
        return List.of();
    }
}
