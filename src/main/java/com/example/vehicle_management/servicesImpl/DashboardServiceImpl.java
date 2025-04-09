package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.dtos.DashboardDTO;
import com.example.vehicle_management.repositories.IDashboardRepository;
import com.example.vehicle_management.services.IDashboardService;

import java.sql.SQLException;

public class DashboardServiceImpl implements IDashboardService {
    private final IDashboardRepository dashboardRepository;

    public DashboardServiceImpl(IDashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public DashboardDTO getDashboardData() {
        DashboardDTO dto = new DashboardDTO();
        
        try {
            dto.setTotalRevenue(dashboardRepository.getTotalRevenue());
            dto.setTotalVisitors(dashboardRepository.getTotalVisitors());
            dto.setTotalRegisteredCards(dashboardRepository.getTotalRegisteredCards());
            dto.setRevenueData(dashboardRepository.getRevenueByTimeData());
            dto.setVehicleTypeData(dashboardRepository.getVehicleTypeData());
            dto.setCardStats(dashboardRepository.getCardStatistics());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dto;
    }
}