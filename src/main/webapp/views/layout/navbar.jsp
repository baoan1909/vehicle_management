<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/24/2025
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
        </li>
<%--        <li class="nav-item d-none d-sm-inline-block">--%>
<%--            <a href="#" class="nav-link">Home</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item d-none d-sm-inline-block">--%>
<%--            <a href="#" class="nav-link">Contact</a>--%>
<%--        </li>--%>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <!-- Navbar Search -->
        <li class="nav-item mr-2">
            <a class="input-group" data-widget="sidebar-search">
                <input class="form-control form-control-sidebar" type="search" placeholder="Tìm kiếm" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-sidebar bg-cyan">
                        <i class="fas fa-search fa-fw"></i>
                    </button>
                </div>
            </a>
        </li>

<%--        <!-- Messages Dropdown Menu -->--%>
<%--        <li class="nav-item dropdown">--%>
<%--            <a class="nav-link" data-toggle="dropdown" href="#">--%>
<%--                <i class="far fa-comments"></i>--%>
<%--                <span class="badge badge-danger navbar-badge">3</span>--%>
<%--            </a>--%>
<%--            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">--%>
<%--                <a href="#" class="dropdown-item">--%>
<%--                    <!-- Message Start -->--%>
<%--                    <div class="media">--%>
<%--                        <img src="dist/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">--%>
<%--                        <div class="media-body">--%>
<%--                            <h3 class="dropdown-item-title">--%>
<%--                                Brad Diesel--%>
<%--                                <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>--%>
<%--                            </h3>--%>
<%--                            <p class="text-sm">Call me whenever you can...</p>--%>
<%--                            <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <!-- Message End -->--%>
<%--                </a>--%>
<%--                <div class="dropdown-divider"></div>--%>
<%--                <a href="#" class="dropdown-item">--%>
<%--                    <!-- Message Start -->--%>
<%--                    <div class="media">--%>
<%--                        <img src="dist/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">--%>
<%--                        <div class="media-body">--%>
<%--                            <h3 class="dropdown-item-title">--%>
<%--                                John Pierce--%>
<%--                                <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>--%>
<%--                            </h3>--%>
<%--                            <p class="text-sm">I got your message bro</p>--%>
<%--                            <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <!-- Message End -->--%>
<%--                </a>--%>
<%--                <div class="dropdown-divider"></div>--%>
<%--                <a href="#" class="dropdown-item">--%>
<%--                    <!-- Message Start -->--%>
<%--                    <div class="media">--%>
<%--                        <img src="dist/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">--%>
<%--                        <div class="media-body">--%>
<%--                            <h3 class="dropdown-item-title">--%>
<%--                                Nora Silvester--%>
<%--                                <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>--%>
<%--                            </h3>--%>
<%--                            <p class="text-sm">The subject goes here</p>--%>
<%--                            <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <!-- Message End -->--%>
<%--                </a>--%>
<%--                <div class="dropdown-divider"></div>--%>
<%--                <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>--%>
<%--            </div>--%>
<%--        </li>--%>
        <!-- Notifications Dropdown Menu -->
        <li class="nav-item dropdown mr-2">
            <a class="nav-link" data-toggle="dropdown" href="#">
                <i class="far fa-bell"></i>
                <span class="badge badge-warning navbar-badge">15</span>
            </a>
            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                <span class="dropdown-item dropdown-header">15 Thông báo</span>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-envelope mr-2"></i> 4 tin nhắn mới
                    <span class="float-right text-muted text-sm">3 phút</span>
                </a>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-users mr-2"></i> 8 yêu cầu đăng ký
                    <span class="float-right text-muted text-sm">12 giờ</span>
                </a>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-file mr-2"></i> 3 báo cáo mới
                    <span class="float-right text-muted text-sm">2 ngày</span>
                </a>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item dropdown-footer">Xem tất cả thông báo</a>
            </div>
        </li>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" data-widget="fullscreen" href="#" role="button">--%>
<%--                <i class="fas fa-expand-arrows-alt"></i>--%>
<%--            </a>--%>
<%--        </li>--%>
        <div class="nav-item dropdown">
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
                        <span class="d-block mt-3"><h5>Alexander Pierce</h5></span>
                    </div>
                </div>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-user-circle mr-2"></i> Thông tin tài khoản
                </a>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-question-circle mr-2"></i> Trợ giúp
                </a>
                <div class="dropdown-divider"></div>
                <a href="<%= request.getContextPath() %>/login?page=login" class="dropdown-item">
                    <i class="fas fa-sign-out-alt mr-2"></i> Đăng xuất
                </a>
<%--                <div class="dropdown-divider"></div>--%>
<%--                <a href="#" class="dropdown-item dropdown-footer"></a>--%>
            </div>
        </div>
    </ul>
</nav>
