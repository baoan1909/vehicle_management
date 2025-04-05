package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardDTO;
import com.example.vehicle_management.mappers.CardMapper;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositoriesImpl.CardRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.ICardService;
import com.example.vehicle_management.services.IVehicleTypeService;
import com.example.vehicle_management.servicesImpl.CardServiceImpl;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/card", "/admin/card/edit", "/admin/card/add", "/admin/card/delete", "/admin/card/save"})
public class CardServlet extends HttpServlet {
    private ICardService cardService;
    private IVehicleTypeService vehicleTypeService;

    @Override
    public void init() throws ServletException {
        cardService = new CardServiceImpl(new CardRepositoryImpl());
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

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
            List<Card> cards = cardService.getAllCards();
            List<CardDTO> lstCards = CardMapper.toDTOList(cards, vehicleTypeService);
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

        // Kiểm tra nếu số thẻ đã tồn tại
        if (cardService.isExistsCardNumber(cardNumber)) {
            request.setAttribute("error", "Số thẻ này đã tồn tại.");
            request.setAttribute("card", card);
            request.setAttribute("vehicleTypes", vehicleTypeService.getAllVehicleTypes());
            request.getRequestDispatcher("/views/admin/card/card-detail.jsp").forward(request, response);
            return;
        }

        if (cardId == 0) {
            cardService.insertCard(card);
        } else {
            cardService.updateCard(card);
        }

        response.sendRedirect(request.getContextPath() + "/admin/card");
    }
}
