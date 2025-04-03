package com.example.vehicle_management.services;

import com.example.vehicle_management.models.CustomerRegisterTicket;

import java.util.List;

public interface ICustomerRegisterTicketService {
    boolean addCustomerRegisterTicket(CustomerRegisterTicket customerRegisterTicket);
    boolean updateCustomerRegisterTicket(CustomerRegisterTicket customerRegisterTicket);
    boolean deleteCustomerRegisterTicket(int id);
    CustomerRegisterTicket getCustomerRegisterTicketById(int id);
    List<CustomerRegisterTicket> getAllCustomerRegisterTickets();
}
