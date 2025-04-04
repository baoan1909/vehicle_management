package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Role;
import com.example.vehicle_management.repositories.IRoleRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements IRoleRepository {
    private static final String SELECT_ALL_ROLE = "SELECT roleId, roleName FROM Role;";
    private static final String SELECT_ROLE_BY_ID = "SELECT roleId, roleName FROM Role WHERE roleId = ?;";
    private static final String INSERT_ROLE = "INSERT INTO Role(roleName) VALUES (?);";
    private static final String UPDATE_ROLE = "UPDATE Role SET roleName = ? WHERE roleId = ?;";
    private static final String DELETE_ROLE = "DELETE FROM Role WHERE roleId = ?;";

    @Override
    public boolean insert(Role role) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_ROLE)) {

            stmt.setString(1, role.getRoleName());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Role role) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_ROLE)) {

            stmt.setString(1, role.getRoleName());
            stmt.setInt(2, role.getRoleId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_ROLE)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Role getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ROLE_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Role(
                            rs.getInt("roleId"),
                            rs.getString("roleName")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ROLE);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                roles.add(new Role(
                        rs.getInt("roleId"),
                        rs.getString("roleName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
