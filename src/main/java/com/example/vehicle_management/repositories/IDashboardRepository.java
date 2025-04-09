package com.example.vehicle_management.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDashboardRepository {
    double getTotalRevenue() throws SQLException;
    int getTotalVisitors() throws SQLException;
    int getTotalRegisteredCards() throws SQLException;
    List<Map<String, Object>> getRevenueByTimeData() throws SQLException;
    List<Map<String, Object>> getVehicleTypeData() throws SQLException;
    Map<String, Object> getCardStatistics() throws SQLException;
}