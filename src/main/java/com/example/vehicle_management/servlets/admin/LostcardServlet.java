package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardSwipeDTO;
import com.example.vehicle_management.dtos.LostCardDTO;
import com.example.vehicle_management.mappers.CardSwipeMapper;
import com.example.vehicle_management.mappers.LostCardMapper;
import com.example.vehicle_management.mappers.ParkingFeeOfCustomerMapper;
import com.example.vehicle_management.models.*;
import com.example.vehicle_management.repositoriesImpl.*;
import com.example.vehicle_management.services.*;
import com.example.vehicle_management.servicesImpl.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet({"/admin/lost", "/admin/lost/edit", "/admin/lost/add", "/admin/lost/delete", "/admin/lost/save", "/admin/lost/getcustomer"})
public class LostcardServlet extends HttpServlet {
    private ILostCardService lostCardService;
    private ICardService cardService;
    private IVehicleTypeService vehicleTypeService;
    private ICustomerService customerService;
    private ICardSwipeService cardSwipeService;
    private ITicketTypeService ticketTypeService;

    @Override
    public void init() throws ServletException {
        lostCardService = new LostCardServiceImpl(new LostCardRepositoryImpl());
        cardService = new CardServiceImpl(new CardRepositoryImpl());
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
        customerService = new CustomerServiceImpl(new CustomerRepositoryImpl());
        cardSwipeService = new CardSwipeServiceImpl(new CardSwipeRepositoryImpl());
        ticketTypeService = new TicketTypeServiceImpl(new TicketTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        List<VehicleType> vehicleTypeList= vehicleTypeService.getAllVehicleTypes();
        request.setAttribute("vehicleTypeList", vehicleTypeList);
        List<TicketType> ticketTypesList= ticketTypeService.getAllTicketTypes();
        request.setAttribute("ticketTypeList", ticketTypesList);

        if (uri.contains("getcustomer")) {
            String cardIdStr = request.getParameter("cardId");

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            if (cardIdStr != null) {
                int cardId = Integer.parseInt(cardIdStr);
                CardSwipe cardSwipe = cardSwipeService.getCardSwipeByCardId(cardId);
                Card card = cardService.getCardById(cardId);
                double cardPriceVisitor = cardService.getParkingFeeVisitorByCardId(cardId);
                int customerByCardId = cardService.getCustomerIdByCardId(cardId);

                if (cardSwipe != null && card != null) {
                    if (customerByCardId != 0) {
                        // Có thông tin khách hàng
                        Customer customer = customerService.getCustomerById(customerByCardId);

                        jsonBuilder.append(String.format(
                                " \"type\":\"%s\", \"checkInImagePath\":\"%s\", \"customerId\":\"%s\", \"fullName\":\"%s\", \"phoneNumber\":\"%s\", \"identifyCard\":\"%s\"",
                                card.getType(),
                                cardSwipe.getCheckInImagePath(),
                                customerByCardId,
                                customer.getFullName(),
                                customer.getPhoneNumber(),
                                customer.getIdentifyCard()
                        ));
                    } else {
                        // Không có customerId => là khách vãng lai
                        jsonBuilder.append(String.format(
                                " \"type\":\"%s\", \"checkInImagePath\":\"%s\", \"price\":\"%s\"",
                                card.getType(),
                                cardSwipe.getCheckInImagePath(),
                                cardPriceVisitor
                        ));
                    }
                } else {
                    //Trường hợp không cardswipe nhưng là khách hàng đăng ký
                    if(customerByCardId != 0) {
                        // Có thông tin khách hàng
                        Customer customer = customerService.getCustomerById(customerByCardId);

                        jsonBuilder.append(String.format(
                                " \"type\":\"%s\", \"customerId\":\"%s\", \"fullName\":\"%s\", \"phoneNumber\":\"%s\", \"identifyCard\":\"%s\"",
                                card.getType(),
                                customerByCardId,
                                customer.getFullName(),
                                customer.getPhoneNumber(),
                                customer.getIdentifyCard()
                        ));
                    }else {
                        jsonBuilder.append(" \"message\": \"Thẻ này đã được quẹt THANH TOÁN hoặc CHƯA VÀO cổng\"");
                    }
                }

            } else {
                jsonBuilder.append(" \"message\": \"Không tìm thấy thẻ này\"");
            }

            jsonBuilder.append("}");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonBuilder.toString());
            return;
        }

        if (uri.contains("edit") || uri.contains("add")) {
            int lostCardId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            LostCard lostCard = (lostCardId == 0) ? new LostCard() : lostCardService.getLostCardById(lostCardId);
            List<Card> cards = cardService.getAllCards();
            List<Customer> customers = customerService.getAllCustomers();
            List<CardSwipe> cardSwipes = cardSwipeService.getAllCardSwipes();
            List<CardSwipeDTO> lstCardSwipe = CardSwipeMapper.toDTOList(cardSwipes, cardService, vehicleTypeService);

            String name = lostCard.getVisitorName();
            String phone = lostCard.getVisitorPhoneNum();
            String cccd = lostCard.getIdentifyCard();
            String type = "-- Loại xe --";

            if (lostCard.getCustomerId() != null && lostCard.getCustomerId() != 0) {
                Customer customer = customerService.getCustomerById(lostCard.getCustomerId());
                if (customer != null) {
                    name = customer.getFullName();
                    phone = customer.getPhoneNumber();
                    cccd = customer.getIdentifyCard();
                }
            }
            if (lostCard.getCardId() != null && lostCard.getCardId() != 0) {
                Card card = cardService.getCardById(lostCard.getCardId());
                if (card != null) {
                    type = card.getType();
                }
            }

            // Format ngày tháng từ LocalDateTime về chuỗi
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            String notificationTimeStr = (lostCard.getNotificationTime() != null) ? lostCard.getNotificationTime().format(formatter) : "";
            String timeOfLostStr = (lostCard.getTimeOfLost() != null) ? lostCard.getTimeOfLost().format(formatter) : "";

            request.setAttribute("displayName", name);
            request.setAttribute("displayPhone", phone);
            request.setAttribute("displayIdentifyCard", cccd);
            request.setAttribute("displayType", type);
            request.setAttribute("notificationTimeStr", notificationTimeStr);
            request.setAttribute("timeOfLostStr", timeOfLostStr);
            request.setAttribute("lostCard", lostCard);
            request.setAttribute("cards", cards);
            request.setAttribute("customers", customers);
            request.setAttribute("lstCardSwipe", lstCardSwipe);
            request.getRequestDispatcher("/views/admin/lostcard/lostcard-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int lostCardId = Integer.parseInt(request.getParameter("id"));
            lostCardService.deleteLostCard(lostCardId);
            response.sendRedirect(request.getContextPath() + "/admin/lost");
        } else {
            String vehicleTypeId = request.getParameter("vehicleTypeId");
            String ticketTypeId = request.getParameter("ticketTypeId");
            String dateRange = request.getParameter("dateRange");

            HttpSession session = request.getSession();
            session.setAttribute("vehicleTypeId", vehicleTypeId != null ? vehicleTypeId : "");
            session.setAttribute("ticketTypeId", ticketTypeId != null ? ticketTypeId : "");


            // Truyền xuống JSP qua request
            request.setAttribute("vehicleTypeFilter",session.getAttribute("vehicleTypeId") );
            request.setAttribute("ticketTypeFilter",session.getAttribute("ticketTypeId") );



            LocalDateTime startDate = null;
            LocalDateTime endDate = null;
            if (dateRange != null && !dateRange.isEmpty()) {
                try {
                    String[] dates = dateRange.split(" - ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    startDate = LocalDate.parse(dates[0], formatter).atTime(00, 00, 01);
                    endDate = LocalDate.parse(dates[1], formatter).atTime(23, 59, 59);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            final LocalDateTime finalStartDate = startDate;
            final LocalDateTime finalEndDate = endDate;


            session.setAttribute("startDateFilter", startDate);
            session.setAttribute("endDateFilter", endDate);

            request.setAttribute("startDate", session.getAttribute("startDateFilter"));
            request.setAttribute("endDate", session.getAttribute("endDateFilter"));

            List<LostCard> lostCards = lostCardService.getAllLostCards();
            //List<LostCardDTO> lstLostCards = LostCardMapper.toDTOList(lostCards, cardService, customerService, vehicleTypeService);
//            VehicleType vehicleType=vehicleTypeService.getVehicleTypeById(Integer.parseInt(vehicleTypeId));
//            int vehicleTypeIdFilter=vehicleType.getVehicleTypeId();
            List<LostCardDTO> lstLostCards= lostCards.stream()
//                    .filter(p -> {
//                        if (vehicleTypeId == null || vehicleTypeId.isEmpty()) return true;
//                        try {
//                            return p.get() == Integer.parseInt(vehicleTypeId);
//                        } catch (NumberFormatException e) {
//                            return true;
//                        }
//                    })
//                    .filter(p -> {
//                        if (ticketTypeId == null || ticketTypeId.isEmpty()) return true;
//                        try {
//                            return p.getTicketTypeId() == Integer.parseInt(ticketTypeId);
//                        } catch (NumberFormatException e) {
//                            return true;
//                        }
//                    })
//
//                    .filter(p -> {
//                        if (finalStartDate != null && finalEndDate != null) {
//                            return (p.getNotificationTime() != null &&
//                                    (p.getNotificationTime().isEqual(finalStartDate) || p.getNotificationTime().isAfter(finalStartDate)) &&
//                                    (p.getNotificationTime().isEqual(finalEndDate) || p.getNotificationTime().isBefore(finalEndDate)));
//                        }
//                        return true;
//                    })
                    .map(p -> LostCardMapper.toDTO(p, cardService, customerService, vehicleTypeService))
                    .toList();

            request.setAttribute("lstLostCards", lstLostCards);
            request.getRequestDispatcher("/views/admin/lostcard/lostcard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lostCardId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        Integer  customerId = request.getParameter("customerId").isEmpty() ? null : Integer.parseInt(request.getParameter("customerId"));
        Integer cardId = request.getParameter("cardId").isEmpty() ? null : Integer.parseInt(request.getParameter("cardId"));

        String notificationTimeStr = request.getParameter("notificationTime");
        String timeOfLostStr = request.getParameter("timeOfLost");

        double ticketPrice = request.getParameter("ticketPrice").isEmpty() ? 0 : Double.parseDouble(request.getParameter("ticketPrice"));
        double lostCardFee = request.getParameter("lostCardFee").isEmpty() ? 0 : Double.parseDouble(request.getParameter("lostCardFee"));

        String checkInLicensePhoto = request.getParameter("checkInLicensePhoto");
        checkInLicensePhoto = (checkInLicensePhoto != null && !checkInLicensePhoto.trim().isEmpty()) ? checkInLicensePhoto : null;

        String checkInCustomerPhoto = request.getParameter("checkInCustomerPhoto");

        String visitorName = request.getParameter("visitorName");
        visitorName = (visitorName != null && !visitorName.trim().isEmpty()) ? visitorName : null;

        String visitorPhoneNum = request.getParameter("visitorPhoneNum");
        visitorPhoneNum = (visitorPhoneNum != null && !visitorPhoneNum.trim().isEmpty()) ? visitorPhoneNum : null;

        String identifyCard = request.getParameter("identifyCard");
        identifyCard = (identifyCard != null && !identifyCard.trim().isEmpty()) ? identifyCard : null;

        String registrationLicense = request.getParameter("registrationLicense");
        String note = request.getParameter("note");

        // Chỉnh sửa chuỗi để đảm bảo giờ luôn có 2 chữ số
        if (notificationTimeStr != null && !notificationTimeStr.isEmpty()) {
            if (notificationTimeStr.matches(".*\\s[1-9]:.*")) { // Nếu giờ có 1 chữ số
                notificationTimeStr = notificationTimeStr.replaceFirst("(\\d{1})(:)", "0$1$2");
            }
        }

        if (timeOfLostStr != null && !timeOfLostStr.isEmpty()) {
            if (timeOfLostStr.matches(".*\\s[1-9]:.*")) { // Nếu giờ có 1 chữ số
                timeOfLostStr = timeOfLostStr.replaceFirst("(\\d{1})(:)", "0$1$2");
            }
        }

        // Chuyển đổi thời gian từ chuỗi sang LocalDateTime theo định dạng MM/dd/yyyy hh:mm a
        java.time.LocalDateTime notificationTime = null;
        java.time.LocalDateTime timeOfLost = null;
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        if (notificationTimeStr != null && !notificationTimeStr.isEmpty()) {
            notificationTime = java.time.LocalDateTime.parse(notificationTimeStr, formatter);
        }
        if (timeOfLostStr != null && !timeOfLostStr.isEmpty()) {
            timeOfLost = java.time.LocalDateTime.parse(timeOfLostStr, formatter);
        }

        LostCard lostCard = new LostCard(
                lostCardId,
                customerId,
                cardId,
                notificationTime,
                timeOfLost,
                ticketPrice,
                lostCardFee,
                checkInLicensePhoto,
                checkInCustomerPhoto,
                visitorName,
                visitorPhoneNum,
                identifyCard,
                registrationLicense,
                note
        );

        if (lostCardId == 0) {
            lostCardService.insertLostCard(lostCard);
        } else {
            lostCardService.updateLostCard(lostCard);
        }

        response.sendRedirect(request.getContextPath() + "/admin/lost");
    }
}
