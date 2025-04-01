package com.example.vehicle_management.servlets.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/parkingFeeOfCustomer/parkingFeeOfCustomer-detail")
public class ParkingFeeOfCustomerDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/admin/parkingFee/parkingFeeOfCustomer-detail.jsp");
        dispatcher.forward(request, response);
    }
}