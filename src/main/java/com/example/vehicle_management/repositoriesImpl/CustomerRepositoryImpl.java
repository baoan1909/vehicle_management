package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.repositories.ICustomerRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements ICustomerRepository {
    private static final String SELECT_ALL_CUSTOMERS = "SELECT customerId, fullName, dateOfBirth, gender, phoneNumber, address, email, identifyCard, createDate, updateDate FROM Customer;";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT customerId, fullName, dateOfBirth, gender, phoneNumber, address, email, identifyCard, createDate, updateDate FROM Customer WHERE customerId = ?;";
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer(fullName, dateOfBirth, gender, phoneNumber, address, email, identifyCard) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET fullName = ?, dateOfBirth = ?, gender = ?, phoneNumber = ?, address = ?, email = ?, identifyCard = ? WHERE customerId = ?;";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE customerId = ?;";

    @Override
    public boolean insert(Customer customer) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER)) {

            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getDateOfBirth());
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getEmail());
            stmt.setString(7, customer.getIdentifyCard());

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
            stmt.setString(2, customer.getDateOfBirth());
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getEmail());
            stmt.setString(7, customer.getIdentifyCard());
            stmt.setInt(8, customer.getCustomerId());

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
                rs.getString("dateOfBirth"),
                rs.getString("gender"),
                rs.getString("phoneNumber"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("identifyCard"),
                rs.getTimestamp("createDate").toLocalDateTime(),
                rs.getTimestamp("updateDate").toLocalDateTime()
        );
    }
}
