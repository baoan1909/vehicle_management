package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CustomerDTO;
import com.example.vehicle_management.mappers.CustomerMapper;
import com.example.vehicle_management.models.*;
import com.example.vehicle_management.repositoriesImpl.*;
import com.example.vehicle_management.services.*;
import com.example.vehicle_management.servicesImpl.*;
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
        List<VehicleType> vehicleTypeList= vehicleTypeService.getAllVehicleTypes();
        request.setAttribute("vehicleTypeList", vehicleTypeList);
        List<TicketType> ticketTypeList= ticketTypeService.getAllTicketTypes();
        request.setAttribute("ticketTypeList", ticketTypeList);

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
            String vehicleTypeId= request.getParameter("vehicleTypeId");
            String ticketTypeId= request.getParameter("ticketTypeId");
            String dateRange= request.getParameter("dateRange");

            HttpSession session = request.getSession();
            session.setAttribute("vehicleTypeId", vehicleTypeId);
            session.setAttribute("ticketTypeId", ticketTypeId);
            request.setAttribute("vehicleTypeFilter",session.getAttribute("vehicleTypeId") );
            request.setAttribute("ticketTypeFilter",session.getAttribute("ticketTypeId") );



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

            List<CustomerRegisterTicket> customerRegisterTickets = customerRegisterTicketService.getAllCustomerRegisterTickets();
//            List<CustomerDTO> customerDTOList = CustomerMapper.toDTOList(customerRegisterTickets,customerService, cardService, parkingFeeOfCustomerService, vehicleTypeService, ticketTypeService );
            List<CustomerDTO> customerDTOList = customerRegisterTickets.stream()
                    .filter(p -> {
                        if (vehicleTypeId == null || vehicleTypeId.isEmpty()) return true;
                        try {
                            return p.getVehicleTypeId() == Integer.parseInt(vehicleTypeId);
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    })
                    .filter(p -> {
                        if (ticketTypeId == null || ticketTypeId.isEmpty()) return true;
                        try {
                            return p.getTicketTypeId() == Integer.parseInt(ticketTypeId);
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    })
                    .filter(p -> {
                        if (finalStartDate != null && finalEndDate != null) {
                            return (p.getEffectiveDate() != null &&
                                    (p.getEffectiveDate().isEqual(finalStartDate) || p.getEffectiveDate().isAfter(finalStartDate)) &&
                                    (p.getEffectiveDate().isEqual(finalEndDate) || p.getEffectiveDate().isBefore(finalEndDate)));
                        }
                        return true;
                    })
                    .map(p-> CustomerMapper.toDTO(p,customerService, cardService, parkingFeeOfCustomerService, vehicleTypeService, ticketTypeService) )
                    .toList();
            request.setAttribute("customerDTOList", customerDTOList);
            request.getRequestDispatcher("/views/admin/customer/customer.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ request
            int customerRegisterTicketId = parseInt(request.getParameter("id"));
            int customerId = parseInt(request.getParameter("customerId"));
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String identifyCard = request.getParameter("identifyCard");
            String licensePlate = request.getParameter("licensePlate");

            int cardId = parseInt(request.getParameter("cardId"));
            int feeCustomerId = parseInt(request.getParameter("feeCustomerId"));
            int ticketTypeId = parseInt(request.getParameter("ticketTypeId"));
            int vehicleTypeId = parseInt(request.getParameter("vehicleTypeId"));
            double price = parseDouble(request.getParameter("price"));

            // Xử lý ngày
            LocalDate dateOfBirth = parseDate(request.getParameter("dateOfBirth"));
            LocalDate effectiveDate = parseDate(request.getParameter("effectiveDate"));
            LocalDate expirationDate = parseDate(request.getParameter("expirationDate"));

            // Gán dữ liệu cho DTO để hiển thị lại nếu có lỗi
            CustomerDTO customerDTO = new CustomerDTO(customerId, fullName, dateOfBirth, gender, phoneNumber, address, identifyCard,
                    cardId, feeCustomerId, effectiveDate, expirationDate,
                    licensePlate, ticketTypeId, vehicleTypeId, price);

            // Kiểm tra trùng số điện thoại (trừ trường hợp của chính mình)
            int existingId = customerService.getCustomerIdByPhoneNumber(phoneNumber);
            if (existingId != 0 && existingId != customerId) {
                setRequestAttributes(request, customerDTO, "Số điện thoại này đã có khách hàng sử dụng");
                returnToForm(request, response);
                return;
            }

            // Tạo đối tượng Customer và CustomerRegisterTicket
            Customer customer = new Customer(customerId, fullName, dateOfBirth, gender, phoneNumber, address, identifyCard);
            CustomerRegisterTicket registerTicket = new CustomerRegisterTicket(customerRegisterTicketId, cardId,
                    customerId, feeCustomerId, effectiveDate, expirationDate, licensePlate, vehicleTypeId, ticketTypeId, price);

            if (customerId == 0) {
                int newCustomerId = customerService.insertAndReturnId(customer);
                registerTicket.setCustomerId(newCustomerId);
                customerRegisterTicketService.insertCustomerRegisterTicket(registerTicket);
            } else {
                customerService.updateCustomer(customer);
                customerRegisterTicketService.updateCustomerRegisterTicket(registerTicket);
            }

            response.sendRedirect(request.getContextPath() + "/admin/customer");

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            CustomerDTO dto = extractCustomerDTOFromRequest(request);
            setRequestAttributes(request, dto, "Ngày không đúng định dạng MM/dd/yyyy.");
            returnToForm(request, response);
        }
    }

    private int parseInt(String value) {
        return (value == null || value.isEmpty()) ? 0 : Integer.parseInt(value);
    }

    private double parseDouble(String value) {
        return (value == null || value.isEmpty()) ? 0.0 : Double.parseDouble(value);
    }

    private LocalDate parseDate(String dateStr) throws DateTimeParseException {
        if (dateStr == null || dateStr.isEmpty()) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(dateStr.split(" ")[0], formatter);
    }

    private CustomerDTO extractCustomerDTOFromRequest(HttpServletRequest request) {
        return new CustomerDTO(
                parseInt(request.getParameter("customerId")),
                request.getParameter("fullName"),
                null,  // dateOfBirth được xử lý trong try
                request.getParameter("gender"),
                request.getParameter("phoneNumber"),
                request.getParameter("address"),
                request.getParameter("identifyCard"),
                parseInt(request.getParameter("cardId")),
                parseInt(request.getParameter("feeCustomerId")),
                null,  // effectiveDate
                null,  // expirationDate
                request.getParameter("licensePlate"),
                parseInt(request.getParameter("ticketTypeId")),
                parseInt(request.getParameter("vehicleTypeId")),
                parseDouble(request.getParameter("price"))
        );
    }

    private void setRequestAttributes(HttpServletRequest request, CustomerDTO customerDTO, String errorMessage) {
        request.setAttribute("error", errorMessage);
        request.setAttribute("customerDTO", customerDTO);
    }

    private void returnToForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
        List<TicketType> ticketTypes = ticketTypeService.getAllTicketTypes();
        List<Card> cards = cardService.getCardIdByType();

        request.setAttribute("vehicleTypes", vehicleTypes);
        request.setAttribute("ticketTypes", ticketTypes);
        request.setAttribute("cards", cards);

        // Lấy tên loại xe để hiển thị
        CustomerDTO dto = (CustomerDTO) request.getAttribute("customerDTO");
        String vehicleName = "";
        if (dto != null && dto.getVehicleTypeId() != 0) {
            VehicleType vt = vehicleTypeService.getVehicleTypeById(dto.getVehicleTypeId());
            if (vt != null) vehicleName = vt.getVehicleTypeName();
        }
        request.setAttribute("displayVehicleName", vehicleName);

        // Set lại ngày tháng nếu có
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        if (dto != null) {
            request.setAttribute("dateOfBirth", dto.getDateOfBirth() != null ? dto.getDateOfBirth().format(formatter) : "");
            request.setAttribute("effectiveDate", dto.getEffectiveDate() != null ? dto.getEffectiveDate().format(formatter) : "");
            request.setAttribute("expirationDate", dto.getExpirationDate() != null ? dto.getExpirationDate().format(formatter) : "");
        }

        request.getRequestDispatcher("/views/admin/customer/customer-detail.jsp").forward(request, response);
    }
}
