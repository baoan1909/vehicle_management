<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 4/1/2025
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.vehicle_management.models.Account" %>
<%
    Account account = (Account) session.getAttribute("account");
%>
<nav class="main-header navbar navbar-expand-md navbar-light navbar-white">
    <div class="container">
        <a href="#" class="navbar-brand">
            <img src="<%= request.getContextPath() %>/assets/admin/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
            <span class="brand-text font-weight-light">Admin</span>
        </a>

        <button class="navbar-toggler order-1" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse order-3" id="navbarCollapse">
            <!-- Left navbar links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="<%= request.getContextPath() %>/pricing?page=pricing" class="nav-link">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a href="<%= request.getContextPath() %>/contact?page=contact" class="nav-link">Liên hệ</a>
                </li>
            </ul>
        </div>

        <!-- Right navbar links -->
        <ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
            <c:choose>
                <c:when test="${empty account}">
                    <!-- Chưa đăng nhập -->
                    <li class="nav-item">
                        <div class="col-md-12">
                            <ol class="breadcrumb bg-white">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/login?page=login">Đăng nhập</a></li>
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/register?page=register">Đăng ký</a></li>
                            </ol>
                        </div>
                    </li>
                </c:when>
                <c:when test="${not empty account}">
                    <!-- Đã đăng nhập với roleId == 2 -->
                    <li class="nav-item dropdown">
                        <div class="user-panel d-flex h-100" data-toggle="dropdown">
                            <div class="image">
                                <img src="<%= request.getContextPath() %>/assets/admin/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                            </div>
                            <div class="info">
                                <a href="#" class="d-block"><i class="fas fa-sort-down"></i></a>
                            </div>
                        </div>
                        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                            <div class="dropdown-item dropdown-header">
                                <div class="image">
                                    <img src="<%= request.getContextPath() %>/assets/admin/dist/img/user2-160x160.jpg" class="img-circle elevation-2 mt-3" style="width: 60px" alt="User Image">
                                </div>
                                <div class="info">
                                    <span class="d-block mt-3"><h5>${account.getUserName()}</h5></span>
                                </div>
                            </div>
                            <div class="dropdown-divider"></div>
                            <c:choose>
                                <c:when test="${account.roleId == 2}">
                                    <a href="${pageContext.request.contextPath}/customerTicket/customer-infor-detail" class="dropdown-item">
                                        <i class="fas fa-user-circle mr-2"></i> Thông tin tài khoản
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/customerTicket/customer-infor-detail" class="dropdown-item">
                                        <i class="fas fa-user-circle mr-2"></i> Thông tin tài khoản
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <div class="dropdown-divider"></div>
                            <a href="#" class="dropdown-item">
                                <i class="fas fa-question-circle mr-2"></i> Trợ giúp
                            </a>
                            <div class="dropdown-divider"></div>
                            <a href="<%= request.getContextPath() %>/logout" class="dropdown-item">
                                <i class="fas fa-sign-out-alt mr-2"></i> Đăng xuất
                            </a>
                        </div>
                    </li>
                </c:when>
            </c:choose>
        </ul>
    </div>
</nav>
