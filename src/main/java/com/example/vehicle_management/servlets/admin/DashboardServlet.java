package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.DashboardDTO;
import com.example.vehicle_management.repositories.IDashboardRepository;
import com.example.vehicle_management.repositoriesImpl.DashboardRepositoryImpl;
import com.example.vehicle_management.services.IDashboardService;
import com.example.vehicle_management.servicesImpl.DashboardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet({"/admin/dashboard"})
public class DashboardServlet extends HttpServlet {
    private IDashboardService dashboardService;

    @Override
    public void init() throws ServletException {
        IDashboardRepository dashboardRepository = new DashboardRepositoryImpl();
        this.dashboardService = new DashboardServiceImpl(dashboardRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DashboardDTO dashboardData = dashboardService.getDashboardData();
    
    // In dữ liệu ra để kiểm tra
    System.out.println("===== DEBUG DASHBOARD DATA =====");
    System.out.println("Total Revenue: " + dashboardData.getTotalRevenue());
    System.out.println("Total Visitors: " + dashboardData.getTotalVisitors());
    System.out.println("Total Registered Cards: " + dashboardData.getTotalRegisteredCards());
    
    System.out.println("Revenue Data: ");
    if (dashboardData.getRevenueData() != null) {
        for (Map<String, Object> revenue : dashboardData.getRevenueData()) {
            System.out.println("  Month: " + revenue.get("month") + ", Revenue: " + revenue.get("revenue"));
        }
    } else {
        System.out.println("  Revenue Data is NULL");
    }
    
    System.out.println("Vehicle Type Data: ");
    if (dashboardData.getVehicleTypeData() != null) {
        for (Map<String, Object> vehicle : dashboardData.getVehicleTypeData()) {
            System.out.println("  Type: " + vehicle.get("type") + ", Count: " + vehicle.get("count"));
        }
    } else {
        System.out.println("  Vehicle Type Data is NULL");
    }
    
    System.out.println("Card Stats: ");
    if (dashboardData.getCardStats() != null) {
        System.out.println("  Lost Cards %: " + dashboardData.getCardStats().get("lostCardsPercent"));
        System.out.println("  Member Cards %: " + dashboardData.getCardStats().get("memberCardsPercent"));
        System.out.println("  Visitor Cards %: " + dashboardData.getCardStats().get("visitorCardsPercent"));
    } else {
        System.out.println("  Card Stats is NULL");
    }
    System.out.println("================================");
    
    request.setAttribute("totalRevenue", dashboardData.getTotalRevenue());
    request.setAttribute("totalVisitors", dashboardData.getTotalVisitors());
    request.setAttribute("totalRegisteredCards", dashboardData.getTotalRegisteredCards());
    request.setAttribute("revenueData", dashboardData.getRevenueData());
    request.setAttribute("vehicleTypeData", dashboardData.getVehicleTypeData());
    request.setAttribute("cardStats", dashboardData.getCardStats());
    
    request.getRequestDispatcher("/views/admin/dashboard/dashboard.jsp").forward(request, response);
    }
}