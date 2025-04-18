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
    private static final String GET_PARKING_FEE_VISITOR_BY_CARD_ID_IN_LOST_CARD = "SELECT pf.price FROM ParkingFeeOfVisitor pf JOIN VehicleType vt ON pf.vehicleTypeId = vt.vehicleTypeId JOIN Card c ON c.vehicleTypeId = vt.vehicleTypeId JOIN LostCard lc ON lc.cardId = c.cardId WHERE c.cardId = ? ORDER BY pf.startDate DESC LIMIT 1;";
    private static final String GET_CUSTOMER_ID_BY_CARD_ID = "SELECT customerId FROM CustomerRegisterTicket WHERE cardId = ? AND effectiveDate <= CURRENT_DATE AND CURRENT_DATE <= expirationDate ";
    private static final String GET_CARD_ID_BY_CARD_NUMBER = "SELECT cardId FROM CARD WHERE cardNumber = ? ";
    private static final String SELECT_CARD_BY_TYPE = "SELECT c.cardId FROM Card c WHERE c.type = 'Đăng ký' AND ( c.cardId NOT IN ( SELECT crt.cardId FROM CustomerRegisterTicket crt ) OR c.cardId IN ( SELECT crt.cardId FROM CustomerRegisterTicket crt WHERE crt.expirationDate < CURRENT_DATE ) );";

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

    @Override
    public double getParkingFeeVisitorByCardId(int cardId) {
        double price = 0.0;
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_PARKING_FEE_VISITOR_BY_CARD_ID_IN_LOST_CARD)) {

            // Thiết lập giá trị cho tham số cardId
            stmt.setInt(1, cardId);

            // Thực thi truy vấn
            ResultSet rs = stmt.executeQuery();

            // Nếu có kết quả trả về, lấy giá trị Price
            if (rs.next()) {
                price = rs.getDouble("price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    @Override
    public int getCustomerIdByCardId(int cardId) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_ID_BY_CARD_ID)) {

            stmt.setInt(1, cardId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("CustomerId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getCardIdByCardNumber(String cardNumber) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_CARD_ID_BY_CARD_NUMBER)) {

            stmt.setString(1, cardNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("CardId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Card> getCardIdByType() {
        List<Card> cards = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CARD_BY_TYPE);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("cardId");
                Card card = getById(id); // lấy thông tin đầy đủ của Card
                if (card != null) {
                    cards.add(card);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
