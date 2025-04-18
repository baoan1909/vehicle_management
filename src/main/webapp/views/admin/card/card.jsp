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
    <title>Card</title>
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
                        <h1 class="m-0">Quản lý thẻ</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Thẻ</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <form  method="get" action="${pageContext.request.contextPath}/admin/card">
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <div class="form-group col-md-4 ml-auto">
                                            <div class="input-group">
                                                <input name="dateRange" type="text" class="form-control float-right" id="daterange-btn" value="${startDate} - ${endDate}">
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
                                                        <select name="isCreated" class="form-control select2" style="width: 100%;">
                                                            <option value="2">Tất cả loại thẻ</option>
                                                            <c:choose>
                                                                <c:when test="${card.isCreated}">
                                                                    <option value="1" selected>Đã tạo thẻ</option>
                                                                    <option value="0">Chưa tạo thẻ</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="1">Đã tạo thẻ</option>
                                                                    <option value="0" selected>Chưa tạo thẻ</option>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mt-3">
                                                    <div class="input-group">
                                                        <div class="input-group-append">
                                                            <button class="bg-cyan btn btn-sidebar" type="submit">
                                                                <i class="fa fa-filter"></i> Lọc
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.col -->
                                                <div class="col-md-1 mt-3 ml-auto">
                                                    <button type="button" href="${pageContext.request.contextPath}/admin/card" class="btn btn-block btn-info">Đặt lại</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-2 ml-auto mr-3">
                                            <a href="<%= request.getContextPath() %>/admin/card/add" class="btn btn-info btn-block">
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
                                            <th>ID thẻ</th>
                                            <th>Số thẻ</th>
                                            <th>Loại thẻ</th>
                                            <th>Loại xe</th>
                                            <th>Thẻ vậy lý</th>
                                            <th>Trạng thái</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="card" items="${lstCards}" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                                <td>${card.cardId}</td>
                                                <td>${card.cardNumber}</td>
                                                <td>${card.type}</td>
                                                <td>${card.vehicleTypeName}</td>
                                                <td class="project-state">
                                                    <c:choose>
                                                        <c:when test="${card.isCreated == 1}">
                                                            <span class="badge bg-cyan">Đã tạo</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge bg-danger">Chưa tạo</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="project-state">
                                                    <c:choose>
                                                        <c:when test="${card.isUsed == 1}">
                                                            <span class="badge bg-cyan">Đã sử dụng</span>
                                                        </c:when>
                                                        <c:when test="${card.isUsed == 0 && card.isCreated == 1}">
                                                            <span class="badge bg-yellow">Chưa sử dụng</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge bg-danger">Chưa sử dụng</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
<%--                                                <td class="project-state">--%>
<%--                                                    <span class="badge ${card.isUsed ? 'bg-cyan' : 'bg-red'}">--%>
<%--                                                        ${card.isUsed ? 'Đang được sử dụng' : 'Chưa được sử dụng'}--%>
<%--                                                    </span>--%>
<%--                                                </td>--%>
                                                <td>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/card/edit?id=${card.cardId}" class="btn btn-info btn-block">
                                                                        <i class="fas fa-pen-square"></i>
                                                                    </a>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/card/delete?id=${card.cardId}" class="btn btn-outline-warning btn-block" onclick="return confirm('Bạn có chắc muốn xóa?')">
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
                                            <th>ID thẻ</th>
                                            <th>Số thẻ</th>
                                            <th>Loại thẻ</th>
                                            <th>Loại xe</th>
                                            <th>Thẻ vậy lý</th>
                                            <th>Trạng thái</th>
                                            <th>Chức năng</th>
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
