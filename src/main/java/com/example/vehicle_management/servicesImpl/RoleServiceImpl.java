package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.Role;
import com.example.vehicle_management.repositories.IRoleRepository;
import com.example.vehicle_management.services.IRoleService;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;
    public RoleServiceImpl(IRoleRepository repository) {
        this.roleRepository = repository;
    }

    @Override
    public boolean insertRole(Role role) {
        return roleRepository.insert(role);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleRepository.update(role);
    }

    @Override
    public boolean deleteRole(int id) {
        return roleRepository.delete(id);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.getById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAll();
    }
}
