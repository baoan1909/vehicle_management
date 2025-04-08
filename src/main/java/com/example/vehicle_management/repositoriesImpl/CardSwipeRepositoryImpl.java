package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.repositories.ICardSwipeRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardSwipeRepositoryImpl implements ICardSwipeRepository {
    private static final String INSERT = "INSERT INTO CardSwipe (licensePlate, cardId, checkInTime, checkOutTime, checkInImagePath, imagePathOut, price, vehicleTypeId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE CardSwipe SET licensePlate = ?, cardId = ?, checkInTime = ?, checkOutTime = ?, checkInImagePath = ?, imagePathOut = ?, price = ?, vehicleTypeId = ? WHERE cardSwipeId = ?";
    private static final String DELETE = "DELETE FROM CardSwipe WHERE cardSwipeId = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM CardSwipe WHERE cardSwipeId = ?";
    private static final String SELECT_ALL = "SELECT * FROM CardSwipe";
    private static final String SELECT_CARD_SWIPE_BY_CARD_ID = "SELECT * FROM CardSwipe WHERE cardId = ? AND checkOutTime IS NULL ORDER BY checkInTime DESC LIMIT 1;";

    @Override
    public boolean insert(CardSwipe cardSwipe) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setString(1, cardSwipe.getLicensePlate());
            ps.setInt(2, cardSwipe.getCardId());
            ps.setTimestamp(3, Timestamp.valueOf(cardSwipe.getCheckInTime()));
            ps.setTimestamp(4, cardSwipe.getCheckOutTime() != null ? Timestamp.valueOf(cardSwipe.getCheckOutTime()) : null);
            ps.setString(5, cardSwipe.getCheckInImagePath());
            ps.setString(6, cardSwipe.getImagePathOut());
            ps.setDouble(7, cardSwipe.getPrice());
            ps.setInt(8, cardSwipe.getVehicleTypeId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(CardSwipe cardSwipe) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, cardSwipe.getLicensePlate());
            ps.setInt(2, cardSwipe.getCardId());
            ps.setTimestamp(3, Timestamp.valueOf(cardSwipe.getCheckInTime()));
            ps.setTimestamp(4, cardSwipe.getCheckOutTime() != null ? Timestamp.valueOf(cardSwipe.getCheckOutTime()) : null);
            ps.setString(5, cardSwipe.getCheckInImagePath());
            ps.setString(6, cardSwipe.getImagePathOut());
            ps.setDouble(7, cardSwipe.getPrice());
            ps.setInt(8, cardSwipe.getVehicleTypeId());
            ps.setInt(9, cardSwipe.getCardSwipeId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CardSwipe getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCardSwipe(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CardSwipe> getAll() {
        List<CardSwipe> list = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToCardSwipe(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private CardSwipe mapResultSetToCardSwipe(ResultSet rs) throws SQLException {
        return new CardSwipe(
                rs.getInt("cardSwipeId"),
                rs.getString("licensePlate"),
                rs.getInt("cardId"),
                rs.getTimestamp("checkInTime").toLocalDateTime(),
                rs.getTimestamp("checkOutTime") != null ? rs.getTimestamp("checkOutTime").toLocalDateTime() : null,
                rs.getString("checkInImagePath"),
                rs.getString("imagePathOut"),
                rs.getDouble("price"),
                rs.getInt("vehicleTypeId")
        );
    }

    @Override
    public CardSwipe getCardSwipeByCardId(int cardId) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CARD_SWIPE_BY_CARD_ID)) {

            // Set giá trị cho cardId
            stmt.setInt(1, cardId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Nếu tìm thấy bản ghi
                    CardSwipe cardSwipe = new CardSwipe();
                    cardSwipe.setCardId(resultSet.getInt("cardId"));
                    cardSwipe.setCheckInImagePath(resultSet.getString("checkInImagePath"));
                    cardSwipe.setPrice(resultSet.getDouble("price"));
                    cardSwipe.setCheckInTime(resultSet.getTimestamp("checkInTime").toLocalDateTime());

                    return cardSwipe;
                } else {
                    return null; // Không tìm thấy bản ghi nào
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu có (hoặc ném exception)
            return null;
        }
    }
}
