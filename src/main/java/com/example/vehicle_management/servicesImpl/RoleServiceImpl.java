package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.Role;
import com.example.vehicle_management.services.IRoleService;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    @Override
    public boolean addRole(Role role) {
        return false;
    }

    @Override
    public boolean updateRole(Role role) {
        return false;
    }

    @Override
    public boolean deleteRole(int id) {
        return false;
    }

    @Override
    public Role getRoleById(int id) {
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return List.of();
    }
}
