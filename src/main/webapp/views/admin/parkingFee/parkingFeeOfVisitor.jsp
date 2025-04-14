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
                    <form method="get" action="${pageContext.request.contextPath}/admin/parkingFeeOfVisitor">
                        <div class="row">

                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="dateRange" id="daterange-btn" value="${startDate}-${endDate}">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-cyan">
                                            <i class="far fa-calendar-alt"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>


                            <!-- Input tìm kiếm theo giá vé -->
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input name="search" type="search" class="form-control" placeholder="Giá vé..." value="${search}">
                                    <div class="input-group-append">
                                        <button class="bg-cyan btn btn-sidebar" type="submit">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <!-- Lọc theo loại xe -->
                            <div class="col-md-3">
                                <select name="vehicleTypeId" class="form-control select2">
                                    <option value="" ${(1==1) ? "selected" : ""}>Loại xe</option>
                                    <c:forEach var="vehicleType" items="${vehicleTypeList}">
                                        <option value="${vehicleType.vehicleTypeId}" >
                                                ${vehicleType.vehicleTypeName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Nút đặt lại -->
                            <div class="col-md-2">
                                <a href="${pageContext.request.contextPath}/admin/parkingFeeOfVisitor" class="btn btn-info btn-block">
                                    Đặt lại
                                </a>
                            </div>
                        </div>
                    </form>

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
<script>
    document.querySelector('select[name="vehicleTypeId"]').addEventListener('change', function () {
        this.form.submit();
    });
</script>

</body>
</html>