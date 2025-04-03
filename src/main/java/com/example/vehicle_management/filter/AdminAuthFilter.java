package com.example.vehicle_management.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isAdmin = false;

        if (isLoggedIn) {
            // Giả sử 'userRole' là một thuộc tính trong session lưu vai trò của người dùng
            String userRole = (String) session.getAttribute("userRole");
            isAdmin = "admin".equals(userRole);
        }

        if (isLoggedIn && isAdmin) {
            // Nếu đã đăng nhập và có quyền admin, cho phép tiếp tục
            chain.doFilter(request, response);
        } else {
            // Redirect về trang login nếu chưa đăng nhập hoặc không có quyền admin
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên khi filter bị hủy
    }

}
