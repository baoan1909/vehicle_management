package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.repositories.IAccountRepository;

import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {
    @Override
    public boolean insert(Account account) {
        return false;
    }

    @Override
    public boolean update(Account account) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Account getById(int id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return List.of();
    }
}
