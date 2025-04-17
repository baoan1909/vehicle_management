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
    public boolean insertCustomerRegisterTicket(CustomerRegisterTicket customerRegisterTicket) {
        return customerRegisterTicketRepository.insert(customerRegisterTicket);
    }

    @Override
    public boolean updateCustomerRegisterTicket(CustomerRegisterTicket customerRegisterTicket) {
        return customerRegisterTicketRepository.update(customerRegisterTicket);
    }

    @Override
    public boolean deleteCustomerRegisterTicket(int id) {
        return customerRegisterTicketRepository.delete(id);
    }

    @Override
    public CustomerRegisterTicket getCustomerRegisterTicketById(int id) {
        return customerRegisterTicketRepository.getById(id);
    }

    @Override
    public List<CustomerRegisterTicket> getAllCustomerRegisterTickets() {
        return customerRegisterTicketRepository.getAll();
    }
}
