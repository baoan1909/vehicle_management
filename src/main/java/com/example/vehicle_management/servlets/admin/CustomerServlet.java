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

@WebServlet({"/admin/customer","/admin/customer/add","/admin/customer/edit","/admin/customer/delete","/admin/customer/save", "/admin/customer/getCard", "/admin/customer/getFeeCustomer"})
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

        //Tự động lấy dữ liệu theo cardId khi chọn cardId
        if (uri.contains("getCard")) {
            String cardIdStr = request.getParameter("cardId");

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            if (cardIdStr != null) {
                int cardId = Integer.parseInt(cardIdStr);
                Card card = cardService.getCardById(cardId);

                if(card != null) {
                    VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(card.getVehicleTypeId());
                    jsonBuilder.append(String.format(
                            " \"vehicleTypeName\":\"%s\", \"vehicleTypeId\":\"%s\"",
                            vehicleType.getVehicleTypeName(),
                            vehicleType.getVehicleTypeId()
                    ));
                }else {
                    jsonBuilder.append(" \"message\": \"Thẻ này không tồn tại hoặc không có dữ liệu\"");
                }
            }else {
                jsonBuilder.append(" \"message\": \"Không tìm thấy thẻ này\"");
            }
            jsonBuilder.append("}");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonBuilder.toString());
            return;
        }

        //Tự động lấy dữ liệu theo vehicleTypeId và ticketTypeId
        if (uri.contains("getFeeCustomer")) {
            String vehicleTypeIdStr = request.getParameter("vehicleTypeId");
            String ticketTypeIdStr = request.getParameter("ticketTypeId");

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            if (vehicleTypeIdStr != null && ticketTypeIdStr != null) {
                int vehicleTypeId = Integer.parseInt(vehicleTypeIdStr);
                int ticketTypeId = Integer.parseInt(ticketTypeIdStr);

                if (vehicleTypeId != 0 && ticketTypeId != 0) {
                    ParkingFeeOfCustomer parkingFeeOfCustomer = parkingFeeOfCustomerService.findByTicketTypeAndVehicleType(ticketTypeId, vehicleTypeId);
                    if(parkingFeeOfCustomer != null) {
                        jsonBuilder.append(String.format(
                                " \"feeCustomerId\":\"%s\", \"price\":\"%s\"",
                                parkingFeeOfCustomer.getFeeCustomerId(),
                                parkingFeeOfCustomer.getPrice()
                        ));
                    }else {
                        jsonBuilder.append(" \"message\": \"Không tìm thấy gói phí phù hợp với loại vé và loại xe. \"");
                    }
                }else {
                    jsonBuilder.append(" \"message\": \"Không tìm thấy loại vé và loại xe. \"");
                }
            }else {
                jsonBuilder.append(" \"message\": \"Không tìm thấy ID Loại xe và ID loại vé\"");
            }
            jsonBuilder.append("}");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonBuilder.toString());
            return;
        }


        if (uri.contains("edit") || uri.contains("add")) {
            int customerRegisterTicketId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            CustomerRegisterTicket customerRegisterTicket = (customerRegisterTicketId == 0) ? new CustomerRegisterTicket() : customerRegisterTicketService.getCustomerRegisterTicketById(customerRegisterTicketId);
            CustomerDTO customerDTO= CustomerMapper.toDTO(customerRegisterTicket, customerService, cardService, parkingFeeOfCustomerService, vehicleTypeService, ticketTypeService);
            List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
            List<TicketType> ticketTypes = ticketTypeService.getAllTicketTypes();
            List<Card> cards = cardService.getCardIdByType();
            String vehicleName = "";

            if (customerDTO.getVehicleTypeId() != 0) {
                VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(customerDTO.getVehicleTypeId());
                if (vehicleType != null) {
                    vehicleName = vehicleType.getVehicleTypeName();
                }
            }

            // Format ngày tháng từ LocalDateTime về chuỗi
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String dateOfBirthStr = (customerDTO.getDateOfBirth() != null) ? customerDTO.getDateOfBirth().format(formatter) : "";
            String effectiveDateStr = (customerDTO.getEffectiveDate() != null) ? customerDTO.getEffectiveDate().format(formatter) : "";
            String expirationDateStr = (customerDTO.getExpirationDate() != null) ? customerDTO.getExpirationDate().format(formatter) : "";

            request.setAttribute("customerDTO", customerDTO);
            request.setAttribute("displayVehicleName", vehicleName);
            request.setAttribute("dateOfBirth", dateOfBirthStr);
            request.setAttribute("effectiveDate", effectiveDateStr);
            request.setAttribute("expirationDate", expirationDateStr);
            request.setAttribute("vehicleTypes", vehicleTypes);
            request.setAttribute("ticketTypes", ticketTypes);
            request.setAttribute("cards", cards);
            request.getRequestDispatcher("/views/admin/customer/customer-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int customerRegisterTicketId = Integer.parseInt(request.getParameter("id"));
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            customerRegisterTicketService.deleteCustomerRegisterTicket(customerRegisterTicketId);
            customerService.deleteCustomer(customerId);
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
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String identifyCard = request.getParameter("identifyCard");

        int cardId = request.getParameter("cardId").isEmpty() ? 0 : Integer.parseInt(request.getParameter("cardId"));
        int feeCustomerId = (request.getParameter("feeCustomerId").isEmpty()) ? 0 : Integer.parseInt(request.getParameter("feeCustomerId"));
        String effectiveDateStr = request.getParameter("effectiveDate");
        String expirationDateStr = request.getParameter("expirationDate");
        String licensePlate = request.getParameter("licensePlate");
        int ticketTypeId = request.getParameter("ticketTypeId").isEmpty()? 0 : Integer.parseInt(request.getParameter("ticketTypeId"));
        int vehicleTypeId = request.getParameter("vehicleTypeId").isEmpty() ? 0 : Integer.parseInt(request.getParameter("vehicleTypeId"));
        double price =request.getParameter("price").isEmpty() ? 0.0 : Double.parseDouble(request.getParameter("price"));

        // Định dạng ngày
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateOfBirth = null;
        LocalDate effectiveDate = null;
        LocalDate expirationDate = null;

        try {
            if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
                String cleanedDateOfBirth = dateOfBirthStr.split(" ")[0];
                dateOfBirth = LocalDate.parse(cleanedDateOfBirth, dateFormatter);
            }

            if (effectiveDateStr != null && !effectiveDateStr.isEmpty()) {
                String cleanedEffectiveDate = effectiveDateStr.split(" ")[0];
                effectiveDate = LocalDate.parse(cleanedEffectiveDate, dateFormatter);
            }

            if (expirationDateStr != null && !expirationDateStr.isEmpty()) {
                String cleanedExpirationDate = expirationDateStr.split(" ")[0];
                expirationDate = LocalDate.parse(cleanedExpirationDate, dateFormatter);
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ngày không đúng định dạng MM/dd/yyyy.");
            request.getRequestDispatcher("/views/admin/customer/customer-detail.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(customerId, fullName, dateOfBirth, gender, phoneNumber, address, identifyCard);

        int existCustomerUsePhoneNumber = customerService.getCustomerIdByPhoneNumber(phoneNumber);
        if(existCustomerUsePhoneNumber != 0){
            request.setAttribute("error", "Số điện thoại này đã có khách hàng sử dụng");
            request.setAttribute("customerDTO", customer);
            request.getRequestDispatcher("/views/admin/customer/customer-detail.jsp").forward(request, response);
            return;
        }

        if (customerId == 0) {
           int newCustomerId = customerService.insertAndReturnId(customer);

            CustomerRegisterTicket customerRegisterTicket = new CustomerRegisterTicket(
                    customerRegisterTicketId, cardId, newCustomerId, feeCustomerId,
                    effectiveDate, expirationDate, licensePlate, vehicleTypeId, ticketTypeId, price
            );

            customerRegisterTicketService.insertCustomerRegisterTicket(customerRegisterTicket);
        } else {
            CustomerRegisterTicket customerRegisterTicket = new CustomerRegisterTicket(
                    customerRegisterTicketId, cardId, customerId, feeCustomerId,
                    effectiveDate, expirationDate, licensePlate, vehicleTypeId, ticketTypeId, price
            );
            customerRegisterTicketService.updateCustomerRegisterTicket(customerRegisterTicket);
            customerService.updateCustomer(customer);

        }
        response.sendRedirect(request.getContextPath() + "/admin/customer");
    }
}
