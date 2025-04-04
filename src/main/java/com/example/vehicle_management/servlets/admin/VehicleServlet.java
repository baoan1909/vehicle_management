package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.IVehicleTypeService;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/vehicle", "/admin/vehicle/edit", "/admin/vehicle/add", "/admin/vehicle/delete", "/admin/vehicle/save"})
public class VehicleServlet extends HttpServlet {
    private IVehicleTypeService vehicleTypeService;

    @Override
    public void init() throws ServletException {
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String searchQuery = request.getParameter("search");

        if (uri.contains("edit") || uri.contains("add")) {
            int vehicleTypeId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            VehicleType vehicleType = (vehicleTypeId == 0) ? new VehicleType() : vehicleTypeService.getVehicleTypeById(vehicleTypeId);

            request.setAttribute("vehicleType", vehicleType);
            request.getRequestDispatcher("/views/admin/vehicle/vehicle-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int vehicleTypeId = Integer.parseInt(request.getParameter("id"));
            vehicleTypeService.deleteVehicleType(vehicleTypeId);
            response.sendRedirect(request.getContextPath() + "/admin/vehicle");
        } else {
            List<VehicleType> lstVehicleType = vehicleTypeService.getAllVehicleTypes();
            request.setAttribute("lstVehicleType", lstVehicleType);
            request.getRequestDispatcher("/views/admin/vehicle/vehicle.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleTypeId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        String vehicleTypeName = request.getParameter("vehicleTypeName");
        String description = request.getParameter("description");

        VehicleType vehicleType = new VehicleType(vehicleTypeId, vehicleTypeName, description);

        if (vehicleTypeId == 0) {
            vehicleTypeService.insertVehicleType(vehicleType);
        } else {
            vehicleTypeService.updateVehicleType(vehicleType);
        }
        response.sendRedirect(request.getContextPath() + "/admin/vehicle");
    }
}