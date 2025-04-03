package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.CustomerRegisterTicket;
import com.example.vehicle_management.repositories.ICustomerRegisterTicketRepository;
import com.example.vehicle_management.services.ICustomerRegisterTicketService;

import java.util.List;

public class CustomerRegisterTicketServiceImpl implements ICustomerRegisterTicketService {
    private final ICustomerRegisterTicketRepository customerRegisterTicketRepository;
    public CustomerRegisterTicketServiceImpl(ICustomerRegisterTicketRepository customerRegisterTicketRepository) {
        this.customerRegisterTicketRepository = customerRegisterTicketRepository;
    }

    @Override
    public boolean addCustomerRegisterTicket(CustomerRegisterTicket customerRegisterTicket) {
        return false;
    }

    @Override
    public boolean updateCustomerRegisterTicket(CustomerRegisterTicket customerRegisterTicket) {
        return false;
    }

    @Override
    public boolean deleteCustomerRegisterTicket(int id) {
        return false;
    }

    @Override
    public CustomerRegisterTicket getCustomerRegisterTicketById(int id) {
        return null;
    }

    @Override
    public List<CustomerRegisterTicket> getAllCustomerRegisterTickets() {
        return List.of();
    }
}
