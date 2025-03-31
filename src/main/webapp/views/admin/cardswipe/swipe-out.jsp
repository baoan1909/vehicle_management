<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Swipe out</title>
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
                        <h1 class="m-0">Quản lý xe ra</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý vào ra</a></li>
                            <li class="breadcrumb-item active">Xe ra</li>
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
                                <div class="form-group col-md-1 ml-auto mr-3">
                                    <a href="<%= request.getContextPath() %>/admin/card-swipe?page=card-swipe" class="btn btn-outline-dark btn-block">
                                        <i class="fas fa-arrow-alt-circle-left"></i> Thoát
                                    </a>
                                </div>
                            </div>
                            <div class="card shadow">
                                <div class="card-body">
                                    <div class="col-12 card card-cyan card-outline">
                                        <div class="row ml-3">
                                            <div class="col-md-2 mt-3">
                                                <div class="form-group">
                                                    <label>ID thẻ xe:</label>
                                                    <select class="form-control select2" style="width: 100%;">
                                                        <option selected="selected">001</option>
                                                        <option>002</option>
                                                        <option>003</option>
                                                        <option>004</option>
                                                        <option>005</option>
                                                        <option>006</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-2 mt-3">
                                                <div class="form-group">
                                                    <label>Loại thẻ:</label>
                                                    <input type="text" class="form-control" placeholder="Vãng lai/Đăng ký" disabled="">
                                                </div>
                                            </div>
                                            <div class="col-md-2 mt-3">
                                                <div class="form-group">
                                                    <label>Loại xe:</label>
                                                    <input type="text" class="form-control" placeholder="Loại xe" disabled="">
                                                </div>
                                            </div>
                                            <div class="col-md-4 mt-3">
                                                <div class="form-group">
                                                    <label>Biển số:</label>
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="Biển số xe" disabled="">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row ml-3">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>Thành tiển</label>
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="200000" disabled="">
                                                        <div class="input-group-append">
                                                            <span class="input-group-text bg-cyan">VNĐ</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.info-box -->
                        </div>
                    </div>
                    <div class="col-12 mt-3">
                        <div class="row">
                            <div class="col-xl-4">
                                <div class="card card-cyan">
                                    <div class="card-header">
                                        <h3 class="card-title">Hình ảnh xe vào</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <div class="col-lx-12">
                                            <div class="position-relative" style="min-height: 250px;">
                                                <img src="<%= request.getContextPath() %>/assets/admin/dist/img/photo1.png" alt="Photo 1" class="img-fluid">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <!-- Date and time -->
                                        <div class="form-group">
                                            <label>Thời gian vào:</label>
                                            <div class="input-group date">
                                                <input type="text" class="form-control datetimepicker-input" disabled=""/>
                                                <div class="input-group-append">
                                                    <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.form group -->
                                    </div>
                                </div>
                                <!-- /.card -->
                            </div>
                            <div class="col-xl-4">
                                <div class="row d-flex justify-content-center" style="min-height: 250px;">
                                    <div class="mt-auto">
                                        <button class="btn btn-info">
                                            <i class="fas fa-qrcode"></i> Quét
                                        </button>
                                    </div>
                                </div>
                                <div class="row d-flex justify-content-center" style="min-height: 190px;">
                                    <div class="mt-auto">
                                        <button class="btn btn-primary">
                                            <i class="fas fa-save"></i> Lưu
                                        </button>
                                    </div>
                                </div>
                                <div class="row d-flex justify-content-center">
                                    <div class="mt-1">
                                        <button class="btn btn-danger">
                                            <i class="fas fa-times"></i> Hủy
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4">
                                <div class="card card-cyan">
                                    <div class="card-header">
                                        <h3 class="card-title">Hình ảnh xe ra</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <div class="col-lx-12">
                                            <div class="position-relative" style="min-height: 250px;">
                                                <img src="<%= request.getContextPath() %>/assets/admin/dist/img/photo1.png" alt="Photo 1" class="img-fluid">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <!-- Date and time -->
                                        <div class="form-group">
                                            <label>Thời gian ra:</label>
                                            <div class="input-group date" id="reservationdatetime" data-target-input="nearest">
                                                <input type="text" class="form-control datetimepicker-input" data-target="#reservationdatetime"/>
                                                <div class="input-group-append" data-target="#reservationdatetime" data-toggle="datetimepicker">
                                                    <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.form group -->
                                    </div>
                                </div>
                                <!-- /.card -->
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
