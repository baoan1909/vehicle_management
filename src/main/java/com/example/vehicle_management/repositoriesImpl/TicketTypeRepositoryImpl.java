package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.repositories.ITicketTypeRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeRepositoryImpl implements ITicketTypeRepository {
    private static final String SELECT_ALL_TICKET_TYPE = "SELECT ticketTypeId, ticketTypeName, description FROM TicketType;";
    private static final String SELECT_TICKET_TYPE_BY_ID = "SELECT ticketTypeId, ticketTypeName, description FROM TicketType WHERE ticketTypeId = ?;";
    private static final String INSERT_TICKET_TYPE = "INSERT INTO TicketType(ticketTypeName, description) VALUES (?, ?);";
    private static final String UPDATE_TICKET_TYPE = "UPDATE TicketType SET ticketTypeName = ?, description = ? WHERE ticketTypeId = ?;";
    private static final String DELETE_TICKET_TYPE = "DELETE FROM TicketType WHERE ticketTypeId = ?;";

    @Override
    public boolean insert(TicketType ticketType) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_TICKET_TYPE)) {

            stmt.setString(1, ticketType.getTicketTypeName());
            stmt.setString(2, ticketType.getDescription());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(TicketType ticketType) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_TICKET_TYPE)) {

            stmt.setString(1, ticketType.getTicketTypeName());
            stmt.setString(2, ticketType.getDescription());

            stmt.setInt(3, ticketType.getTicketTypeId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_TICKET_TYPE)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TicketType getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_TICKET_TYPE_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTicketType(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TicketType> getAll() {
        List<TicketType> lstTicketType = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_TICKET_TYPE);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lstTicketType.add(mapResultSetToTicketType(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstTicketType;
    }

    private TicketType mapResultSetToTicketType(ResultSet rs) throws SQLException {
        return new TicketType(
                rs.getInt("ticketTypeId"),
                rs.getString("ticketTypeName"),
                rs.getString("description")
        );
    }

    // Hàm chuyển đổi Timestamp -> LocalDateTime
    private LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return (timestamp != null) ? timestamp.toLocalDateTime() : null;
    }
}
