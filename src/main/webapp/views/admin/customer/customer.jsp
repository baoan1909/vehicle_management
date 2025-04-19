<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/25/2025
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer</title>
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
                        <h1 class="m-0">Quản lý khách hàng</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Khách hàng</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <form  method="get" action="${pageContext.request.contextPath}/admin/customer">
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <div class="form-group col-md-4 ml-auto">
                                            <div class="input-group">
                                                <input name="dateRange" type="text" class="form-control float-right" id="daterange-btn">
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
                                                        <select name="vehicleTypeId" class="form-control select2" style="width: 100%;">
                                                            <option value="">Tất cả loại xe</option>
                                                            <c:forEach var="vehicleType" items="${vehicleTypeList}">
                                                                <option value="${vehicleType.vehicleTypeId}"
                                                                        <c:if test="${vehicleType.vehicleTypeId == vehicleTypeFilter}">selected</c:if>>
                                                                        ${vehicleType.vehicleTypeName}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mt-3">
                                                    <div class="form-group">
                                                        <select name="ticketTypeId" class="form-control select2" style="width: 100%;">
                                                            <option value="">Tất cả loại vé</option>
                                                            <c:forEach var="ticketType" items="${ticketTypeList}">
                                                                <option value="${ticketType.ticketTypeId}"
                                                                        <c:if test="${ticketType.ticketTypeId == ticketTypeFilter}">selected</c:if>>
                                                                        ${ticketType.ticketTypeName}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <!--Search -->
                                                <div class="col-md-4 mt-3">
                                                    <div class="input-group">
                                                        <div class="input-group-append">
                                                            <button type="submit" class="bg-cyan btn btn-sidebar">
                                                                <i class="fa fa-filter"></i> Lọc
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.col -->
                                                <div class="col-md-2 ml-auto">
                                                    <a  href="${pageContext.request.contextPath}/admin/customer" class="btn btn-info btn-block">
                                                        Đặt lại
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-2 ml-auto mr-3">
                                            <a href="<%= request.getContextPath() %>/admin/customer/add" class="btn btn-info btn-block">
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
                                    <h3 class="card-title">Bảng quản lý thông tin khách hàng</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Số thẻ</th>
                                            <th>ID Khách hàng</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại xe</th>
                                            <th>Biển số</th>
                                            <th>Loại vé</th>
                                            <th>Ngày đăng ký</th>
                                            <th>Ngày hết hạn</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="customer" items="${customerDTOList}" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                                <td>${customer.cardNumber}</td>
                                                <td>${customer.customerId}</td>
                                                <td>${customer.customerName}</td>
                                                <td>${customer.customerPhoneNumber}</td>
                                                <td>${customer.vehicleTypeName}</td>
                                                <td>${customer.licensePlate}</td>
                                                <td>${customer.ticketTypeName}</td>
                                                <td>${customer.effectiveDate}</td>
                                                <td>${customer.expirationDate}</td>
                                                <td>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/customer/edit?id=${customer.customerRegisterTicketId}" class="btn btn-info btn-block">
                                                                        <i class="fas fa-pen-square"></i>
                                                                    </a>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/customer/delete?id=${customer.customerRegisterTicketId}&customerId=${customer.customerId}" class="btn btn-info btn-block" onclick="return confirm('Bạn có chắc muốn xóa?')">
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
                                            <th>STT</th>
                                            <th>Số thẻ</th>
                                            <th>ID Khách hàng</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại xe</th>
                                            <th>Biển số</th>
                                            <th>Loại vé</th>
                                            <th>Ngày đăng ký</th>
                                            <th>Ngày hết hạn</th>
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
