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
        return accountRepository.insert(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountRepository.update(account);
    }

    @Override
    public boolean deleteAccount(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepository.getById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAll();
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.getAccountByUsername(username);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.getAccountByEmail(email);
    }
}
