package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.repositoriesImpl.TicketTypeRepositoryImpl;
import com.example.vehicle_management.services.ITicketTypeService;
import com.example.vehicle_management.servicesImpl.TicketTypeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/ticket", "/admin/ticket/edit", "/admin/ticket/add", "/admin/ticket/delete", "/admin/ticket/save"})
public class TicketServlet extends HttpServlet {
    private ITicketTypeService ticketTypeService;

    @Override
    public void init() throws ServletException {
        ticketTypeService = new TicketTypeServiceImpl(new TicketTypeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String searchQuery = request.getParameter("search"); // Lấy từ khóa tìm kiếm

        if (uri.contains("edit") || uri.contains("add")) {
            int ticketTypeId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            TicketType ticketType = (ticketTypeId == 0) ? new TicketType() : ticketTypeService.getTicketTypeById(ticketTypeId);

            request.setAttribute("ticketType", ticketType);
            request.getRequestDispatcher("/views/admin/ticket/ticket-detail.jsp").forward(request, response);
        }else if (uri.contains("delete")) {
            int ticketTypeId = Integer.parseInt(request.getParameter("id"));
            ticketTypeService.deleteTicketType(ticketTypeId);
            response.sendRedirect( request.getContextPath() + "/admin/ticket");
        }else {
            List<TicketType> lstTicketType = ticketTypeService.getAllTicketTypes();

            // Đưa danh sách sách vào request
            request.setAttribute("lstTicketType", lstTicketType);

            // Điều hướng đến JSP để hiển thị dữ liệu
            request.getRequestDispatcher("/views/admin/ticket/ticket.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ticketTypeId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        String ticketTypeName = request.getParameter("ticketTypeName");
        String description = request.getParameter("description");

        TicketType ticketType = new TicketType(ticketTypeId, ticketTypeName, description);

        if(ticketTypeId == 0){
            ticketTypeService.insertTicketType(ticketType);
        }else {
            ticketTypeService.updateTicketType(ticketType);
        }
        response.sendRedirect(request.getContextPath() + "/admin/ticket");
    }
}
