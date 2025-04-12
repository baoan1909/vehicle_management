package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.repositories.IParkingFeeOfCustomerRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingFeeOfCustomerRepositoryImpl implements IParkingFeeOfCustomerRepository {

    private static final String SELECT_ALL_PARKING_FEE_OF_CUSTOMER = """
        SELECT
            pfc.FeeCustomerId,
            pfc.TicketTypeId,
            tt.TicketTypeName,
            pfc.VehicleTypeId,
            vt.VehicleTypeName,
            pfc.Price,
            pfc.StartDate,
            pfc.CreateDate,
            pfc.UpdateDate
        FROM 
            ParkingFeeOfCustomer pfc
        JOIN 
            TicketType tt ON tt.TicketTypeId = pfc.TicketTypeId
        JOIN 
            VehicleType vt ON vt.VehicleTypeId = pfc.VehicleTypeId
        ORDER BY
            pfc.FeeCustomerId;
    """;

    private static final String SELECT_PARKING_FEE_OF_CUSTOMER_BY_ID = """
        SELECT 
            pfc.FeeCustomerId,
            pfc.TicketTypeId,
            tt.TicketTypeName,
            pfc.VehicleTypeId,
            vt.VehicleTypeName,
            pfc.Price,
            pfc.StartDate,
            pfc.CreateDate,
            pfc.UpdateDate
        FROM 
            ParkingFeeOfCustomer pfc
        JOIN 
            TicketType tt ON tt.TicketTypeId = pfc.TicketTypeId
        JOIN 
            VehicleType vt ON vt.VehicleTypeId = pfc.VehicleTypeId
        WHERE 
            pfc.FeeCustomerId = ?;
    """;

    private static final String INSERT_PARKING_FEE_OF_CUSTOMER =
            "INSERT INTO ParkingFeeOfCustomer(TicketTypeId, VehicleTypeId, Price, StartDate) VALUES (?, ?, ?, ?);";

    private static final String UPDATE_PARKING_FEE_OF_CUSTOMER =
            "UPDATE ParkingFeeOfCustomer SET TicketTypeId = ?, VehicleTypeId = ?, Price = ?, StartDate = ? WHERE FeeCustomerId = ?;";

    private static final String DELETE_PARKING_FEE_OF_CUSTOMER =
            "DELETE FROM ParkingFeeOfCustomer WHERE FeeCustomerId = ?;";

    @Override
    public boolean insert(ParkingFeeOfCustomer parkingFeeOfCustomer) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PARKING_FEE_OF_CUSTOMER)) {

            stmt.setInt(1, parkingFeeOfCustomer.getTicketTypeId());
            stmt.setInt(2, parkingFeeOfCustomer.getVehicleTypeId());
            stmt.setDouble(3, parkingFeeOfCustomer.getPrice());
            stmt.setDate(4, java.sql.Date.valueOf(parkingFeeOfCustomer.getStartDate()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ParkingFeeOfCustomer parkingFeeOfCustomer) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_PARKING_FEE_OF_CUSTOMER)) {

            stmt.setInt(1, parkingFeeOfCustomer.getTicketTypeId());
            stmt.setInt(2, parkingFeeOfCustomer.getVehicleTypeId());
            stmt.setDouble(3, parkingFeeOfCustomer.getPrice());
            stmt.setDate(4, java.sql.Date.valueOf(parkingFeeOfCustomer.getStartDate()));
            stmt.setInt(5, parkingFeeOfCustomer.getFeeCustomerId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_PARKING_FEE_OF_CUSTOMER)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ParkingFeeOfCustomer getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_PARKING_FEE_OF_CUSTOMER_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToParkingFeeOfCustomer(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ParkingFeeOfCustomer> getAll() {
        List<ParkingFeeOfCustomer> parkingFeeOfCustomers = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_PARKING_FEE_OF_CUSTOMER);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                parkingFeeOfCustomers.add(mapResultSetToParkingFeeOfCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingFeeOfCustomers;
    }

    private ParkingFeeOfCustomer mapResultSetToParkingFeeOfCustomer(ResultSet rs) throws SQLException {
        return new ParkingFeeOfCustomer(
                rs.getInt("FeeCustomerId"),
                rs.getInt("TicketTypeId"),
                rs.getInt("VehicleTypeId"),
                rs.getDouble("Price"),
                rs.getTimestamp("StartDate").toLocalDateTime().toLocalDate(),
                rs.getTimestamp("CreateDate").toLocalDateTime(),
                rs.getTimestamp("UpdateDate").toLocalDateTime()
        );
    }
}
