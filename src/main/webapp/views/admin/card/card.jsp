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
                                                    <input type="search" class="form-control" placeholder="Mã thẻ, Số thẻ">
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
                                                        <option>Alaska</option>
                                                        <option>California</option>
                                                        <option>Delaware</option>
                                                        <option>Tennessee</option>
                                                        <option>Texas</option>
                                                        <option>Washington</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-2 mt-3">
                                                <div class="form-group">
                                                    <select class="form-control select2" style="width: 100%;">
                                                        <option selected="selected">Loại thẻ</option>
                                                        <option>Alaska</option>
                                                        <option>California</option>
                                                        <option>Delaware</option>
                                                        <option>Tennessee</option>
                                                        <option>Texas</option>
                                                        <option>Washington</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-2 mt-3">
                                                <div class="form-group">
                                                    <select class="form-control select2" style="width: 100%;">
                                                        <option selected="selected">Đã tạo thẻ</option>
                                                        <option>Chưa tạo thẻ</option>
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
                                        <a href="<%= request.getContextPath() %>/admin/card/card-detail?page=card-detail" class="btn btn-info btn-block">
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
                                            <th>Đã tạo thẻ vậy lý</th>
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
                                                <td>${card.isCreated ? "X" : ""}</td>
                                                <td class="project-state">
                                                    <span class="badge ${(card.isCreated && card.isUsed ) ? 'bg-cyan' : (card.isCreated && not card.isUsed) ? 'bg-yellow' : 'bg-red' }">
                                                            ${(card.isCreated && card.isUsed) ? "Đang được sử dụng" : 'Chưa được sử dụng' }
                                                    </span>
                                                </td>
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
                                            <th>Đã tạo thẻ vậy lý</th>
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
