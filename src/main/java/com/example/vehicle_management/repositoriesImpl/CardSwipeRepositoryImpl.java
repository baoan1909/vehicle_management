package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.repositories.ICardSwipeRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CardSwipeRepositoryImpl implements ICardSwipeRepository {
    private static final String SELECT_CARD_SWIPE_BY_CARD_ID = "SELECT * FROM CardSwipe WHERE cardId = ? AND checkOutTime IS NULL ORDER BY checkInTime DESC LIMIT 1;";
    @Override
    public boolean insert(CardSwipe cardSwipe) {
        return false;
    }

    @Override
    public boolean update(CardSwipe cardSwipe) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public CardSwipe getById(int id) {
        return null;
    }

    @Override
    public List<CardSwipe> getAll() {
        return List.of();
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
