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
    <title>Ticket</title>
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
                        <h1 class="m-0">Quản lý vé</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Vé</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12 mt-4">
                                <div class="row">
                                    <div class="form-group col-2 ml-auto mr-3">
                                        <a href="${pageContext.request.contextPath}/admin/ticket/add" class="btn btn-info btn-block">
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
                                    <h3 class="card-title">Bảng quản lý thông tin vé</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th style="width: 50px">STT</th>
                                            <th style="width: 50px">ID vé</th>
                                            <th>Loại vé</th>
                                            <th>Mô tả</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="ticket" items="${lstTicketType}" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                                <td>${ticket.ticketTypeId}</td>
                                                <td>${ticket.ticketTypeName}</td>
                                                <td>${ticket.description}</td>
                                                <td>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/ticket/edit?id=${ticket.ticketTypeId}" class="btn btn-info btn-block">
                                                                        <i class="fas fa-pen-square"></i>
                                                                    </a>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <a href="${pageContext.request.contextPath}/admin/ticket/delete?id=${ticket.ticketTypeId}" class="btn btn-outline-warning btn-block" onclick="return confirm('Bạn có chắc muốn xóa?')">
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
                                            <th>ID vé</th>
                                            <th>Loại vé</th>
                                            <th>Mô tả</th>
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