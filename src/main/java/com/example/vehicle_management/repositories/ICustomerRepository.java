package com.example.vehicle_management.repositories;

import com.example.vehicle_management.models.Customer;

import java.util.List;

public interface ICustomerRepository extends IRepository<Customer> {
    List<Customer> getAllOnlyCustomer();
    int getCustomerIdByPhoneNumber(String phoneNumber);
    int insertAndReturnId(Customer customer);
}
