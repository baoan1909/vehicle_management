package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardSwipeDTO;
import com.example.vehicle_management.mappers.CardSwipeMapper;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositories.IParkingFeeOfVisitorRepository;
import com.example.vehicle_management.repositoriesImpl.CardRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.CardSwipeRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.ParkingFeeOfVisitorRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.ICardService;
import com.example.vehicle_management.services.ICardSwipeService;
import com.example.vehicle_management.services.IParkingFeeOfVisitorService;
import com.example.vehicle_management.services.IVehicleTypeService;
import com.example.vehicle_management.servicesImpl.CardServiceImpl;
import com.example.vehicle_management.servicesImpl.CardSwipeServiceImpl;
import com.example.vehicle_management.servicesImpl.ParkingFeeOfVisitorServiceImpl;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.List;


@WebServlet({"/admin/swipe", "/admin/swipe/swipein", "/admin/swipe/swipeout", "/admin/swipe/save", "/admin/swipe/delete", "/admin/swipe/getCardSwipeIn", "/admin/swipe/getCardSwipeOut"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize = 5 * 1024 * 1024,        // 5 MB
        maxRequestSize = 10 * 1024 * 1024     // 10 MB
)
public class CardSwipeServlet extends HttpServlet {

    private ICardSwipeService cardSwipeService;
    private ICardService cardService;
    private IVehicleTypeService vehicleTypeService;
    private IParkingFeeOfVisitorService parkingFeeOfVisitorService;

    @Override
    public void init() throws ServletException {
        cardSwipeService = new CardSwipeServiceImpl(new CardSwipeRepositoryImpl());
        cardService = new CardServiceImpl(new CardRepositoryImpl());
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
        parkingFeeOfVisitorService = new ParkingFeeOfVisitorServiceImpl(new ParkingFeeOfVisitorRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        //Tự động lấy dữ liệu theo cardId khi chọn cardId
        if (uri.contains("getCardSwipeIn")) {
            String cardIdStr = request.getParameter("cardId");

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            if (cardIdStr != null) {
                int cardId = Integer.parseInt(cardIdStr);
                Card card = cardService.getCardById(cardId);

                if(card != null) {
                    VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(card.getVehicleTypeId());
                    jsonBuilder.append(String.format(
                            " \"type\":\"%s\", \"vehicleTypeName\":\"%s\", \"vehicleTypeId\":\"%s\"",
                            card.getType(),
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

        if (uri.contains("getCardSwipeOut")) {
            String cardIdStr = request.getParameter("cardId");

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            if (cardIdStr != null) {
                int cardId = Integer.parseInt(cardIdStr);
                Card card = cardService.getCardById(cardId);
//                double cardSwipePriceVisitor = parkingFeeOfVisitorService;
                int customerByCardId = cardService.getCustomerIdByCardId(cardId);
                CardSwipe cardSwipe = cardSwipeService.getCardSwipeByCardId(cardId);

                if(cardSwipe != null && card != null) {
                    VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(card.getVehicleTypeId());

                    if(customerByCardId != 0) {
                        jsonBuilder.append(String.format(
                                " \"cardSwipeId\":\"%s\", \"type\":\"%s\", \"vehicleTypeName\":\"%s\", \"licensePlate\":\"%s\", \"checkInTime\":\"%s\", \"checkInImagePath\":\"%s\"",
                                cardSwipe.getCardSwipeId(),
                                card.getType(),
                                vehicleType.getVehicleTypeName(),
                                cardSwipe.getLicensePlate(),
                                cardSwipe.getCheckInTime(),
                                cardSwipe.getCheckInImagePath()
                        ));
                    }else {
                        jsonBuilder.append(String.format(
                                " \"cardSwipeId\":\"%s\", \"type\":\"%s\", \"vehicleTypeName\":\"%s\", \"licensePlate\":\"%s\", \"checkInTime\":\"%s\", \"checkInImagePath\":\"%s\", \"price\":\"%s\"",
                                cardSwipe.getCardSwipeId(),
                                card.getType(),
                                vehicleType.getVehicleTypeName(),
                                cardSwipe.getLicensePlate(),
                                cardSwipe.getCheckInTime(),
                                cardSwipe.getCheckInImagePath()
//                                cardSwipePriceVisitor
                        ));
                    }
                }else {
                    jsonBuilder.append(" \"message\": \"Thẻ này đã được quẹt THANH TOÁN hoặc CHƯA VÀO cổng\"");
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

        if(uri.contains("swipein") || uri.contains("swipeout")) {
            int cardSwipeId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            CardSwipe cardSwipe = (cardSwipeId == 0) ? new CardSwipe() : cardSwipeService.getCardSwipeById(cardSwipeId);
            List<Card> cards = cardService.getAllCards();

            // Format ngày tháng từ LocalDateTime về chuỗi
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            String checkInTimeStr = (cardSwipe.getCheckInTime() != null) ? cardSwipe.getCheckInTime().format(formatter) : "";
            String checkOutTimeStr = (cardSwipe.getCheckOutTime() != null) ? cardSwipe.getCheckOutTime().format(formatter) : "";

            request.setAttribute("cardSwipe", cardSwipe);
            request.setAttribute("cards", cards);
            request.setAttribute("checkInTimeStr", checkInTimeStr);
            request.setAttribute("checkOutTimeStr", checkOutTimeStr);

            if(uri.contains("swipeout")){
                request.getRequestDispatcher("/views/admin/swipe/swipe-out.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("/views/admin/swipe/swipe-in.jsp").forward(request, response);
            }

        }else if(uri.contains("delete")) {
            int cardSwipeId = Integer.parseInt(request.getParameter("id"));
            cardSwipeService.deleteCardSwipe(cardSwipeId);
            response.sendRedirect(request.getContextPath() + "/admin/swipe");
        }else {
            List<CardSwipe> cardSwipes = cardSwipeService.getAllCardSwipes();
            List<CardSwipeDTO> lstCardSwipe = CardSwipeMapper.toDTOList(cardSwipes, cardService, vehicleTypeService);

            request.setAttribute("lstCardSwipe", lstCardSwipe);
            request.getRequestDispatcher("/views/admin/swipe/swipe.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cardSwipeId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        String licensePlate = request.getParameter("licensePlate");
        int cardId = Integer.parseInt(request.getParameter("cardId"));

        String checkInTimeStr = request.getParameter("checkInTime");
        String checkOutTimeStr = request.getParameter("checkOutTime");

        //chuyển thời gian về đúng định dạng ví dụ 3:00 -> 03:00
        if(checkInTimeStr != null && !checkInTimeStr.isEmpty()) {
            if(checkInTimeStr.matches(".*\\s[1-9]:.*")){
                checkInTimeStr = checkInTimeStr.replaceFirst("(\\d{1})(:)", "0$1$2");
            }

        }
        if(checkOutTimeStr != null && !checkOutTimeStr.isEmpty()) {
            if(checkOutTimeStr.matches(".*\\s[1-9]:.*")){
                checkOutTimeStr = checkOutTimeStr.replaceFirst("(\\d{1})(:)", "0$1$2");
            }

        }

        java.time.LocalDateTime checkInTime = null;
        java.time.LocalDateTime checkOutTime = null;
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        if (checkInTimeStr != null && !checkInTimeStr.isEmpty()){
            checkInTime = java.time.LocalDateTime.parse(checkInTimeStr, formatter);
        }
        if (checkOutTimeStr != null && !checkOutTimeStr.isEmpty()){
            checkOutTime = java.time.LocalDateTime.parse(checkOutTimeStr, formatter);
        }

        String imagePathOut = null;
        double price = 0;

        String checkInImagePath = request.getParameter("checkInImagePath");
        if (request.getParameter("imagePathOut") != null && !request.getParameter("imagePathOut").isEmpty()) {
            imagePathOut = request.getParameter("imagePathOut");
        }

        if (request.getParameter("price") != null && !request.getParameter("price").isEmpty()) {
            price = Double.parseDouble(request.getParameter("price"));
        }

        int vehicleTypeId =Integer.parseInt(request.getParameter("vehicleTypeId"));


        CardSwipe cardSwipe = new CardSwipe(cardSwipeId, licensePlate, cardId, checkInTime, checkOutTime, checkInImagePath, imagePathOut, price, vehicleTypeId);

        if (cardSwipeId == 0) {
            cardSwipeService.insertCardSwipe(cardSwipe);
        } else {
            cardSwipeService.updateCardSwipe(cardSwipe);
        }

        response.sendRedirect(request.getContextPath() + "/admin/swipe");
    }

}
