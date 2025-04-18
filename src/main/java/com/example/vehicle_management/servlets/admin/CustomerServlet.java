package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardDTO;
import com.example.vehicle_management.dtos.CustomerDTO;
import com.example.vehicle_management.mappers.CardMapper;
import com.example.vehicle_management.mappers.CustomerMapper;
import com.example.vehicle_management.mappers.CustomerRegisterTicketMapper;
import com.example.vehicle_management.models.*;
import com.example.vehicle_management.repositoriesImpl.*;
import com.example.vehicle_management.services.*;
import com.example.vehicle_management.servicesImpl.*;
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
import java.util.Date;
import java.util.List;

@WebServlet({"/admin/customer","/admin/customer/add","/admin/customer/edit","/admin/customer/delete","/admin/customer/save"})
public class CustomerServlet extends HttpServlet {
    private ICardService cardService;
    private ICustomerService customerService;
    private ICustomerRegisterTicketService customerRegisterTicketService;
    private IParkingFeeOfCustomerService parkingFeeOfCustomerService;
    private IVehicleTypeService vehicleTypeService;
    private ITicketTypeService ticketTypeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        cardService = new CardServiceImpl(new CardRepositoryImpl());
        customerService = new CustomerServiceImpl(new CustomerRepositoryImpl());
        customerRegisterTicketService = new CustomerRegisterTicketServiceImpl(new CustomerRegisterTicketRepositoryImpl());
        parkingFeeOfCustomerService = new ParkingFeeOfCustomerServiceImpl(new ParkingFeeOfCustomerRepositoryImpl());
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
        ticketTypeService = new TicketTypeServiceImpl(new TicketTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        List<VehicleType> vehicleTypeList=vehicleTypeService.getAllVehicleTypes();
        request.setAttribute("vehicleTypeList", vehicleTypeList);
        List<TicketType> ticketTypeList=ticketTypeService.getAllTicketTypes();
        request.setAttribute("ticketTypeList", ticketTypeList);
        if (uri.contains("edit") || uri.contains("add")) {
            int customerRegisterTicketId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            CustomerRegisterTicket customerRegisterTicket = (customerRegisterTicketId == 0) ? new CustomerRegisterTicket() : customerRegisterTicketService.getCustomerRegisterTicketById(customerRegisterTicketId);
            CustomerDTO customerDTO= CustomerMapper.toDTO(customerRegisterTicket, customerService, cardService, parkingFeeOfCustomerService, vehicleTypeService, ticketTypeService);
            List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
            List<TicketType> ticketTypes = ticketTypeService.getAllTicketTypes();

            request.setAttribute("customerDTO", customerDTO);
            request.setAttribute("vehicleTypes", vehicleTypes);
            request.setAttribute("ticketTypes", ticketTypes);

            if(customerRegisterTicketId!=0){
                int customerId=customerRegisterTicketService.getCustomerRegisterTicketById(customerRegisterTicketId).getCustomerId();
                java.util.Date formattedDateOfBirth = java.sql.Date.valueOf(customerService.getCustomerById(customerId).getDateOfBirth());
                request.setAttribute("formattedDateOfBirth", formattedDateOfBirth);
            }
            if(customerRegisterTicketId!=0){
                java.util.Date formattedEffectiveDate = java.sql.Date.valueOf(customerRegisterTicketService.getCustomerRegisterTicketById(customerRegisterTicketId).getEffectiveDate());
                request.setAttribute("formattedEffectiveDate", formattedEffectiveDate);
            }
            if(customerRegisterTicketId!=0){
                java.util.Date formattedExpirationDate = java.sql.Date.valueOf(customerRegisterTicketService.getCustomerRegisterTicketById(customerRegisterTicketId).getExpirationDate());
                request.setAttribute("formattedExpirationDate", formattedExpirationDate);
            }

            request.getRequestDispatcher("/views/admin/customer/customer-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int cardId = Integer.parseInt(request.getParameter("id"));
            cardService.deleteCard(cardId);
            response.sendRedirect(request.getContextPath() + "/admin/customer");
        } else {
            List<CustomerRegisterTicket> customerRegisterTickets = customerRegisterTicketService.getAllCustomerRegisterTickets();
            List<CustomerDTO> customerDTOList = CustomerMapper.toDTOList(customerRegisterTickets,customerService, cardService, parkingFeeOfCustomerService, vehicleTypeService, ticketTypeService );
            request.setAttribute("customerDTOList", customerDTOList);
            request.getRequestDispatcher("/views/admin/customer/customer.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerRegisterTicketId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        int customerId = request.getParameter("customerId").isEmpty() ? 0 : Integer.parseInt(request.getParameter("customerId"));

        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int feeCustomerId = (request.getParameter("feeCustomerId").isEmpty()) ? 0 : Integer.parseInt(request.getParameter("feeCustomerId"));
        String email = request.getParameter("email");
        String identifyCard = request.getParameter("identifyCard");
        int ticketTypeId = request.getParameter("ticketTypeId").isEmpty()? 0 : Integer.parseInt(request.getParameter("ticketTypeId"));
        int vehicleTypeId = request.getParameter("vehicleTypeId").isEmpty() ? 0 : Integer.parseInt(request.getParameter("vehicleTypeId"));
        String licensePlate = request.getParameter("licensePlate");
        String cardNumber = request.getParameter("cardNumber");
        Double price =request.getParameter("price").isEmpty() ? 0.0 : Double.parseDouble(request.getParameter("price"));
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String effectiveDateStr = request.getParameter("effectiveDate");
        String expirationDateStr = request.getParameter("expirationDate");

        //Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateOfBirth= null;
        try {
            dateOfBirthStr = dateOfBirthStr.split(" ")[0];
            dateOfBirth = LocalDate.parse(dateOfBirthStr, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ngày hiệu lực không đúng định dạng MM/dd/yyyy.");
        }
        LocalDate effectiveDate= null;
        try {
            effectiveDateStr = effectiveDateStr.split(" ")[0];
             effectiveDate = LocalDate.parse(effectiveDateStr, formatter);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ngày hiệu lực không đúng định dạng MM/dd/yyyy.");
        }
        LocalDate expirationDate=null;
        try {
            expirationDateStr = expirationDateStr.split(" ")[0];
             expirationDate = LocalDate.parse(expirationDateStr, formatter);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ngày hết hạn không đúng định dạng MM/dd/yyyy.");
        }


        int cardId = cardService.getCardIdByCardNumber(cardNumber);

        Customer customer=new Customer(customerId,fullName, dateOfBirth,gender,phoneNumber,address, identifyCard);

        if (feeCustomerId==0){
            customerService.insertCustomer(customer);
            //customerId=customerService.getCustomerByEmail(email);
            customerId=1;
            CustomerRegisterTicket customerRegisterTicket = new CustomerRegisterTicket(customerRegisterTicketId,cardId,customerId,feeCustomerId,effectiveDate,expirationDate,licensePlate,vehicleTypeId,ticketTypeId,price);
            customerRegisterTicketService.insertCustomerRegisterTicket(customerRegisterTicket);
        }
        else {
            CustomerRegisterTicket customerRegisterTicket = new CustomerRegisterTicket(customerRegisterTicketId,cardId,customerId,feeCustomerId,effectiveDate,expirationDate,licensePlate,vehicleTypeId,ticketTypeId,price);
            customerRegisterTicketService.updateCustomerRegisterTicket(customerRegisterTicket);
            customerService.updateCustomer(customer);
        }

        response.sendRedirect(request.getContextPath() + "/admin/customer");

    }
}
