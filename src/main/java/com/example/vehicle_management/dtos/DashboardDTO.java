package com.example.vehicle_management.dtos;

import java.util.List;
import java.util.Map;

public class DashboardDTO {
    private double totalRevenue;
    private int totalVisitors;
    private int totalRegisteredCards;
    private List<Map<String, Object>> revenueData;
    private List<Map<String, Object>> vehicleTypeData;
    private Map<String, Object> cardStats;

    public DashboardDTO() {
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalVisitors() {
        return totalVisitors;
    }

    public void setTotalVisitors(int totalVisitors) {
        this.totalVisitors = totalVisitors;
    }

    public int getTotalRegisteredCards() {
        return totalRegisteredCards;
    }

    public void setTotalRegisteredCards(int totalRegisteredCards) {
        this.totalRegisteredCards = totalRegisteredCards;
    }

    public List<Map<String, Object>> getRevenueData() {
        return revenueData;
    }

    public void setRevenueData(List<Map<String, Object>> revenueData) {
        this.revenueData = revenueData;
    }

    public List<Map<String, Object>> getVehicleTypeData() {
        return vehicleTypeData;
    }

    public void setVehicleTypeData(List<Map<String, Object>> vehicleTypeData) {
        this.vehicleTypeData = vehicleTypeData;
    }

    public Map<String, Object> getCardStats() {
        return cardStats;
    }

    public void setCardStats(Map<String, Object> cardStats) {
        this.cardStats = cardStats;
    }
}