package com.example.vehicle_management.servlets.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/","/admin/dashboard"})
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");


        if (page == null || page.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard?page=dashboard");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/dashboard/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
