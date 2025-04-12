package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositories.IVehicleTypeRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleTypeRepositoryImpl implements IVehicleTypeRepository {
    private static final String SELECT_ALL_VEHICLE_TYPE = "SELECT vehicleTypeId, vehicleTypeName, description FROM VehicleType;";
    private static final String SELECT_VEHICLE_TYPE_BY_ID = "SELECT vehicleTypeId, vehicleTypeName, description FROM VehicleType WHERE vehicleTypeId = ?;";
    private static final String SELECT_VEHICLE_TYPE_BY_NAME = "SELECT vehicleTypeId FROM VehicleType WHERE vehicleTypeName= ?;";
    private static final String INSERT_VEHICLE_TYPE = "INSERT INTO VehicleType(vehicleTypeName, description) VALUES (?, ?);";
    private static final String UPDATE_VEHICLE_TYPE = "UPDATE VehicleType SET vehicleTypeName = ?, description = ? WHERE vehicleTypeId = ?;";
    private static final String DELETE_VEHICLE_TYPE = "DELETE FROM VehicleType WHERE vehicleTypeId = ?;";


    @Override
    public boolean insert(VehicleType vehicleType) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_VEHICLE_TYPE)) {

            stmt.setString(1, vehicleType.getVehicleTypeName());
            stmt.setString(2, vehicleType.getDescription());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(VehicleType vehicleType) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_VEHICLE_TYPE)) {

            stmt.setString(1, vehicleType.getVehicleTypeName());
            stmt.setString(2, vehicleType.getDescription());

            stmt.setInt(3, vehicleType.getVehicleTypeId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_VEHICLE_TYPE)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public VehicleType getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_VEHICLE_TYPE_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToVehicleType(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VehicleType> getAll() {
        List<VehicleType> lstVehicleType = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_VEHICLE_TYPE);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lstVehicleType.add(mapResultSetToVehicleType(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstVehicleType;
    }

    private VehicleType mapResultSetToVehicleType(ResultSet rs) throws SQLException {
        return new VehicleType(
                rs.getInt("vehicleTypeId"),
                rs.getString("vehicleTypeName"),
                rs.getString("description")
        );
    }

    // Hàm chuyển đổi Timestamp -> LocalDateTime (nếu cần)
    private LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return (timestamp != null) ? timestamp.toLocalDateTime() : null;
    }

}
