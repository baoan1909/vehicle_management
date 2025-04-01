<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 4/1/2025
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <li class="nav-item">
                <div class="col-md-12">
                    <ol class="breadcrumb bg-white">
                        <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/login?page=login">Đăng nhập</a></li>
                        <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/register?page=register">Đăng ký</a></li>
                    </ol>
                </div>
            </li>
        </ul>
    </div>
</nav>
