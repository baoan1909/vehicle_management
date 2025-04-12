package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.models.ParkingFeeOfVisitor;
import com.example.vehicle_management.repositories.IParkingFeeOfVisitorRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingFeeOfVisitorRepositoryImpl implements IParkingFeeOfVisitorRepository {
    private static final String SELECT_ALL_PARKING_FEE_OF_VISITOR = """
        SELECT
            pfv.FeeVisitorId,
            pfv.Price,
            pfv.VehicleTypeId,
            vt.VehicleTypeName,
            pfv.StartDate,
            pfv.CreateDate,
            pfv.UpdateDate
        FROM 
            ParkingFeeOfVisitor pfv
        JOIN 
            VehicleType vt ON vt.VehicleTypeId = pfv.VehicleTypeId
        ORDER BY
            pfv.FeeVisitorId;
    """;

    private static final String SELECT_PARKING_FEE_OF_VISITOR_BY_ID = """
        SELECT
            pfv.FeeVisitorId,
            pfv.Price,
            pfv.VehicleTypeId,
            vt.VehicleTypeName,
            pfv.StartDate,
            pfv.CreateDate,
            pfv.UpdateDate
        FROM 
            ParkingFeeOfVisitor pfv
        JOIN 
            VehicleType vt ON vt.VehicleTypeId = pfv.VehicleTypeId
        WHERE 
            pfv.FeeVisitorId = ?;
    """;

    private static final String INSERT_PARKING_FEE_OF_VISITOR =
            "INSERT INTO ParkingFeeOfVisitor( Price, VehicleTypeId, StartDate) VALUES (?, ?, ?);";

    private static final String UPDATE_PARKING_FEE_OF_VISITOR =
            "UPDATE ParkingFeeOfVisitor SET  Price = ?, VehicleTypeId = ?,  StartDate = ? WHERE FeeVisitorId = ?;";

    private static final String DELETE_PARKING_FEE_OF_VISITOR =
            "DELETE FROM ParkingFeeOfVisitor WHERE FeeVisitorId = ?;";

    @Override
    public boolean insert(ParkingFeeOfVisitor parkingFeeOfVisitor) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PARKING_FEE_OF_VISITOR)) {

            stmt.setDouble(1, parkingFeeOfVisitor.getPrice());
            stmt.setInt(2, parkingFeeOfVisitor.getVehicleTypeId());
            stmt.setDate(3, java.sql.Date.valueOf(parkingFeeOfVisitor.getStartDate()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ParkingFeeOfVisitor parkingFeeOfVisitor) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_PARKING_FEE_OF_VISITOR)) {
            stmt.setDouble(1, parkingFeeOfVisitor.getPrice());
            stmt.setInt(2, parkingFeeOfVisitor.getVehicleTypeId());
            stmt.setDate(3, java.sql.Date.valueOf(parkingFeeOfVisitor.getStartDate()));
            stmt.setInt(4, parkingFeeOfVisitor.getFeeVisitorId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_PARKING_FEE_OF_VISITOR)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ParkingFeeOfVisitor getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_PARKING_FEE_OF_VISITOR_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToParkingFeeOfVisitor(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ParkingFeeOfVisitor> getAll()
    {
        List<ParkingFeeOfVisitor> parkingFeeOfVisitors = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_PARKING_FEE_OF_VISITOR);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                parkingFeeOfVisitors.add(mapResultSetToParkingFeeOfVisitor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingFeeOfVisitors;
    }

    private ParkingFeeOfVisitor mapResultSetToParkingFeeOfVisitor(ResultSet rs) throws SQLException {
        return new ParkingFeeOfVisitor(
                rs.getInt("FeeVisitorId"),
                rs.getDouble("Price"),
                rs.getInt("VehicleTypeId"),
                rs.getTimestamp("StartDate").toLocalDateTime().toLocalDate(),
                rs.getTimestamp("CreateDate").toLocalDateTime(),
                rs.getTimestamp("UpdateDate").toLocalDateTime()
        );
    }
}

