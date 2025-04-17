package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.repositories.IAccountRepository;
import com.example.vehicle_management.utils.DBConnectionPool;
import com.example.vehicle_management.utils.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {
    private static final String SELECT_ALL = "SELECT * FROM Account;";
    private static final String SELECT_BY_ID = "SELECT * FROM Account WHERE accountId = ?;";
    private static final String INSERT = "INSERT INTO Account(userName, hashPassword, customerId, email, roleId, status) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE Account SET userName = ?, hashPassword = ?, customerId = ?, email = ?, roleId = ?, status = ? WHERE accountId = ?;";
    private static final String DELETE = "DELETE FROM Account WHERE accountId = ?;";
    private static final String SELECT_ACCOUNT_BY_USERNAME = "SELECT * FROM Account WHERE userName = ?;";
    private static final String SELECT_ACCOUNT_BY_EMAIL = "SELECT * FROM Account WHERE email = ?;";

    @Override
    public boolean insert(Account account) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)) {

            stmt.setString(1, account.getUserName());
            stmt.setString(2, PasswordUtil.hashPassword(account.getHashPassword())); // Hash tại đây
            stmt.setInt(3, account.getCustomerId());
            stmt.setString(4, account.getEmail());
            stmt.setInt(5, account.getRoleId());
            stmt.setInt(6, account.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Account account) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            stmt.setString(1, account.getUserName());
            stmt.setString(2, PasswordUtil.hashPassword(account.getHashPassword()));
            stmt.setInt(3, account.getCustomerId());
            stmt.setString(4, account.getEmail());
            stmt.setInt(5, account.getRoleId());
            stmt.setInt(6, account.getStatus());
            stmt.setInt(7, account.getAccountId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAccount(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                accounts.add(mapResultSetToAccount(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account getAccountByUsername(String username) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ACCOUNT_BY_USERNAME)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAccount(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Account getAccountByEmail(String email) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ACCOUNT_BY_EMAIL)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAccount(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt("accountId"),
                rs.getString("userName"),
                rs.getString("hashPassword"),
                rs.getInt("customerId"),
                rs.getString("email"),
                rs.getInt("roleId"),
                rs.getInt("status")
        );
    }
}
