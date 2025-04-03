package com.example.vehicle_management.services;


import com.example.vehicle_management.models.Account;

import java.util.List;

public interface IAccountService {
    boolean insertAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(int id);
    Account getAccountById(int id);
    List<Account> getAllAccounts();

}
