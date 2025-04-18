package com.example.vehicle_management.services;

import com.example.vehicle_management.models.Customer;

import java.util.List;

public interface ICustomerService {
    boolean insertCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    List<Customer> getAllOnlyCustomer();
    int getCustomerIdByPhoneNumber(String phoneNumber);
    int insertAndReturnId(Customer customer);

}
