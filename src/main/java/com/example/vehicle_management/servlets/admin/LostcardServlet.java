package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.LostCardDTO;
import com.example.vehicle_management.mappers.LostCardMapper;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.repositoriesImpl.CardRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.CustomerRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.LostCardRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.*;
import com.example.vehicle_management.servicesImpl.CardServiceImpl;
import com.example.vehicle_management.servicesImpl.CustomerServiceImpl;
import com.example.vehicle_management.servicesImpl.LostCardServiceImpl;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/lostcard", "/admin/lostcard/edit", "/admin/lostcard/add", "/admin/lostcard/delete", "/admin/lostcard/save", "/admin/lostcard/getcustomer"})
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("getcustomer")) {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            Customer customer = customerService.getCustomerById(customerId);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (customer != null) {
                String json = String.format(
                        "{\"fullName\":\"%s\", \"phoneNumber\":\"%s\", \"identifyCard\":\"%s\"}",
                        customer.getFullName(), customer.getPhoneNumber(), customer.getIdentifyCard()
                );
                response.getWriter().write(json);
            } else {
                response.getWriter().write("{}");
            }
            return;
        }

        if (uri.contains("edit") || uri.contains("add")) {
            int lostCardId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            LostCard lostCard = (lostCardId == 0) ? new LostCard() : lostCardService.getLostCardById(lostCardId);
            List<Card> cards = cardService.getAllCards();
            List<Customer> customers = customerService.getAllCustomers();

            String name = lostCard.getVisitorName();
            String phone = lostCard.getVisitorPhoneNum();
            String cccd = lostCard.getIdentifyCard();

            if (lostCard.getCustomerId() != null && lostCard.getCustomerId() != 0) {
                Customer customer = customerService.getCustomerById(lostCard.getCustomerId());
                if (customer != null) {
                    name = customer.getFullName();
                    phone = customer.getPhoneNumber();
                    cccd = customer.getIdentifyCard();
                }
            }

            // Format ngày tháng từ LocalDateTime về chuỗi
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            String notificationTimeStr = (lostCard.getNotificationTime() != null) ? lostCard.getNotificationTime().format(formatter) : "";
            String timeOfLostStr = (lostCard.getTimeOfLost() != null) ? lostCard.getTimeOfLost().format(formatter) : "";

            request.setAttribute("displayName", name);
            request.setAttribute("displayPhone", phone);
            request.setAttribute("displayIdentifyCard", cccd);
            request.setAttribute("notificationTimeStr", notificationTimeStr);
            request.setAttribute("timeOfLostStr", timeOfLostStr);
            request.setAttribute("lostCard", lostCard);
            request.setAttribute("cards", cards);
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/views/admin/lostcard/lostcard-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int lostCardId = Integer.parseInt(request.getParameter("id"));
            lostCardService.deleteLostCard(lostCardId);
            response.sendRedirect(request.getContextPath() + "/admin/lostcard");
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
        String checkInCustomerPhoto = request.getParameter("checkInCustomerPhoto");
        String visitorName = request.getParameter("visitorName");
        String visitorPhoneNum = request.getParameter("visitorPhoneNum");
        String identifyCard = request.getParameter("identifyCard");
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

        response.sendRedirect(request.getContextPath() + "/admin/lostcard");
    }
}
