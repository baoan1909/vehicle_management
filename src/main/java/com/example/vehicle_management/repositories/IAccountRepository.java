package com.example.vehicle_management.repositories;

import com.example.vehicle_management.models.Account;

public interface IAccountRepository extends IRepository<Account> {
   Account getAccountByUsername(String username);
   Account getAccountByEmail(String email);
}
