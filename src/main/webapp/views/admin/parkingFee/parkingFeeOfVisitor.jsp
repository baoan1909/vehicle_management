<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parking Fee Of Visitor</title>
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
                        <h1 class="m-0">Quản lý phí khách vãng lai</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Phí khách vãng lai</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12 mt-4">
                            <div class="card shadow">
                                <div class="card-body">
                                    <div class="form-group col-md-4 ml-auto">
                                        <div class="input-group">
                                            <input type="text" class="form-control float-right" id="daterange-btn">
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
                                            <!--Search -->
                                            <div class="col-md-4 mt-3">
                                                <div class="input-group">
                                                    <input type="search" class="form-control" placeholder="Giá vé">
                                                    <div class="input-group-append">
                                                        <button class="bg-cyan btn btn-sidebar">
                                                            <i class="fa fa-search"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-2 mt-3">
                                                <div class="form-group">
                                                    <select class="form-control select2" style="width: 100%;">
                                                        <option selected="selected">Loại xe</option>
                                                        <c:forEach var="vehicleType" items="${vehicleTypeList}">
                                                            <option value="${vehicleType.vehicleTypeId}">
                                                                    ${vehicleType.vehicleTypeName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <!-- /.col -->
                                            <div class="col-md-1 mt-3 ml-auto">
                                                <button type="button" class="btn btn-block btn-info">Đặt lại</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-2 ml-auto mr-3">
                                        <a href="${pageContext.request.contextPath}/admin/parkingFeeOfVisitor/add" class="btn btn-info btn-block">
                                            <i class="fas fa-plus-circle"></i> Thêm mới
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <!-- /.info-box -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Bảng quản lý thông tin phí khách vãng lai</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>ID phí vé</th>
                                            <th>Loại xe</th>
                                            <th>Giá Vé</th>
                                            <th>Ngày áp dụng</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="pfv" items="${parkingFeeOfVisitorDTOList}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${pfv.feeVisitorId}</td>
                                                <td>${pfv.vehicleTypeName}</td>
                                                <td>${pfv.parkingFeeOfVisitor}</td>
                                                <td>${pfv.startDate}</td>
                                                <td>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <a href="${pageContext.request.contextPath}/admin/parkingFeeOfVisitor/edit?id=${pfv.feeVisitorId}" class="btn btn-info btn-block">
                                                                <i class="fas fa-pen-square"></i>
                                                            </a>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <a href="${pageContext.request.contextPath}/admin/parkingFeeOfVisitor/delete?id=${pfv.feeVisitorId}" class="btn btn-outline-warning btn-block" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">
                                                                <i class="fas fa-trash-alt"></i>
                                                            </a>
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
                                            <th>Loại xe</th>
                                            <th>Giá Vé</th>
                                            <th>Ngày áp dụng</th>
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