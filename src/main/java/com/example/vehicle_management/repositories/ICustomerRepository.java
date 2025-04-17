package com.example.vehicle_management.repositories;

import com.example.vehicle_management.models.Customer;

public interface ICustomerRepository extends IRepository<Customer> {
     int getByEmail(String email);
}
