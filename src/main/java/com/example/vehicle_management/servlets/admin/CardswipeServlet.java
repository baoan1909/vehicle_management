package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.CardSwipeDTO;
import com.example.vehicle_management.mappers.CardSwipeMapper;
import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.repositoriesImpl.CardRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.CardSwipeRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.VehicleTypeRepositoryImpl;
import com.example.vehicle_management.services.ICardService;
import com.example.vehicle_management.services.ICardSwipeService;
import com.example.vehicle_management.services.IVehicleTypeService;
import com.example.vehicle_management.servicesImpl.CardServiceImpl;
import com.example.vehicle_management.servicesImpl.CardSwipeServiceImpl;
import com.example.vehicle_management.servicesImpl.VehicleTypeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/card-swipe")
public class CardswipeServlet extends HttpServlet {
    private ICardSwipeService cardSwipeService;
    private ICardService cardService;
    private IVehicleTypeService vehicleTypeService;

    @Override
    public void init() throws ServletException {
        cardSwipeService = new CardSwipeServiceImpl(new CardSwipeRepositoryImpl());
        cardService = new CardServiceImpl(new CardRepositoryImpl());
        vehicleTypeService = new VehicleTypeServiceImpl(new VehicleTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CardSwipe> cardSwipes = cardSwipeService.getAllCardSwipes();
        List<CardSwipeDTO> lstCardSwipe = CardSwipeMapper.toDTOList(cardSwipes, cardService, vehicleTypeService);
        request.setAttribute("lstCardSwipe", lstCardSwipe);
        request.getRequestDispatcher("/views/admin/cardswipe/cardswipe.jsp").forward(request, response);
    }
}
