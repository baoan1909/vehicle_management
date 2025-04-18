package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.ParkingFeeOfCustomerDTO;
import com.example.vehicle_management.dtos.ParkingFeeOfVisitorDTO;
import com.example.vehicle_management.mappers.ParkingFeeOfCustomerMapper;
import com.example.vehicle_management.mappers.ParkingFeeOfVisitorMapper;
import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.models.ParkingFeeOfVisitor;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositoriesImpl.ParkingFeeOfVisitorRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.IParkingFeeOfCustomerService;
import com.example.vehicle_management.services.IParkingFeeOfVisitorService;
import com.example.vehicle_management.services.IVehicleTypeService;
import com.example.vehicle_management.servicesImpl.ParkingFeeOfVisitorServiceImpl;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet({"/admin/visitorParkingFee","/admin/visitorParkingFee/add","/admin/visitorParkingFee/edit","/admin/visitorParkingFee/delete","/admin/visitorParkingFee/save"})
public class ParkingFeeOfVisitorServlet extends HttpServlet {
    private IParkingFeeOfVisitorService parkingFeeOfVisitorService;
    private IVehicleTypeService vehicleTypeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        parkingFeeOfVisitorService=new ParkingFeeOfVisitorServiceImpl(new ParkingFeeOfVisitorRepositoryImpl());
        vehicleTypeService=new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri=request.getRequestURI();
        List<VehicleType> vehicleTypeList=vehicleTypeService.getAllVehicleTypes();
        request.setAttribute("vehicleTypeList", vehicleTypeList);

        if (uri.contains("edit")||uri.contains("add")) {
            int feeVisitorId=request.getParameter("id")==null ? 0 : Integer.parseInt(request.getParameter("id"));
            ParkingFeeOfVisitor parkingFeeOfVisitor = (feeVisitorId==0) ? new ParkingFeeOfVisitor() : parkingFeeOfVisitorService.getParkingFeeOfVisitorById(feeVisitorId);
            ParkingFeeOfVisitorDTO parkingFeeOfVisitorDTO= ParkingFeeOfVisitorMapper.toDTO(parkingFeeOfVisitor,vehicleTypeService);
            request.setAttribute("parkingFeeOfVisitorDTO", parkingFeeOfVisitorDTO);
            request.setAttribute("vehicleTypeList", vehicleTypeList);
            //format ngày MM/dd/yyyy
            if(feeVisitorId!=0){
                java.util.Date formattedStartDate = java.sql.Date.valueOf(parkingFeeOfVisitorService.getParkingFeeOfVisitorById(feeVisitorId).getStartDate());
                request.setAttribute("formattedStartDate", formattedStartDate);
            }
            request.getRequestDispatcher("/views/admin/parkingFee/visitorParkingFee-detail.jsp").forward(request, response);
        }else if (uri.contains("delete")) {
            int feeVisitorId=Integer.parseInt(request.getParameter("id"));
            parkingFeeOfVisitorService.deleteParkingFeeOfVisitor(feeVisitorId);
            request.getRequestDispatcher("/admin/visitorParkingFee").forward(request, response);
        }else {
            String vehicleTypeId = request.getParameter("vehicleTypeId");
            String dateRange = request.getParameter("dateRange");

            HttpSession session = request.getSession();
            session.setAttribute("vehicleTypeId", vehicleTypeId != null ? vehicleTypeId : "");


            // Truyền xuống JSP qua request
            request.setAttribute("vehicleTypeFilter",session.getAttribute("vehicleTypeId") );



            LocalDate startDate = null;
            LocalDate endDate = null;
            if (dateRange != null && !dateRange.isEmpty()) {
                try {
                    String[] dates = dateRange.split(" - ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    startDate = LocalDate.parse(dates[0], formatter);
                    endDate = LocalDate.parse(dates[1], formatter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            final LocalDate finalStartDate = startDate;
            final LocalDate finalEndDate = endDate;

            session.setAttribute("startDateFilter", startDate);
            session.setAttribute("endDateFilter", endDate);

            request.setAttribute("startDate", session.getAttribute("startDateFilter"));
            request.setAttribute("endDate", session.getAttribute("endDateFilter"));


            // Lấy danh sách và lọc theo điều kiện
            List<ParkingFeeOfVisitor> parkingFeeOfVisitorList = parkingFeeOfVisitorService.getAllParkingFeeOfVisitors();

            List<ParkingFeeOfVisitorDTO> filteredList = parkingFeeOfVisitorList.stream()
                    .filter(p -> {
                        if (vehicleTypeId == null || vehicleTypeId.isEmpty()) return true;
                        try {
                            return p.getVehicleTypeId() == Integer.parseInt(vehicleTypeId);
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    })

                    .filter(p -> {
                        if (finalStartDate != null && finalEndDate != null) {
                            return (p.getStartDate() != null &&
                                    (p.getStartDate().isEqual(finalStartDate) || p.getStartDate().isAfter(finalStartDate)) &&
                                    (p.getStartDate().isEqual(finalEndDate) || p.getStartDate().isBefore(finalEndDate)));
                        }
                        return true;
                    })
                    .map(p -> ParkingFeeOfVisitorMapper.toDTO(p, vehicleTypeService))
                    .toList();

            request.setAttribute("parkingFeeOfVisitorDTOList", filteredList);
            request.getRequestDispatcher("/views/admin/parkingFee/visitorParkingFee.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feeVisitorId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        int vehicleTypeId = Integer.parseInt(request.getParameter("vehicleTypeId"));
        double price=Double.parseDouble(request.getParameter("price"));
        String startDateStr = request.getParameter("startDate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDate = null;
        try {
            startDate = LocalDate.parse(startDateStr, formatter);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ngày không đúng định dạng MM/dd/yyyy.");
        }

        ParkingFeeOfVisitor parkingFeeOfVisitor = new ParkingFeeOfVisitor(feeVisitorId,  price, vehicleTypeId, startDate);

        if (feeVisitorId == 0) {
            parkingFeeOfVisitorService.insertParkingFeeOfVisitor(parkingFeeOfVisitor);
        } else {
            parkingFeeOfVisitorService.updateParkingFeeOfVisitor(parkingFeeOfVisitor);
        }

        response.sendRedirect(request.getContextPath() + "/admin/visitorParkingFee");

    }
}