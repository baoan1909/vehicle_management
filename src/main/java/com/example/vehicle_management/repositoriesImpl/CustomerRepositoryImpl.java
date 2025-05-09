package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.repositories.ICustomerRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements ICustomerRepository {
    private static final String SELECT_ALL_CUSTOMERS = "SELECT customerId, fullName, dateOfBirth, gender, phoneNumber, address, identifyCard FROM Customer";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT customerId, fullName, dateOfBirth, gender, phoneNumber, address, identifyCard FROM Customer WHERE customerId = ?;";
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer(fullName, dateOfBirth, gender, phoneNumber, address, identifyCard) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET fullName = ?, dateOfBirth = ?, gender = ?, phoneNumber = ?, address = ?, identifyCard = ? WHERE customerId = ?;";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE customerId = ?;";
    private static final String SELECT_ALL_ONLY_CUSTOMER = "SELECT * FROM Customer;";
    private static final String SELECT_CUSTOMER_ID_BY_PHONE = "SELECT customerId FROM  Customer WHERE phoneNumber = ?;";

    @Override
    public boolean insert(Customer customer) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER)) {

            stmt.setString(1, customer.getFullName());
            stmt.setDate(2, java.sql.Date.valueOf(customer.getDateOfBirth()));
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getIdentifyCard());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMER)) {

            stmt.setString(1, customer.getFullName());
            stmt.setDate(2, java.sql.Date.valueOf(customer.getDateOfBirth()));
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getIdentifyCard());
            stmt.setInt(7, customer.getCustomerId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CUSTOMER)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CUSTOMER_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CUSTOMERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("customerId"),
                rs.getString("fullName"),
                rs.getTimestamp("dateOfBirth").toLocalDateTime().toLocalDate(),
                rs.getString("gender"),
                rs.getString("phoneNumber"),
                rs.getString("address"),
                rs.getString("identifyCard")
        );
    }

    @Override
    public List<Customer> getAllOnlyCustomer() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ONLY_CUSTOMER);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public int getCustomerIdByPhoneNumber(String phoneNumber) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CUSTOMER_ID_BY_PHONE)) {

            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("customerId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertAndReturnId(Customer customer) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getFullName());
            stmt.setDate(2, java.sql.Date.valueOf(customer.getDateOfBirth()));
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getIdentifyCard());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Trả về customerId
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
