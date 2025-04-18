<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Parking Fee Of Visitor Detail</title>
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
                        <h1 class="m-0">Thông tin chi tiết phí khách vãng lai</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý phí khách vãng lai</a></li>
                            <li class="breadcrumb-item active">Thông tin phí khách vãng lai</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row d-flex justify-content-center mt-4">
                        <div class="col-md-10">
                            <div class="card card-cyan">
                                <div class="card-header">
                                    <h3 class="card-title">Thêm/ Sửa thông tin phí khách vãng lai</h3>
                                </div>
                                <!-- /.card-header -->
                                <!-- form start -->
                                <form action="${pageContext.request.contextPath}/admin/parkingFeeOfVisitor/save" method="post">
                                    <div class="card-body">
                                        <c:if test="${not empty parkingFeeOfVisitorDTO.feeVisitorId}">
                                            <input type="hidden" name="id" value="${parkingFeeOfVisitorDTO.feeVisitorId}" />
                                        </c:if>
                                        <div class="form-group">
                                            <label>Loại xe:</label>
                                            <select class="form-control select2" name="vehicleTypeId">
                                                <c:forEach var="vehicleType" items="${vehicleTypeList}">
                                                    <option value="${vehicleType.vehicleTypeId}" ${parkingFeeOfVisitorDTO.vehicleTypeName==vehicleType.vehicleTypeName ? "selected" : ""}>
                                                            ${vehicleType.vehicleTypeName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Giá vé:</label>
                                            <div class="input-group">
                                                <input type="text" name="price" class="form-control" value="${parkingFeeOfVisitorDTO.parkingFeeOfVisitor}">
                                                <div class="input-group-append">
                                                    <span class="input-group-text bg-cyan">VNĐ</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Ngày áp dụng:</label>
                                            <div class="input-group date" id="reservationdate" data-target-input="nearest">
                                                <fmt:formatDate value="${formattedStartDate}" pattern="MM/dd/yyyy" var="formattedStartDateStr" />
                                                <input name="startDate" type="text" class="form-control datetimepicker-input"
                                                       data-target="#reservationdate"
                                                       value="${formattedStartDateStr}" />

                                                <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
                                                    <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <a class="btn btn-default" href="<%= request.getContextPath() %>/admin/parkingFeeOfVisitor?page=parkingFeeOfVisitor">Thoát</a>
                                        <button type="submit" class="btn btn-info float-right"><i class="fas fa-save"></i> Lưu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!--Nội dung-->
                </div>
            </section>
        </div>
    </div>
</div>
<jsp:include page="/views/library/_script.jsp" />
</body>
</html>