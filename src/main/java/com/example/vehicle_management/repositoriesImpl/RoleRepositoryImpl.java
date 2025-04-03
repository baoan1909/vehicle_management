package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Role;
import com.example.vehicle_management.repositories.IRoleRepository;

import java.util.List;

public class RoleRepositoryImpl implements IRoleRepository {
    @Override
    public boolean insert(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Role getById(int id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return List.of();
    }
}
