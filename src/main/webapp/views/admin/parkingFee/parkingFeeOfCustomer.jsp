<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registered Customer</title>
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
                        <h1 class="m-0">Quản lý khách đăng ký</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Khách đăng ký</li>
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
                                                    <input type="search" class="form-control" placeholder="Tìm kiếm">
                                                    <div class="input-group-append">
                                                        <button class="bg-cyan btn btn-sidebar">
                                                            <i class="fa fa-search"></i>
                                                        </button>
                                                    </div>
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
                                        <a href="<%= request.getContextPath() %>/admin/parkingFeeOfCustomer/parkingFeeOfCustomer-detail?page=parkingFeeOfCustomer-detail" class="btn btn-info btn-block">
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
                                    <h3 class="card-title">Bảng quản lý thông tin khách đăng ký</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Loại Vé</th>
                                            <th>Loại Xe</th>
                                            <th>Giá Vé</th>
                                            <th>Ngày Áp Dụng</th>
                                            <th style="width: 100px">Chức năng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Vé tháng</td>
                                            <td>Xe máy</td>
                                            <td>200,000 VNĐ</td>
                                            <td>01/03/2025</td>
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
                                            <td>Vé năm</td>
                                            <td>Ô tô</td>
                                            <td>5,000,000 VNĐ</td>
                                            <td>15/03/2025</td>
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
                                            <td>Vé quý</td>
                                            <td>Xe tải</td>
                                            <td>1,800,000 VNĐ</td>
                                            <td>20/03/2025</td>
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
                                            <th>Loại Vé</th>
                                            <th>Loại Xe</th>
                                            <th>Giá Vé</th>
                                            <th>Ngày Áp Dụng</th>
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