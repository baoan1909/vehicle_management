package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.repositories.ICustomerRepository;

import java.util.List;

public class CustomerRepositoryImpl implements ICustomerRepository {
    @Override
    public boolean insert(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
}
