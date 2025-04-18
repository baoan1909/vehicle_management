package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardSwipeDTO;
import com.example.vehicle_management.mappers.CardSwipeMapper;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositories.IParkingFeeOfVisitorRepository;
import com.example.vehicle_management.repositoriesImpl.*;
import com.example.vehicle_management.services.*;
import com.example.vehicle_management.servicesImpl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private ITicketTypeService ticketTypeService;

    @Override
    public void init() throws ServletException {
        cardSwipeService = new CardSwipeServiceImpl(new CardSwipeRepositoryImpl());
        cardService = new CardServiceImpl(new CardRepositoryImpl());
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
        parkingFeeOfVisitorService = new ParkingFeeOfVisitorServiceImpl(new ParkingFeeOfVisitorRepositoryImpl());
        ticketTypeService = new TicketTypeServiceImpl(new TicketTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        List<VehicleType> vehicleTypeList= vehicleTypeService.getAllVehicleTypes();
        request.setAttribute("vehicleTypeList", vehicleTypeList);
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
                int customerByCardId = cardService.getCustomerIdByCardId(cardId);
                CardSwipe cardSwipe = cardSwipeService.getCardSwipeByCardId(cardId);

                if(cardSwipe != null && card != null) {
                    VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(card.getVehicleTypeId());
                    double cardSwipePriceVisitor = cardSwipeService.getParkingFeeVisitorByVehicleIdInCardSwipe(cardSwipe.getCardSwipeId());
                    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
                    String checkInTimeStr = (cardSwipe.getCheckInTime() != null) ? cardSwipe.getCheckInTime().format(formatter) : "";

                    if(customerByCardId != 0) {
                        jsonBuilder.append(String.format(
                                " \"cardSwipeId\":\"%s\", \"type\":\"%s\", \"vehicleTypeName\":\"%s\", \"vehicleTypeId\":\"%s\", \"licensePlate\":\"%s\", \"checkInTime\":\"%s\", \"checkInImagePath\":\"%s\"",
                                cardSwipe.getCardSwipeId(),
                                card.getType(),
                                vehicleType.getVehicleTypeName(),
                                vehicleType.getVehicleTypeId(),
                                cardSwipe.getLicensePlate(),
                                checkInTimeStr,
                                cardSwipe.getCheckInImagePath()
                        ));
                    }else {
                        jsonBuilder.append(String.format(
                                " \"cardSwipeId\":\"%s\", \"type\":\"%s\", \"vehicleTypeName\":\"%s\", \"vehicleTypeId\":\"%s\", \"licensePlate\":\"%s\", \"checkInTime\":\"%s\", \"checkInImagePath\":\"%s\", \"price\":\"%s\"",
                                cardSwipe.getCardSwipeId(),
                                card.getType(),
                                vehicleType.getVehicleTypeName(),
                                vehicleType.getVehicleTypeId(),
                                cardSwipe.getLicensePlate(),
                                checkInTimeStr,
                                cardSwipe.getCheckInImagePath(),
                                cardSwipePriceVisitor
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
            //Bộ lọc

            String vehicleTypeId = request.getParameter("vehicleTypeId");
            String dateRange = request.getParameter("dateRange");

            HttpSession session = request.getSession();

            session.setAttribute("vehicleTypeId", vehicleTypeId != null ? vehicleTypeId : "");


            request.setAttribute("vehicleTypeFilter",session.getAttribute("vehicleTypeId") );



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

            List<CardSwipe> cardSwipes= cardSwipeService.getAllCardSwipes();
            //List<CardSwipeDTO> lstCardSwipeFiltered = CardSwipeMapper.toDTOList(cardSwipes, cardService, vehicleTypeService);
            List<CardSwipeDTO> lstCardSwipeFiltered = cardSwipes.stream()
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
                            return (p.getCheckInTime() != null &&
                                    (p.getCheckInTime().isEqual(finalStartDate) || p.getCheckInTime().isAfter(finalStartDate)) &&
                                    (p.getCheckInTime().isEqual(finalEndDate) || p.getCheckInTime().isBefore(finalEndDate)));
                        }
                        return true;
                    })
                    .map(p -> CardSwipeMapper.toDTO(p, cardService, vehicleTypeService))
                    .toList();

            request.setAttribute("lstCardSwipe", lstCardSwipeFiltered);
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

        int vehicleTypeId = Integer.parseInt(request.getParameter("vehicleTypeId"));


        CardSwipe cardSwipe = new CardSwipe(cardSwipeId, licensePlate, cardId, checkInTime, checkOutTime, checkInImagePath, imagePathOut, price, vehicleTypeId);

        HttpSession session = request.getSession();
        if (cardSwipeId == 0) {
            cardSwipeService.insertCardSwipe(cardSwipe);
            session.setAttribute("message", "Quẹt thẻ vào THÀNH CÔNG");
            response.sendRedirect(request.getContextPath() + "/admin/swipe/swipein");
        } else {
            cardSwipeService.updateCardSwipe(cardSwipe);
            session.setAttribute("message", "Quẹt thẻ ra THÀNH CÔNG");
            response.sendRedirect(request.getContextPath() + "/admin/swipe/swipeout");
        }
    }

}
