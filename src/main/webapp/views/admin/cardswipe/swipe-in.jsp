<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/27/2025
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Swipe in</title>
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
                        <h1 class="m-0">Quản lý xe vào </h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý vào ra</a></li>
                            <li class="breadcrumb-item active">Xe vào</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid m-4">
                    <div class="row">
                        <div class="form-group col-md-1 ml-auto mr-3">
                            <a href="<%= request.getContextPath() %>/admin/card-swipe?page=card-swipe" class="btn btn-outline-dark btn-block">
                                <i class="fas fa-arrow-alt-circle-left"></i> Thoát
                            </a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-5 ml-3">
                            <div class="card card-cyan">
                                <div class="card-header">
                                    <h3 class="card-title">Hình ảnh xe vào</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <div class="col-lx-12">
                                        <div class="position-relative" style="min-height: 400px;">
                                            <img src="<%= request.getContextPath() %>/assets/admin/dist/img/photo1.png" alt="Photo 1" class="img-fluid">
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </div>
                        <div class="col-xl-6 ml-3">
                            <div class="card card-cyan card-outline shadow">
                                <div class="card-header ">
                                    <h3 class="card-title">Thông tin xe vào</h3>
                                    <div class="card-tools">
                                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                            <i class="fas fa-minus"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <!--Search -->
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>ID thẻ xe</label>
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
                                    <div class="col-md-12 mt-3">
                                        <div class="form-group">
                                            <label>Loại xe</label>
                                            <select class="form-control select2" style="width: 100%;">
                                                <option selected="selected">Xe máy</option>
                                                <option>Ô tô</option>
                                                <option>Xe khác</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mt-3">
                                        <div class="form-group">
                                            <label>Loại thẻ</label>
                                            <input type="text" class="form-control" placeholder="Vãng lai/Đăng ký" disabled="">
                                        </div>
                                    </div>
                                    <div class="col-md-12 mt-3">
                                        <div class="form-group">
                                            <label>Biển số</label>
                                            <div class="row">
                                                <div class="col-xl-5">
                                                    <div class="custom-file">
                                                        <input type="file" class="custom-file-input" id="customFile">
                                                        <label class="custom-file-label" for="customFile">Chọn tệp</label>
                                                    </div>
                                                </div>
                                                <div class="col-xl-7">
                                                    <input type="text" class="form-control" placeholder="80H-1826-127" disabled="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-12 mt-3">
                                        <!-- Date and time -->
                                        <div class="form-group">
                                            <label>Thời gian vào:</label>
                                            <div class="input-group date" id="reservationdatetime" data-target-input="nearest">
                                                <input type="text" class="form-control datetimepicker-input" data-target="#reservationdatetime"/>
                                                <div class="input-group-append" data-target="#reservationdatetime" data-toggle="datetimepicker">
                                                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.form group -->
                                        <div class="row">
                                            <div class="col-12">
                                                <a href="#" class="btn btn-outline-warning">Hủy</a>
                                                <input type="submit" value="Lưu" class="btn btn-info">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                    </div>

                </div>
            </section>
        </div>
    </div>
</div>
<jsp:include page="/views/library/_script.jsp" />
<!-- Page specific script -->
<script>
    $(function () {
        bsCustomFileInput.init();
    });
</script>
</body>
</html>
