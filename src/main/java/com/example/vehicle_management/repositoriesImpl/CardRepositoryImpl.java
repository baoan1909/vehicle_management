package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.repositories.ICardRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements ICardRepository {
    private static final String SELECT_ALL_CARD = "SELECT cardId, cardNumber, type, vehicleTypeId, isCreated, isUsed FROM Card;";
    private static final String SELECT_CARD_BY_ID = "SELECT cardId, cardNumber, type, vehicleTypeId, isCreated, isUsed FROM Card WHERE cardId = ?;";
    private static final String INSERT_CARD = "INSERT INTO Card(cardNumber, type, vehicleTypeId, isCreated, isUsed) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_CARD = "UPDATE Card SET cardNumber = ?, type = ?, vehicleTypeId = ?, isCreated = ?, isUsed = ? WHERE cardId = ?;";
    private static final String DELETE_CARD = "DELETE FROM Card WHERE cardId = ?;";

    @Override
    public boolean insert(Card card) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CARD)) {

            stmt.setString(1, card.getCardNumber());
            stmt.setString(2, card.getType());
            stmt.setInt(3, card.getVehicleTypeId());
            stmt.setInt(4, card.getIsCreated());
            stmt.setInt(5, card.getIsUsed());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Card card) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CARD)) {

            stmt.setString(1, card.getCardNumber());
            stmt.setString(2, card.getType());
            stmt.setInt(3, card.getVehicleTypeId());
            stmt.setInt(4, card.getIsCreated());
            stmt.setInt(5, card.getIsUsed());
            stmt.setInt(6, card.getCardId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CARD)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Card getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CARD_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCard(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Card> getAll() {
        List<Card> cards = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CARD);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cards.add(mapResultSetToCard(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private Card mapResultSetToCard(ResultSet rs) throws SQLException {
        return new Card(
                rs.getInt("cardId"),
                rs.getString("cardNumber"),
                rs.getString("type"),
                rs.getInt("vehicleTypeId"),
                rs.getInt("isCreated"),
                rs.getInt("isUsed")
        );
    }

    @Override
    public boolean existsByCardNumber(String cardNumber) {
        String query = "SELECT COUNT(*) FROM Card WHERE cardNumber = ?";

        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cardNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Nếu số lượng bản ghi tìm thấy lớn hơn 0 thì thẻ đã tồn tại
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu không có kết quả hoặc có lỗi
    }
}
