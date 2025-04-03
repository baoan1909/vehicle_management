package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.repositories.ITicketTypeRepository;

import java.util.List;

public class TicketTypeRepositoryImpl implements ITicketTypeRepository {
    @Override
    public boolean insert(TicketType ticketType) {
        return false;
    }

    @Override
    public boolean update(TicketType ticketType) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public TicketType getById(int id) {
        return null;
    }

    @Override
    public List<TicketType> getAll() {
        return List.of();
    }
}
