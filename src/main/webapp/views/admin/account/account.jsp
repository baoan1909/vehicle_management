
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Account</title>
    <link rel="icon" href="<c:url value='/assets/admin/dist/img/AdminLTELogo.png' />" type="image/png" />

    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
    <!--Navbar-->
    <jsp:include page="/views/layout/navbar.jsp" />
    <!-- Sidebar -->
    <jsp:include page="/views/layout/sidebar.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Quản lý tài khoản</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Tài khoản</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <form  method="get" action="${pageContext.request.contextPath}/admin/account">
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <div class="form-group col-md-4 ml-auto">
                                            <div class="input-group">
                                                <input name="dateRange" type="text" class="form-control float-right" id="daterange-btn"
                                                value="${startDate}-${endDate}">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text bg-cyan">
                                                        <i class="far fa-calendar-alt"></i>
                                                    </span>
                                                </div>
                                            </div>
                                            <!-- /.input group -->
                                        </div>
                                        <div class="col-12 callout callout-info">
                                            <div class="row">
                                                <div class="col-md-2 mt-3">
                                                    <div class="form-group">
                                                        <select name="isActive" class="form-control select2" style="width: 100%;">
                                                            <option value="2" selected>Tất cả</option>
                                                            <c:choose>
                                                                <c:when test="${isActive}">
                                                                    <option value="1" selected>Đang hoạt động </option>
                                                                    <option value="0">Đã bị khóa</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="1">Đang hoạt động</option>
                                                                    <option value="0" selected>Đã bị khóa</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mt-3">
                                                    <div class="input-group">
                                                        <div class="input-group-append">
                                                            <button type="submit" class="bg-cyan btn btn-sidebar">
                                                                <i class="fa fa-filter"></i>Lọc
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.col -->
                                                <div class="col-md-1 mt-3 ml-auto">
                                                    <button href="${pageContext.request.contextPath}/admin/account" type="button" class="btn btn-block btn-info">Đặt lại</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-2 ml-auto mr-3">
                                            <a href="<%= request.getContextPath() %>/admin/account/add" class="btn btn-info btn-block">
                                                <i class="fas fa-plus-circle"></i> Thêm mới
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <!-- /.info-box -->
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Bảng quản lý thông tin thẻ</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th style="width: 50px">STT</th>
                                            <th>ID tài khoản</th>
                                            <th>Tên tài khoản</th>
                                            <th>Tên khách hàng</th>
                                            <th>Email</th>
                                            <th>Vai trò</th>
                                            <th>Trạng thái</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="account" items="${lstAccount}" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                                <td>${account.accountId}</td>
                                                <td>${account.userName}</td>
                                                <td>${account.fullName}</td>
                                                <td>${account.email}</td>
                                                <td>${account.roleName}</td>
                                                <td class="project-state">
                                                    <c:choose>
                                                        <c:when test="${account.status == 1}">
                                                            <span class="badge bg-cyan">Đang được sử dụng</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge bg-danger">Đang bị khóa</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/account/edit?id=${account.accountId}" class="btn btn-info btn-block">
                                                                        <i class="fas fa-pen-square"></i>
                                                                    </a>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/account/delete?id=${account.accountId}" class="btn btn-outline-warning btn-block" onclick="return confirm('Bạn có chắc muốn xóa?')">
                                                                        <i class="fas fa-trash-alt"></i>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <th style="width: 50px">STT</th>
                                            <th>ID tài khoản</th>
                                            <th>Tên tài khoản</th>
                                            <th>Tên khách hàng</th>
                                            <th>Email</th>
                                            <th>Vai trò</th>
                                            <th>Trạng thái</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </div>
                    </div>

                </div>
            </section>
        </div>
    </div>
</div>
<jsp:include page="/views/library/_script.jsp" />
</body>
</html>
