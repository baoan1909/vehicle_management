package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.repositories.IAccountRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {
    private static final String SELECT_ACCOUNT_BY_USERNAME = "SELECT * FROM account WHERE userName = ?;";

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

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
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

        return account;
    }

    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt("accountId"),
                rs.getString("userName"),
                rs.getString("hashPassword"),
                rs.getInt("customerId"),
                rs.getInt("roleId"),
                rs.getBoolean("status"),
                rs.getTimestamp("createDate").toLocalDateTime(),
                rs.getTimestamp("updateDate").toLocalDateTime()
        );
    }
}
