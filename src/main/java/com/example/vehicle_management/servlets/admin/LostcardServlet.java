package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardSwipeDTO;
import com.example.vehicle_management.dtos.LostCardDTO;
import com.example.vehicle_management.mappers.CardSwipeMapper;
import com.example.vehicle_management.mappers.LostCardMapper;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.repositoriesImpl.*;
import com.example.vehicle_management.services.*;
import com.example.vehicle_management.servicesImpl.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/lost", "/admin/lost/edit", "/admin/lost/add", "/admin/lost/delete", "/admin/lost/save", "/admin/lost/getcustomer"})
public class LostcardServlet extends HttpServlet {
    private ILostCardService lostCardService;
    private ICardService cardService;
    private IVehicleTypeService vehicleTypeService;
    private ICustomerService customerService;
    private ICardSwipeService cardSwipeService;

    @Override
    public void init() throws ServletException {
        lostCardService = new LostCardServiceImpl(new LostCardRepositoryImpl());
        cardService = new CardServiceImpl(new CardRepositoryImpl());
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
        customerService = new CustomerServiceImpl(new CustomerRepositoryImpl());
        cardSwipeService = new CardSwipeServiceImpl(new CardSwipeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

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
            String type = "";

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
            List<LostCard> lostCards = lostCardService.getAllLostCards();
            List<LostCardDTO> lstLostCards = LostCardMapper.toDTOList(lostCards, cardService, customerService, vehicleTypeService);

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
