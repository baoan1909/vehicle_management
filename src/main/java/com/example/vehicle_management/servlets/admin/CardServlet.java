package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardDTO;
import com.example.vehicle_management.mappers.CardMapper;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositoriesImpl.CardRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.TicketTypeRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.ICardService;
import com.example.vehicle_management.services.ITicketTypeService;
import com.example.vehicle_management.services.IVehicleTypeService;
import com.example.vehicle_management.servicesImpl.CardServiceImpl;
import com.example.vehicle_management.servicesImpl.TicketTypeServiceImpl;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
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

@WebServlet({"/admin/card", "/admin/card/edit", "/admin/card/add", "/admin/card/delete", "/admin/card/save"})
public class CardServlet extends HttpServlet {
    private ICardService cardService;
    private IVehicleTypeService vehicleTypeService;
    private ITicketTypeService ticketTypeService;

    @Override
    public void init() throws ServletException {
        cardService = new CardServiceImpl(new CardRepositoryImpl());
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
        List<Card> cardList=cardService.getAllCards();
        request.setAttribute("cardList", cardList);

        if (uri.contains("edit") || uri.contains("add")) {
            int cardId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            Card card = (cardId == 0) ? new Card() : cardService.getCardById(cardId);
            List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();

            request.setAttribute("card", card);
            request.setAttribute("vehicleTypes", vehicleTypes);
            request.getRequestDispatcher("/views/admin/card/card-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int cardId = Integer.parseInt(request.getParameter("id"));
            cardService.deleteCard(cardId);
            response.sendRedirect(request.getContextPath() + "/admin/card");
        } else {

            String vehicleTypeId = request.getParameter("vehicleTypeId");
            String ticketTypeName = request.getParameter("ticketTypeName");
            String dateRange = request.getParameter("dateRange");
            String isCreated =request.getParameter("isCreated")==null ? "" : request.getParameter("isCreated");

            HttpSession session = request.getSession();
            session.setAttribute("isCreated", isCreated);
            session.setAttribute("vehicleTypeId", vehicleTypeId != null ? vehicleTypeId : "");
            session.setAttribute("ticketTypeId", ticketTypeName != null ? ticketTypeName : "");


            // Truyền xuống JSP qua request
            request.setAttribute("isCreated", session.getAttribute("isCreated"));
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

            List<Card> cards = cardService.getAllCards();

            List<CardDTO> lstCards= cards.stream()
//                            .filter(p->{
//                                if (vehicleTypeId == null || vehicleTypeId.isEmpty()) return true;
//                                try {
//                                    return p.getVehicleTypeId() == Integer.parseInt(vehicleTypeId);
//                                } catch (NumberFormatException e) {
//                                    return true;
//                                }
//                            })
//                            .filter(p->{
//                                if (ticketTypeName == null || ticketTypeName.isEmpty()) return true;
//                                try {
//                                    return ticketTypeName.contains(p.getType());
//                                } catch (NumberFormatException e) {
//                                    return true;
//                                }
//                            })
//                            .filter(p -> {
//                                if (isCreated == null || isCreated.isEmpty()) return true;
//                                try {
//                                    return p.getIsCreated() == Integer.parseInt(isCreated);
//                                }catch (NumberFormatException e) {
//                                    return true;
//                                }
//                            })
//                            .filter(p -> {
//                                if (finalStartDate != null && finalEndDate != null) {
//                                    return (p.getCreateDate() != null &&
//                                            (p.getCreateDate().isEqual(finalStartDate) || p.getCreateDate().isAfter(finalStartDate)) &&
//                                            (p.getCreateDate().isEqual(finalEndDate) || p.getCreateDate().isBefore(finalEndDate)));
//                                }
//                                return true;
//                            })
                            .map(p-> CardMapper.toDTO(p, vehicleTypeService))
                            .toList();
            request.setAttribute("lstCards", lstCards);
            request.getRequestDispatcher("/views/admin/card/card.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cardId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        String cardNumber = request.getParameter("cardNumber");
        String type = request.getParameter("type");
        int vehicleTypeId = Integer.parseInt(request.getParameter("vehicleTypeId"));
        int isCreated = (request.getParameter("isCreated") != null) ? 1 : 0;
        int isUsed = (request.getParameter("isUsed") != null) ? 1 : 0;

        Card card = new Card(cardId, cardNumber, type, vehicleTypeId, isCreated, isUsed);

        if (cardId == 0) {
            if (cardService.isExistsCardNumber(cardNumber)) {
                request.setAttribute("error", "Số thẻ này đã tồn tại.");
                request.setAttribute("card", card);
                request.setAttribute("vehicleTypes", vehicleTypeService.getAllVehicleTypes());
                request.getRequestDispatcher("/views/admin/card/card-detail.jsp").forward(request, response);
                return;
            }else {
                cardService.insertCard(card);
            }
        } else {
            cardService.updateCard(card);
        }

        response.sendRedirect(request.getContextPath() + "/admin/card");
    }
}
