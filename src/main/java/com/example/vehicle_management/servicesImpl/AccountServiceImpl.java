package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.services.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    @Override
    public boolean insertAccount(Account account) {
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        return false;
    }

    @Override
    public boolean deleteAccount(int id) {
        return false;
    }

    @Override
    public Account getAccountById(int id) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return List.of();
    }
}
