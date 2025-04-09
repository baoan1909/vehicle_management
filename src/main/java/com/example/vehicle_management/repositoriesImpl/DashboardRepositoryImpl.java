package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.repositories.IDashboardRepository;
import com.example.vehicle_management.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardRepositoryImpl implements IDashboardRepository {
    
    // SQL Queries as constants
    private static final String SQL_TOTAL_REVENUE_CARD_SWIPE = "SELECT SUM(Price) as TotalRevenue FROM CardSwipe WHERE CheckOutTime IS NOT NULL";
    private static final String SQL_TOTAL_REVENUE_CUSTOMER_REGISTER = "SELECT SUM(Price) as TotalRevenue FROM CustomerRegisterTicket";
    private static final String SQL_TOTAL_VISITORS = "SELECT COUNT(*) as TotalVisitors FROM CardSwipe";
    private static final String SQL_TOTAL_REGISTERED_CARDS = "SELECT COUNT(*) as TotalCards FROM Card WHERE IsCreated = TRUE";
    private static final String SQL_LATEST_DATA_YEAR = "SELECT YEAR(CheckOutTime) AS Year FROM CardSwipe WHERE CheckOutTime IS NOT NULL ORDER BY CheckOutTime DESC LIMIT 1";
    private static final String SQL_REVENUE_BY_MONTH_CARD_SWIPE = "SELECT MONTH(CheckOutTime) AS Month, SUM(Price) AS Revenue FROM CardSwipe WHERE CheckOutTime IS NOT NULL AND YEAR(CheckOutTime) = ? GROUP BY MONTH(CheckOutTime) ORDER BY Month";
    private static final String SQL_REVENUE_BY_MONTH_CUSTOMER_REGISTER = "SELECT MONTH(CreateDate) AS Month, SUM(Price) AS Revenue FROM CustomerRegisterTicket WHERE YEAR(CreateDate) = ? GROUP BY MONTH(CreateDate) ORDER BY Month";
    private static final String SQL_VEHICLE_TYPE_DATA = "SELECT vt.VehicleTypeName, COUNT(cs.CardSwipeId) as Count FROM CardSwipe cs JOIN VehicleType vt ON cs.VehicleTypeId = vt.VehicleTypeId GROUP BY vt.VehicleTypeId, vt.VehicleTypeName";
    private static final String SQL_LOST_CARD_COUNT = "SELECT COUNT(*) AS LostCount FROM LostCard";
    private static final String SQL_MEMBER_CARD_COUNT = "SELECT COUNT(*) AS MemberCount FROM CustomerRegisterTicket";
    private static final String SQL_VISITOR_CARD_COUNT = "SELECT COUNT(DISTINCT cs.CardId) AS VisitorCount FROM CardSwipe cs LEFT JOIN CustomerRegisterTicket crt ON cs.CardId = crt.CardId WHERE crt.CardId IS NULL";

    @Override
    public double getTotalRevenue() throws SQLException {
        double total = 0;
        Connection conn = null;
        
        try {
            conn = DBConnectionPool.getConnection();
            
            try (PreparedStatement stmt = conn.prepareStatement(SQL_TOTAL_REVENUE_CARD_SWIPE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble("TotalRevenue");
                }
            }
            
            // Thêm doanh thu từ đăng ký vé tháng
            try (PreparedStatement stmt = conn.prepareStatement(SQL_TOTAL_REVENUE_CUSTOMER_REGISTER);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total += rs.getDouble("TotalRevenue");
                }
            }
            
        } finally {
            DBConnectionPool.closeConnection(conn);
        }
        
        return total;
    }

    @Override
    public int getTotalVisitors() throws SQLException {
        int total = 0;
        Connection conn = null;
        
        try {
            conn = DBConnectionPool.getConnection();
            
            try (PreparedStatement stmt = conn.prepareStatement(SQL_TOTAL_VISITORS);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("TotalVisitors");
                }
            }
        } finally {
            DBConnectionPool.closeConnection(conn);
        }
        
        return total;
    }

    @Override
    public int getTotalRegisteredCards() throws SQLException {
        int total = 0;
        Connection conn = null;
        
        try {
            conn = DBConnectionPool.getConnection();
            
            try (PreparedStatement stmt = conn.prepareStatement(SQL_TOTAL_REGISTERED_CARDS);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("TotalCards");
                }
            }
        } finally {
            DBConnectionPool.closeConnection(conn);
        }
        
        return total;
    }

    @Override
    public List<Map<String, Object>> getRevenueByTimeData() throws SQLException {
        List<Map<String, Object>> revenueData = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = DBConnectionPool.getConnection();
            
            // Tạo dữ liệu giả cho 12 tháng
            for (int i = 1; i <= 12; i++) {
                Map<String, Object> entry = new HashMap<>();
                entry.put("month", i);
                entry.put("revenue", 0.0); // Giá trị mặc định 0
                revenueData.add(entry);
            }
            
            // Lấy năm có dữ liệu gần nhất
            int dataYear = 0;
            try (PreparedStatement stmt = conn.prepareStatement(SQL_LATEST_DATA_YEAR);
                ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dataYear = rs.getInt("Year");
                } else {
                    dataYear = java.time.Year.now().getValue(); // Năm hiện tại nếu không có dữ liệu
                }
            }
            
            // Lấy doanh thu theo tháng từ CardSwipe trong năm có dữ liệu
            try (PreparedStatement stmt = conn.prepareStatement(SQL_REVENUE_BY_MONTH_CARD_SWIPE)) {
                stmt.setInt(1, dataYear);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int month = rs.getInt("Month");
                        double revenue = rs.getDouble("Revenue");
                        
                        // Cập nhật giá trị doanh thu cho tháng tương ứng
                        if (month >= 1 && month <= 12) {
                            revenueData.get(month - 1).put("revenue", revenue);
                        }
                    }
                }
            }
            
            // Thêm doanh thu từ CustomerRegisterTicket theo tháng trong năm có dữ liệu
            try (PreparedStatement stmt = conn.prepareStatement(SQL_REVENUE_BY_MONTH_CUSTOMER_REGISTER)) {
                stmt.setInt(1, dataYear);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int month = rs.getInt("Month");
                        double revenue = rs.getDouble("Revenue");
                        
                        // Cập nhật giá trị doanh thu cho tháng tương ứng (cộng dồn vào dữ liệu hiện có)
                        if (month >= 1 && month <= 12) {
                            double currentRevenue = (double) revenueData.get(month - 1).get("revenue");
                            revenueData.get(month - 1).put("revenue", currentRevenue + revenue);
                        }
                    }
                }
            }
            
            // Thêm log để debug
            System.out.println("Revenue data for year " + dataYear + ":");
            for (Map<String, Object> entry : revenueData) {
                System.out.println("  Month: " + entry.get("month") + ", Revenue: " + entry.get("revenue"));
            }
            
        } finally {
            DBConnectionPool.closeConnection(conn);
        }
        
        return revenueData;
    }

    @Override
    public List<Map<String, Object>> getVehicleTypeData() throws SQLException {
        List<Map<String, Object>> vehicleData = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = DBConnectionPool.getConnection();
            
            try (PreparedStatement stmt = conn.prepareStatement(SQL_VEHICLE_TYPE_DATA);
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Map<String, Object> entry = new HashMap<>();
                    entry.put("type", rs.getString("VehicleTypeName"));
                    entry.put("count", rs.getInt("Count"));
                    vehicleData.add(entry);
                }
            }
        } finally {
            DBConnectionPool.closeConnection(conn);
        }
        
        return vehicleData;
    }

    @Override
    public Map<String, Object> getCardStatistics() throws SQLException {
        Map<String, Object> cardStats = new HashMap<>();
        Connection conn = null;
        
        try {
            conn = DBConnectionPool.getConnection();
            // Thẻ bị mất
            try (PreparedStatement stmt = conn.prepareStatement(SQL_LOST_CARD_COUNT);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cardStats.put("lostCards", rs.getInt("LostCount"));
                }
            }
            
            // Thẻ thành viên (đăng ký)
            try (PreparedStatement stmt = conn.prepareStatement(SQL_MEMBER_CARD_COUNT);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cardStats.put("memberCards", rs.getInt("MemberCount"));
                }
            }
            
            // Thẻ vãng lai - tính từ CardSwipe mà không có trong CustomerRegisterTicket
            try (PreparedStatement stmt = conn.prepareStatement(SQL_VISITOR_CARD_COUNT);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cardStats.put("visitorCards", rs.getInt("VisitorCount"));
                }
            }
            
            // Tính toán phần trăm
            int totalCards = ((Integer)cardStats.getOrDefault("lostCards", 0)) + 
                             ((Integer)cardStats.getOrDefault("memberCards", 0)) + 
                             ((Integer)cardStats.getOrDefault("visitorCards", 0));
            
            if (totalCards > 0) {
                cardStats.put("lostCardsPercent", (double)((Integer)cardStats.getOrDefault("lostCards", 0)) / totalCards * 100);
                cardStats.put("memberCardsPercent", (double)((Integer)cardStats.getOrDefault("memberCards", 0)) / totalCards * 100);
                cardStats.put("visitorCardsPercent", (double)((Integer)cardStats.getOrDefault("visitorCards", 0)) / totalCards * 100);
            }
        } finally {
            DBConnectionPool.closeConnection(conn);
        }
        
        return cardStats;
    }
}