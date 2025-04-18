package com.example.vehicle_management.servlets.admin;

import com.example.vehicle_management.dtos.AccountDTO;
import com.example.vehicle_management.mappers.AccountMapper;
import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.models.Role;
import com.example.vehicle_management.repositoriesImpl.AccountRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.CustomerRepositoryImpl;
import com.example.vehicle_management.repositoriesImpl.RoleRepositoryImpl;
import com.example.vehicle_management.services.IAccountService;
import com.example.vehicle_management.services.ICustomerService;
import com.example.vehicle_management.services.IRoleService;
import com.example.vehicle_management.servicesImpl.AccountServiceImpl;
import com.example.vehicle_management.servicesImpl.CustomerServiceImpl;
import com.example.vehicle_management.servicesImpl.RoleServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/account", "/admin/account/edit", "/admin/account/add", "/admin/account/delete", "/admin/account/save", "/admin/account/infor"})
public class AccountServlet extends HttpServlet {
    private IAccountService accountService;
    private IRoleService roleService;
    private ICustomerService customerService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountServiceImpl(new AccountRepositoryImpl());
        roleService = new RoleServiceImpl(new RoleRepositoryImpl());
        customerService = new CustomerServiceImpl(new CustomerRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("edit") || uri.contains("add")) {
            int accountId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
            Account account = (accountId == 0) ? new Account() : accountService.getAccountById(accountId);
            List<Role> roles = roleService.getAllRoles();
            List<Customer> customers = customerService.getAllOnlyCustomer();


            request.setAttribute("account", account);
            request.setAttribute("customers", customers);
            request.setAttribute("roles", roles);
            request.getRequestDispatcher("/views/admin/account/account-detail.jsp").forward(request, response);
        } else if (uri.contains("delete")) {
            int accountId = Integer.parseInt(request.getParameter("id"));
            accountService.deleteAccount(accountId);
            response.sendRedirect(request.getContextPath() + "/admin/account");
        } else if(uri.contains("infor")) {
            int accountId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));

        } else {
            String isActive = request.getParameter("isActive");

            List<Account> accounts = accountService.getAllAccounts();
            List<AccountDTO> lstAccount = AccountMapper.toDTOList(accounts, roleService, customerService);
            request.setAttribute("lstAccount", lstAccount);
            request.getRequestDispatcher("/views/admin/account/account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String hashPassword = request.getParameter("hashPassword");
        String retypePassword = request.getParameter("retypePassword");
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String email = request.getParameter("email");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        int status = (request.getParameter("status") != null) ? 1 : 0;

        Account account = new Account(accountId, userName, hashPassword, customerId, email, roleId, status);

        // Kiểm tra username trùng
        if (accountService.getAccountByUsername(userName) != null && accountId == 0) {
            setRequestAttributes(request, account, "Tên đăng nhập đã được sử dụng.");
            returnToForm(request, response);
            return;
        }

        // Kiểm tra customerId đã có account chưa
        if (accountService.getAccountByEmail(email) != null && accountId == 0) {
            setRequestAttributes(request, account, "Email này đã được sử dụng bởi khách hàng khác.");
            returnToForm(request, response);
            return;
        }

        // Kiểm tra mật khẩu khớp nhau khi thêm mới
        if (hashPassword == null || !hashPassword.equals(retypePassword)) {
            setRequestAttributes(request, account, "Mật khẩu không khớp hoặc không được để trống.");
            returnToForm(request, response);
            return;
        }

        // Insert hoặc Update
        if (accountId == 0) {
            accountService.insertAccount(account);
        } else {
            accountService.updateAccount(account);
        }

        response.sendRedirect(request.getContextPath() + "/admin/account");
    }

    private void setRequestAttributes(HttpServletRequest request, Account account, String errorMessage) {
        request.setAttribute("error", errorMessage);
        request.setAttribute("account", account);
        request.setAttribute("roles", roleService.getAllRoles());
        request.setAttribute("customers", customerService.getAllCustomers());
    }

    private void returnToForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/account/account-detail.jsp").forward(request, response);
    }
}
