package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.CustomerRegisterTicket;
import com.example.vehicle_management.repositories.ICustomerRegisterTicketRepository;

import java.util.List;

public class CustomerRegisterTicketRepositoryImpl implements ICustomerRegisterTicketRepository {
    @Override
    public boolean insert(CustomerRegisterTicket customerRegisterTicket) {
        return false;
    }

    @Override
    public boolean update(CustomerRegisterTicket customerRegisterTicket) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public CustomerRegisterTicket getById(int id) {
        return null;
    }

    @Override
    public List<CustomerRegisterTicket> getAll() {
        return List.of();
    }
}
