package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.repositories.IAccountRepository;
import com.example.vehicle_management.services.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private final IAccountRepository accountRepository;
    public AccountServiceImpl(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

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

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.getAccountByUsername(username);
    }
}
