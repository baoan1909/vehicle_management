package com.example.vehicle_management.services;

import com.example.vehicle_management.models.Role;

import java.util.List;

public interface IRoleService {
    boolean addRole(Role role);
    boolean updateRole(Role role);
    boolean deleteRole(int id);
    Role getRoleById(int id);
    List<Role> getAllRoles();
}
