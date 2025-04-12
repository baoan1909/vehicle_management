package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.ParkingFeeOfCustomerDTO;
import com.example.vehicle_management.mappers.ParkingFeeOfCustomerMapper;
import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositories.IParkingFeeOfCustomerRepository;
import com.example.vehicle_management.repositoriesImpl.ParkingFeeOfCustomerRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.TicketTypeRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.IParkingFeeOfCustomerService;
import com.example.vehicle_management.services.ITicketTypeService;
import com.example.vehicle_management.services.IVehicleTypeService;
import com.example.vehicle_management.servicesImpl.ParkingFeeOfCustomerServiceImpl;
import com.example.vehicle_management.servicesImpl.TicketTypeServiceImpl;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet({"/admin/parkingFeeOfCustomer","/admin/parkingFeeOfCustomer/edit","/admin/parkingFeeOfCustomer/add", "/admin/parkingFeeOfCustomer/delete","/admin/parkingFeeOfCustomer/save"})
public class ParkingFeeOfCustomerServlet extends HttpServlet {
    private  IParkingFeeOfCustomerService parkingFeeOfCustomerService;
    private  IVehicleTypeService vehicleTypeService;
    private  ITicketTypeService ticketTypeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        parkingFeeOfCustomerService=new ParkingFeeOfCustomerServiceImpl(new ParkingFeeOfCustomerRepositoryImpl());
        vehicleTypeService=new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
        ticketTypeService=new TicketTypeServiceImpl(new TicketTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=request.getRequestURI();
        List<VehicleType> vehicleTypeList=vehicleTypeService.getAllVehicleTypes();
        request.setAttribute("vehicleTypeList", vehicleTypeList);
        List<TicketType> ticketTypeList=ticketTypeService.getAllTicketTypes();
        request.setAttribute("ticketTypeList", ticketTypeList);
        if (uri.contains("edit")||uri.contains("add")) {
            int feeCustomerId=request.getParameter("id")==null ? 0 : Integer.parseInt(request.getParameter("id"));
            ParkingFeeOfCustomer parkingFeeOfCustomer = (feeCustomerId==0) ? new ParkingFeeOfCustomer() : parkingFeeOfCustomerService.getParkingFeeOfCustomerById(feeCustomerId);
            ParkingFeeOfCustomerDTO parkingFeeOfCustomerDTO=ParkingFeeOfCustomerMapper.toDTO(parkingFeeOfCustomer,ticketTypeService,vehicleTypeService);
            request.setAttribute("parkingFeeOfCustomerDTO", parkingFeeOfCustomerDTO);
            request.setAttribute("vehicleTypeList", vehicleTypeList);
            request.setAttribute("ticketTypeList", ticketTypeList);

            if(feeCustomerId!=0){
                java.util.Date formattedStartDate = java.sql.Date.valueOf(parkingFeeOfCustomerService.getParkingFeeOfCustomerById(feeCustomerId).getStartDate());
                request.setAttribute("formattedStartDate", formattedStartDate);
            }
            request.getRequestDispatcher("/views/admin/parkingFee/parkingFeeOfCustomer-detail.jsp").forward(request, response);
        }else if (uri.contains("delete")) {
            int feeCustomerId=Integer.parseInt(request.getParameter("id"));
            parkingFeeOfCustomerService.deleteParkingFeeOfCustomer(feeCustomerId);
            request.getRequestDispatcher("/admin/parkingFeeOfCustomer").forward(request, response);
        }else {
            List<ParkingFeeOfCustomer> parkingFeeOfCustomerList=parkingFeeOfCustomerService.getAllParkingFeeOfCustomers();
            List<ParkingFeeOfCustomerDTO> parkingFeeOfCustomerDTOList=ParkingFeeOfCustomerMapper.toDTOList(parkingFeeOfCustomerList,ticketTypeService,vehicleTypeService);
            request.setAttribute("parkingFeeOfCustomerDTOList", parkingFeeOfCustomerDTOList);

            request.getRequestDispatcher("/views/admin/parkingFee/parkingFeeOfCustomer.jsp").forward(request, response);
        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int feeCustomerId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
            int ticketTypeId = Integer.parseInt(request.getParameter("ticketTypeId"));
            int vehicleTypeId = Integer.parseInt(request.getParameter("vehicleTypeId"));
            Double price = Double.parseDouble(request.getParameter("price"));
            if (price == 0.0 || price==null)
            {
                request.setAttribute("error", "Giá vé không được để trống!");
                request.getRequestDispatcher("/views/admin/parkingFee/parkingFeeOfCustomer-detail.jsp").forward(request, response);

            }



        String startDateStr = request.getParameter("startDate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDate = null;
        try {
            startDate = LocalDate.parse(startDateStr, formatter);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ngày không đúng định dạng MM/dd/yyyy.");
        }


        ParkingFeeOfCustomer parkingFeeOfCustomer = new ParkingFeeOfCustomer(feeCustomerId, ticketTypeId, vehicleTypeId, price, startDate);

            if (feeCustomerId == 0) {
                parkingFeeOfCustomerService.insertParkingFeeOfCustomer(parkingFeeOfCustomer);
            } else {
                parkingFeeOfCustomerService.updateParkingFeeOfCustomer(parkingFeeOfCustomer);
            }

            response.sendRedirect(request.getContextPath() + "/admin/parkingFeeOfCustomer");


    }

}