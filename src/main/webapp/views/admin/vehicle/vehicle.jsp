<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vehicle</title>
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
                        <h1 class="m-0">Quản lý phương tiện</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Phương tiện</li>
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
                                    <div class="col-12 callout callout-info">
                                        <div class="row">
                                            <!--Search -->
                                            <div class="col-md-4 mt-3">
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
                                          
                                            <!-- /.col -->
                                            <div class="col-md-1 mt-3 ml-auto">
                                                <button type="button" class="btn btn-block btn-info">Đặt lại</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-2 ml-auto mr-3">
                                        <a href="<%= request.getContextPath() %>/admin/vehicle/vehicle-detail?page=vehicle-detail" class="btn btn-info btn-block">
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
                                    <h3 class="card-title">Bảng quản lý thông tin phương tiện</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Loại Xe</th>
                                            <th>Mô Tả</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Xe máy</td>
                                            <td>Xe hai bánh</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <button type="button" class="btn btn-info btn-block">
                                                                    <i class="fas fa-pen-square"></i>
                                                                </button>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <button type="button" class="btn btn-outline-warning btn-block">
                                                                    <i class="fas fa-trash-alt"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Ô tô</td>
                                            <td>Xe bốn bánh</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <button type="button" class="btn btn-info btn-block">
                                                                    <i class="fas fa-pen-square"></i>
                                                                </button>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <button type="button" class="btn btn-outline-warning btn-block">
                                                                    <i class="fas fa-trash-alt"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Xe tải</td>
                                            <td>Xe chở hàng hóa</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <button type="button" class="btn btn-info btn-block">
                                                                    <i class="fas fa-pen-square"></i>
                                                                </button>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <button type="button" class="btn btn-outline-warning btn-block">
                                                                    <i class="fas fa-trash-alt"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <th>STT</th>
                                            <th>Loại Xe</th>
                                            <th>Mô Tả</th>
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