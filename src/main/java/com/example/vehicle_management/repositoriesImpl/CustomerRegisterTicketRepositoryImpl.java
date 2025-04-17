package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.models.CustomerRegisterTicket;
import com.example.vehicle_management.repositories.ICustomerRegisterTicketRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRegisterTicketRepositoryImpl implements ICustomerRegisterTicketRepository {
    private static final String SELECT_ALL_CUSTOMERREGISTERTICKET = "SELECT CustomerRegisterTicketId, CardId, CustomerId, FeeCustomerId, EffectiveDate, ExpirationDate, LicensePlate, VehicleTypeId, TicketTypeId, Price  FROM CustomerRegisterTicket;";
    private static final String SELECT_CARD_BY_CUSTOMERREGISTERTICKET = "SELECT CustomerRegisterTicketId, CardId, CustomerId, FeeCustomerId, EffectiveDate, ExpirationDate, LicensePlate, VehicleTypeId,  TicketTypeId, Price  FROM CustomerRegisterTicket WHERE CustomerRegisterTicketId = ?;";
    private static final String INSERT_CUSTOMERREGISTERTICKET = "INSERT INTO CustomerRegisterTicket(CardId, CustomerId, FeeCustomerId, EffectiveDate, ExpirationDate, LicensePlate, VehicleTypeId,  TicketTypeId, Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_CUSTOMERREGISTERTICKET = "UPDATE CustomerRegisterTicket SET cardId = ?, CustomerId = ?, FeeCustomerId = ?, EffectiveDate = ?, ExpirationDate = ?, LicensePlate = ?, VehicleTypeId = ?, TicketTypeId = ?, Price = ? WHERE CustomerRegisterTicketId = ?;";
    private static final String DELETE_CUSTOMERREGISTERTICKET = "DELETE FROM CustomerRegisterTicket WHERE CustomerRegisterTicketId = ?;";

    @Override
    public boolean insert(CustomerRegisterTicket customerRegisterTicket) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMERREGISTERTICKET)) {

            stmt.setInt(1, customerRegisterTicket.getCardId());
            stmt.setInt(2, customerRegisterTicket.getCustomerId());
            stmt.setInt(3, customerRegisterTicket.getFeeCustomerId());
            stmt.setDate(4, java.sql.Date.valueOf(customerRegisterTicket.getEffectiveDate()));
            stmt.setDate(5, java.sql.Date.valueOf(customerRegisterTicket.getExpirationDate()));
            stmt.setString(6, customerRegisterTicket.getLicensePlate());
            stmt.setInt(7, customerRegisterTicket.getVehicleTypeId());
            stmt.setInt(8, customerRegisterTicket.getTicketTypeId());
            stmt.setDouble(9, customerRegisterTicket.getPrice());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(CustomerRegisterTicket customerRegisterTicket) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMERREGISTERTICKET)) {

            stmt.setInt(1, customerRegisterTicket.getCardId());
            stmt.setInt(2, customerRegisterTicket.getCustomerId());
            stmt.setInt(3, customerRegisterTicket.getFeeCustomerId());
            stmt.setDate(4, java.sql.Date.valueOf(customerRegisterTicket.getEffectiveDate()));
            stmt.setDate(5, java.sql.Date.valueOf(customerRegisterTicket.getEffectiveDate()));
            stmt.setString(6, customerRegisterTicket.getLicensePlate());
            stmt.setInt(7, customerRegisterTicket.getVehicleTypeId());
            stmt.setInt(8, customerRegisterTicket.getTicketTypeId());
            stmt.setDouble(9, customerRegisterTicket.getPrice());
            stmt.setInt(10, customerRegisterTicket.getCustomerRegisterTicketId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CUSTOMERREGISTERTICKET)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CustomerRegisterTicket getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CARD_BY_CUSTOMERREGISTERTICKET)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomerRegisterTicket(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerRegisterTicket> getAll() {
        List<CustomerRegisterTicket> customers = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CUSTOMERREGISTERTICKET);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(mapResultSetToCustomerRegisterTicket(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private CustomerRegisterTicket mapResultSetToCustomerRegisterTicket(ResultSet rs) throws SQLException {
        return new CustomerRegisterTicket(
                rs.getInt("CustomerRegisterTicketId"),
                rs.getInt("CardId"),
                rs.getInt("CustomerId"),
                rs.getInt("FeeCustomerId"),
                rs.getTimestamp("EffectiveDate").toLocalDateTime().toLocalDate(),
                rs.getTimestamp("ExpirationDate").toLocalDateTime().toLocalDate(),
                rs.getString("LicensePlate"),
                rs.getInt("VehicleTypeId"),
                rs.getInt("TicketTypeId"),
                rs.getDouble("Price")
        );
    }
}
