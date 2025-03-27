<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/22/2025
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
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
                        <h1 class="m-0">Trang chủ</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                            <li class="breadcrumb-item active">Trang chủ</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="container mt-4">
                        <div class="col-12">
                            <div class="card card-cyan card-outline">
                                <div class="card-body">
                                    <div class="form-group col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text bg-cyan">
                                                    <i class="far fa-calendar-alt"></i>
                                                </span>
                                            </div>
                                            <input type="text" class="form-control float-right" id="daterange-btn">
                                        </div>
                                        <!-- /.input group -->
                                    </div>
                                    <div class="col-10 mx-auto"> <!-- Thêm mx-auto để căn giữa -->
                                        <div class="row info-box d-flex justify-content-center align-items-center shadow">
                                            <div class="col-md-4 text-center d-flex flex-column justify-content-center">
                                                <span class="info-box-text">Tổng doanh thu</span>
                                                <span class="info-box-number">3.192.120đ</span>
                                            </div>
                                            <div class="col-md-4 text-center d-flex flex-column justify-content-center">
                                                <span class="info-box-text">Khách vào bãi</span>
                                                <span class="info-box-number">4.295</span>
                                            </div>
                                            <div class="col-md-4 text-center d-flex flex-column justify-content-center">
                                                <span class="info-box-text">Thẻ đã đăng ký</span>
                                                <span class="info-box-number">1.000</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="row">
                            <!-- Area Chart -->
                            <div class="col-xl-8 col-lg-7">
                                <div class="card card-cyan card-outline shadow mb-4">
                                    <div class="card-header">
                                        <h3 class="card-title">Tổng doanh thu</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <!-- Card Body -->
                                    <div class="card-body">
                                        <div class="chart-area">
                                            <canvas id="myAreaChart" width="978" height="400" style="display: block; height: 320px; width: 783px;" class="chartjs-render-monitor"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-5">
                                <div class="card card-cyan card-outline shadow mb-4">
                                    <!-- Card Header - Dropdown -->
                                    <div class="card-header">
                                        <h3 class="card-title">Tổng loại xe</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <!-- Card Body -->
                                    <div class="card-body">
                                        <div class="chart-pie pt-4 pb-2">
                                            <canvas id="myPieChart" width="448" height="306" style="display: block; height: 245px; width: 359px;" class="chartjs-render-monitor"></canvas>
                                        </div>
                                        <div class="mt-4 text-center small">
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-primary"></i> Xe máy
                                        </span>
                                            <span class="mr-2">
                                            <i class="fas fa-circle text-success"></i> Ô tô
                                        </span>
                                            <span class="mr-2">
                                            <i class="fas fa-circle text-info"></i> Xe khác
                                        </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 mb-4">
                                <!-- Project Card Example -->
                                <div class="card card-cyan card-outline shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="card-title">Thẻ</h6>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h4 class="small font-weight-bold">Mất <span class="float-right">20%</span></h4>
                                        <div class="progress mb-4">
                                            <div class="progress-bar bg-yellowr" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <h4 class="small font-weight-bold">Thành viên <span class="float-right">40%</span></h4>
                                        <div class="progress mb-4">
                                            <div class="progress-bar bg-info" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <h4 class="small font-weight-bold">Vãng lai<span class="float-right">60%</span></h4>
                                        <div class="progress mb-4">
                                            <div class="progress-bar bg-success" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 mb-4">
                                <!-- Project Card Example -->
                                <div class="card card-cyan card-outline shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="card-title">Thiết bị</h6>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="d-sm-flex justify-content-between">
                                            <div class="col-xl-6 col-md-6 mb-4">
                                                <div class="card card-info card-outline shadow h-100 py-2">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col mr-2">
                                                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Camera
                                                                </div>
                                                                <div class="row no-gutters align-items-center">
                                                                    <div class="col-auto">
                                                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">50</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-auto">
                                                                <i class="fas fa-video fa-2x text-gray"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-md-6 mb-4">
                                                <div class="card card-yellow card-outline shadow h-100 py-2">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col mr-2">
                                                                <div class="text-xs font-weight-bold text-yellow text-uppercase mb-1">Đầu lọc thẻ
                                                                </div>
                                                                <div class="row no-gutters align-items-center">
                                                                    <div class="col-auto">
                                                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">3</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-auto">
                                                                <i class="fas fa-sd-card fa-2x text-gray"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-sm-flex justify-content-between">
                                            <div class="col-xl-6 col-md-6 mb-4">
                                                <div class="card card-green card-outline shadow h-100 py-2">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col mr-2">
                                                                <div class="text-xs font-weight-bold text-green text-uppercase mb-1">Máy tính
                                                                </div>
                                                                <div class="row no-gutters align-items-center">
                                                                    <div class="col-auto">
                                                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">4</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-auto">
                                                                <i class="fas fa-desktop fa-2x text-gray"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-md-6 mb-4">
                                                <div class="card card-primary card-outline shadow h-100 py-2">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col mr-2">
                                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Barie
                                                                </div>
                                                                <div class="row no-gutters align-items-center">
                                                                    <div class="col-auto">
                                                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">7</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-auto">
                                                                <i class="fas fa-pallet fa-2x text-gray"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
