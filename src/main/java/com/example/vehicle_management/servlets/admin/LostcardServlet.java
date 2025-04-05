package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.repositoriesImpl.LostCardRepositoryImpl;
import com.example.vehicle_management.services.ILostCardService;
import com.example.vehicle_management.servicesImpl.LostCardServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/lost-card", "/admin/lost-card/edit", "/admin/lost-card/add", "/admin/lost-card/delete", "/admin/lost-card/save"})
public class LostcardServlet extends HttpServlet {
    private ILostCardService lostCardService;

    @Override
    public void init() throws ServletException {
        lostCardService = new LostCardServiceImpl(new LostCardRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("edit") || uri.contains("add")) {
            int lostCardId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            LostCard lostCard = (lostCardId == 0) ? new LostCard() : lostCardService.getLostCardById(lostCardId);

            request.setAttribute("lostCard", lostCard);
            request.getRequestDispatcher("/views/admin/lostcard/lostcard-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int lostCardId = Integer.parseInt(request.getParameter("id"));
            lostCardService.deleteLostCard(lostCardId);
            response.sendRedirect(request.getContextPath() + "/admin/lost-card");
        } else {
            List<LostCard> lstLostCard = lostCardService.getAllLostCards();
            request.setAttribute("lstLostCard", lstLostCard);
            request.getRequestDispatcher("/views/admin/lostcard/lostcard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lostCardId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        Integer customerId = request.getParameter("customerId").isEmpty() ? null : Integer.parseInt(request.getParameter("customerId"));
        int cardId = Integer.parseInt(request.getParameter("cardId"));
        String visitorName = request.getParameter("visitorName");
        String visitorPhoneNum = request.getParameter("visitorPhoneNum");
        String identifyCard = request.getParameter("identifyCard");
        String registrationLicense = request.getParameter("registrationLicense");
        String note = request.getParameter("note");
        // Bạn có thể thêm các tham số khác cho các trường hợp cần thiết

        LostCard lostCard = new LostCard(lostCardId, customerId, cardId, null, null, 0, 0, null, null, visitorName, visitorPhoneNum, identifyCard, registrationLicense, note);

        if (lostCardId == 0) {
            lostCardService.insertLostCard(lostCard);
        } else {
            lostCardService.updateLostCard(lostCard);
        }

        response.sendRedirect(request.getContextPath() + "/admin/lost-card");
    }
}
