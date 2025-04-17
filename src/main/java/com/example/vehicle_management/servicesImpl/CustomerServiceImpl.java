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
        return customerRepository.insert(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customerRepository.delete(id);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.getById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    @Override
    public int getCustomerByEmail(String email) {
        return customerRepository.getByEmail(email);
    }
}
