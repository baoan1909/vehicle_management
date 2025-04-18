package com.example.vehicle_management.servlets.auth;

import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.repositoriesImpl.*;
import com.example.vehicle_management.services.IAccountService;
import com.example.vehicle_management.servicesImpl.*;
import com.example.vehicle_management.utils.PasswordUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private IAccountService accountService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountServiceImpl(new AccountRepositoryImpl());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "";
        String password = "";

        // Đọc cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }

        // Gửi thông tin sang JSP
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login&register/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Account account = accountService.getAccountByUsername(username);

        if (account == null) {
            request.setAttribute("error", "Tài khoản không tồn tại");
            request.getRequestDispatcher("/views/login&register/login.jsp").forward(request, response);
            return;
        }

        if (!PasswordUtil.checkPassword(password, account.getHashPassword())) {
            request.setAttribute("error", "Sai mật khẩu");
            request.getRequestDispatcher("/views/login&register/login.jsp").forward(request, response);
            return;
        }

        // Lưu account vào session nếu cần
        HttpSession userSession = request.getSession();
        userSession.setAttribute("account", account);


        // Ghi nhớ mật khẩu bằng Cookie nếu được chọn
        if (remember != null) {
            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            usernameCookie.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
            passwordCookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
        }


        // Điều hướng theo role
        if (account.getRoleId() == 1) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else if (account.getRoleId() == 2) {
            response.sendRedirect(request.getContextPath() + "/customerTicket/customer-infor");
        } else {
            request.setAttribute("error", "Không xác định vai trò người dùng");
            request.getRequestDispatcher("/views/login&register/login.jsp").forward(request, response);
        }
    }
}
