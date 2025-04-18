<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">
<html>
<head>
    <title>Parking Fee Of Customer</title>
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
                        <h1 class="m-0">Quản lý phí khách đăng ký</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Phí khách đăng ký</li>
                        </ol>
                    </div>
                </div>
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12 mt-4">
                            <div class="card shadow">
                                <div class="card-body">
                                    <form  method="get" action="${pageContext.request.contextPath}/admin/parkingFeeOfCustomer">
                                        <!-- Chọn khoảng thời gian -->
                                        <div class="form-group col-md-4 ml-auto">
                                            <div class="input-group">
                                                <input type="text" class="form-control" name="dateRange" id="daterange-btn"
                                                   value="${startDate}-${endDate}">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text bg-cyan">
                                                        <i class="far fa-calendar-alt"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 callout callout-info">
                                            <div class="row form-group mt-4">
                                                <div class="col-md-2">
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

                                                <!-- Lọc theo loại xe -->
                                                <div class="col-md-3">
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
                                                <div class="col-md-3">
                                                    <div class="input-group">
                                                        <div class="input-group-append">
                                                            <button class="bg-cyan btn btn-sidebar" type="submit">
                                                                <i class="fa fa-filter"></i> Lọc
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- Nút đặt lại -->
                                                <div class="col-md-2 ml-auto">
                                                    <button href="${pageContext.request.contextPath}/admin/parkingFeeOfCustomer" class="btn btn-info btn-block">
                                                        Đặt lại
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-2 ml-auto">
                                                <a href="${pageContext.request.contextPath}/admin/parkingFeeOfVisitor/add" class="btn btn-info btn-block">
                                                    <i class="fas fa-plus-circle"></i> Thêm mới
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Nút thêm mới -->


                    <!-- Table -->
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Bảng quản lý thông tin phí khách đăng ký</h3>
                                </div>
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>ID phí vé</th>
                                                <th>Loại vé</th>
                                                <th>Loại xe</th>
                                                <th>Giá vé</th>
                                                <th>Ngày áp dụng</th>
                                                <th style="width: 100px">Chức năng</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="pfc" items="${parkingFeeOfCustomerDTOList}" varStatus="loop">
                                                <tr>
                                                    <td>${loop.count}</td>
                                                    <td>${pfc.feeCustomerId}</td>
                                                    <td>${pfc.ticketTypeName}</td>
                                                    <td>${pfc.vehicleTypeName}</td>
                                                    <td>${pfc.price}</td>
                                                    <td>${pfc.startDate}</td>
                                                    <td>
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <a href="${pageContext.request.contextPath}/admin/parkingFeeOfCustomer/edit?id=${pfc.feeCustomerId}" class="btn btn-info btn-block">
                                                                            <i class="fas fa-pen-square"></i>
                                                                        </a>
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <a href="${pageContext.request.contextPath}/admin/parkingFeeOfCustomer/delete?id=${pfc.feeCustomerId}" class="btn btn-outline-warning btn-block">
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
                                                <th>ID phí vé</th>
                                                <th>Loại vé</th>
                                                <th>Loại xe</th>
                                                <th>Giá vé</th>
                                                <th>Ngày áp dụng</th>
                                                <th style="width: 100px">Chức năng</th>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
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
