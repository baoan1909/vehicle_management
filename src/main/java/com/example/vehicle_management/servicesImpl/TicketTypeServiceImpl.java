package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.repositories.ITicketTypeRepository;
import com.example.vehicle_management.services.ITicketTypeService;

import java.util.List;

public class TicketTypeServiceImpl implements ITicketTypeService {
    private final ITicketTypeRepository ticketTypeRepository;
    public TicketTypeServiceImpl(ITicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public boolean insertTicketType(TicketType ticketType) {
        return ticketTypeRepository.insert(ticketType);
    }

    @Override
    public boolean updateTicketType(TicketType ticketType) {
        return ticketTypeRepository.update(ticketType);
    }

    @Override
    public boolean deleteTicketType(int id) {
        return ticketTypeRepository.delete(id);
    }

    @Override
    public TicketType getTicketTypeById(int id) {
        return ticketTypeRepository.getById(id);
    }

    @Override
    public List<TicketType> getAllTicketTypes() {
        return ticketTypeRepository.getAll();
    }
}
