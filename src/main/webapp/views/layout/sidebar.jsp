<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/22/2025
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- Main Sidebar Container -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar sidebar-light-cyan elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
        <img src="<%= request.getContextPath() %>/assets/admin/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">Admin</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item mt-5">
                    <a href="<%= request.getContextPath() %>/admin/dashboard?page=dashboard"
                       class="nav-link <%="dashboard".equals(request.getParameter("page")) ? "active" : "" %>">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Trang chủ
                        </p>
                    </a>
                </li>
                <li class="nav-item sidebar-divider mb-2"></li>
                <li class="nav-item">
                    <a href="<%= request.getContextPath() %>/admin/card-swipe?page=card-swipe" class="nav-link <%=("card-swipe".equals(request.getParameter("page")) || "swipe-out".equals(request.getParameter("page")) || "swipe-in".equals(request.getParameter("page"))) ? "active" : "" %>">
                        <i class="nav-icon fas fa-th"></i>
                        <p>
                            Quản lý vào ra
                        </p>
                    </a>
                </li>
                <li class="nav-item <%= ("card".equals(request.getParameter("page")) || "lost-card".equals(request.getParameter("page")) || "card-detail".equals(request.getParameter("page")) || "lostcard-detail".equals(request.getParameter("page"))) ? "menu-open" : "" %>">
                    <a href="#" class="nav-link <%= ("card".equals(request.getParameter("page")) || "lost-card".equals(request.getParameter("page")) || "card-detail".equals(request.getParameter("page")) || "lostcard-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                        <i class="nav-icon fas fa-credit-card"></i>
                        <p>
                            Quản lý thẻ
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="<%= request.getContextPath() %>/admin/card?page=card"
                               class="nav-link <%=("card".equals(request.getParameter("page")) || "card-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Thẻ</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<%= request.getContextPath() %>/admin/lost-card?page=lost-card"
                               class="nav-link <%=("lost-card".equals(request.getParameter("page")) || "lostcard-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Thẻ bị mất</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item <%= ("ticket".equals(request.getParameter("page")) || "vehicle".equals(request.getParameter("page")) || "ticket-detail".equals(request.getParameter("page")) || "vehicle-detail".equals(request.getParameter("page"))) ? "menu-open" : "" %>">
                    <a href="#" class="nav-link <%= ("ticket".equals(request.getParameter("page")) || "vehicle".equals(request.getParameter("page")) || "ticket-detail".equals(request.getParameter("page")) || "vehicle-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                        <i class="nav-icon fas fa-book"></i>
                        <p>
                            Vé & Phương tiện
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="<%= request.getContextPath() %>/admin/ticket?page=ticket"
                               class="nav-link <%=("ticket".equals(request.getParameter("page")) || "ticket-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Vé</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<%= request.getContextPath() %>/admin/vehicle?page=vehicle"
                               class="nav-link <%= ("vehicle".equals(request.getParameter("page")) || "vehicle-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Phương tiện</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item sidebar-divider mb-2"></li>
                <li class="nav-item <%= ("parkingFeeOfVisitor".equals(request.getParameter("page")) || "parkingFeeOfCustomer".equals(request.getParameter("page")) || "parkingFeeOfVisitor-detail".equals(request.getParameter("page")) || "parkingFeeOfCustomer-detail".equals(request.getParameter("page"))) ? "menu-open" : "" %>">
                    <a href="#" class="nav-link <%= ("parkingFeeOfVisitor".equals(request.getParameter("page")) || "parkingFeeOfCustomer".equals(request.getParameter("page")) || "parkingFeeOfVisitor-detail".equals(request.getParameter("page")) || "parkingFeeOfCustomer-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                        <i class="nav-icon fas fa-file-invoice-dollar"></i>
                        <p>
                            Bảng giá
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="<%= request.getContextPath() %>/admin/parkingFeeOfVisitor?page=parkingFeeOfVisitor"
                               class="nav-link <%= ("parkingFeeOfVisitor".equals(request.getParameter("page")) || "parkingFeeOfVisitor-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Phí vãng lai</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<%= request.getContextPath() %>/admin/parkingFeeOfCustomer?page=parkingFeeOfCustomer"
                               class="nav-link <%= ("parkingFeeOfCustomer".equals(request.getParameter("page")) || "parkingFeeOfCustomer-detail".equals(request.getParameter("page"))) ? "active" : "" %>">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Phí đăng ký</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-users"></i>
                        <p>
                            Thành viên
                            <i class="fas fa-angle-left right"></i>
                            <span class="badge badge-info right">6</span>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Nhân viên</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Khách hàng</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-chart-pie"></i>
                        <p>
                            Quản lý vai trò
                        </p>
                    </a>
                </li>
<%--                <li class="nav-item">--%>
<%--                    <a href="#" class="nav-link">--%>
<%--                        <i class="nav-icon fas fa-wrench"></i>--%>
<%--                        <p>--%>
<%--                            Quản lý thiết bị--%>
<%--                            <i class="fas fa-angle-left right"></i>--%>
<%--                        </p>--%>
<%--                    </a>--%>
<%--                    <ul class="nav nav-treeview">--%>
<%--                        <li class="nav-item">--%>
<%--                            <a href="#" class="nav-link">--%>
<%--                                <i class="far fa-circle nav-icon"></i>--%>
<%--                                <p>Camera</p>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a href="#" class="nav-link">--%>
<%--                                <i class="far fa-circle nav-icon"></i>--%>
<%--                                <p>Đầu lọc thẻ</p>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a href="#" class="nav-link">--%>
<%--                                <i class="far fa-circle nav-icon"></i>--%>
<%--                                <p>Máy tính</p>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a href="#" class="nav-link">--%>
<%--                                <i class="far fa-circle nav-icon"></i>--%>
<%--                                <p>Rào chắn</p>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>
