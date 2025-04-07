package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.repositories.ILostCardRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LostCardRepositoryImpl implements ILostCardRepository {
    private static final String SELECT_ALL_LOST_CARDS = "SELECT lostCardId, customerId, cardId, notificationTime, timeOfLost, ticketPrice, lostCardFee, checkInLicensePhoto, checkInCustomerPhoto, visitorName, visitorPhoneNum, identifyCard, registrationLicense, note FROM LostCard;";
    private static final String SELECT_LOST_CARD_BY_ID = "SELECT lostCardId, customerId, cardId, notificationTime, timeOfLost, ticketPrice, lostCardFee, checkInLicensePhoto, checkInCustomerPhoto, visitorName, visitorPhoneNum, identifyCard, registrationLicense, note FROM LostCard WHERE lostCardId = ?;";
    private static final String INSERT_LOST_CARD = "INSERT INTO LostCard(customerId, cardId, notificationTime, timeOfLost, ticketPrice, lostCardFee, checkInLicensePhoto, checkInCustomerPhoto, visitorName, visitorPhoneNum, identifyCard, registrationLicense, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_LOST_CARD = "UPDATE LostCard SET customerId = ?, cardId = ?, notificationTime = ?, timeOfLost = ?, ticketPrice = ?, lostCardFee = ?, checkInLicensePhoto = ?, checkInCustomerPhoto = ?, visitorName = ?, visitorPhoneNum = ?, identifyCard = ?, registrationLicense = ?, note = ? WHERE lostCardId = ?;";
    private static final String DELETE_LOST_CARD = "DELETE FROM LostCard WHERE lostCardId = ?;";

    @Override
    public boolean insert(LostCard lostCard) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_LOST_CARD)) {

            if (lostCard.getCustomerId() != null) {
                stmt.setInt(1, lostCard.getCustomerId());
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }

            if (lostCard.getCardId() != null) {
                stmt.setInt(2, lostCard.getCardId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.setTimestamp(3, Timestamp.valueOf(lostCard.getNotificationTime()));
            stmt.setTimestamp(4, Timestamp.valueOf(lostCard.getTimeOfLost()));
            stmt.setDouble(5, lostCard.getTicketPrice());
            stmt.setDouble(6, lostCard.getLostCardFee());
            stmt.setString(7, lostCard.getCheckInLicensePhoto());
            stmt.setString(8, lostCard.getCheckInCustomerPhoto());
            stmt.setString(9, lostCard.getVisitorName());
            stmt.setString(10, lostCard.getVisitorPhoneNum());
            stmt.setString(11, lostCard.getIdentifyCard());
            stmt.setString(12, lostCard.getRegistrationLicense());
            stmt.setString(13, lostCard.getNote());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LostCard lostCard) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_LOST_CARD)) {

            if (lostCard.getCustomerId() != null) {
                stmt.setInt(1, lostCard.getCustomerId());
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }

            if (lostCard.getCardId() != null) {
                stmt.setInt(2, lostCard.getCardId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.setTimestamp(3, Timestamp.valueOf(lostCard.getNotificationTime()));
            stmt.setTimestamp(4, Timestamp.valueOf(lostCard.getTimeOfLost()));
            stmt.setDouble(5, lostCard.getTicketPrice());
            stmt.setDouble(6, lostCard.getLostCardFee());
            stmt.setString(7, lostCard.getCheckInLicensePhoto());
            stmt.setString(8, lostCard.getCheckInCustomerPhoto());
            stmt.setString(9, lostCard.getVisitorName());
            stmt.setString(10, lostCard.getVisitorPhoneNum());
            stmt.setString(11, lostCard.getIdentifyCard());
            stmt.setString(12, lostCard.getRegistrationLicense());
            stmt.setString(13, lostCard.getNote());
            stmt.setInt(14, lostCard.getLostCardId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_LOST_CARD)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LostCard getById(int id) {
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_LOST_CARD_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLostCard(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LostCard> getAll() {
        List<LostCard> lostCards = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_LOST_CARDS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lostCards.add(mapResultSetToLostCard(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lostCards;
    }

    private LostCard mapResultSetToLostCard(ResultSet rs) throws SQLException {
        return new LostCard(
                rs.getInt("lostCardId"),
                rs.getInt("customerId"),
                rs.getInt("cardId"),
                rs.getTimestamp("notificationTime").toLocalDateTime(),
                rs.getTimestamp("timeOfLost").toLocalDateTime(),
                rs.getDouble("ticketPrice"),
                rs.getDouble("lostCardFee"),
                rs.getString("checkInLicensePhoto"),
                rs.getString("checkInCustomerPhoto"),
                rs.getString("visitorName"),
                rs.getString("visitorPhoneNum"),
                rs.getString("identifyCard"),
                rs.getString("registrationLicense"),
                rs.getString("note")
        );
    }

}
