package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.models.Role;
import com.example.vehicle_management.repositoriesImpl.RoleRepositoryImpl;
import com.example.vehicle_management.services.IRoleService;
import com.example.vehicle_management.servicesImpl.RoleServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/role", "/admin/role/edit", "/admin/role/add", "/admin/role/delete", "/admin/role/save"})
public class RoleServlet extends HttpServlet {
    private IRoleService roleService;

    @Override
    public void init() throws ServletException {
        roleService = new RoleServiceImpl(new RoleRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("edit") || uri.contains("add")) {
            int roleId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            Role role = (roleId == 0) ? new Role() : roleService.getRoleById(roleId);

            request.setAttribute("role", role);
            request.getRequestDispatcher("/views/admin/role/role-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int roleId = Integer.parseInt(request.getParameter("id"));
            roleService.deleteRole(roleId);
            response.sendRedirect(request.getContextPath() + "/admin/role");
        } else {
            List<Role> lstRole = roleService.getAllRoles();

            request.setAttribute("lstRole", lstRole);
            request.getRequestDispatcher("/views/admin/role/role.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roleId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        String roleName = request.getParameter("roleName");

        Role role = new Role(roleId, roleName);

        if (roleId == 0) {
            roleService.insertRole(role);
        } else {
            roleService.updateRole(role);
        }
        response.sendRedirect(request.getContextPath() + "/admin/role");
    }
}
