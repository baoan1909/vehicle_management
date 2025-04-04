package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.repositories.ICustomerRepository;
import com.example.vehicle_management.services.ICustomerService;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerRepository customerRepository;
    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean insertCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(int id) {
        return false;
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }
}
