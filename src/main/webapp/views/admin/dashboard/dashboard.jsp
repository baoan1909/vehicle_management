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
    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/vendor/fontawesome-free/css/all.min.css">
<%--    <!-- Ionicons -->--%>
<%--    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">--%>
    <!-- Tempusdominus Bootstrap 4 -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/vendor/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/vendor/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- JQVMap -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/vendor/jqvmap/jqvmap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/dist/css/adminlte.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/vendor/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/vendor/daterangepicker/daterangepicker.css">
    <!-- summernote -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/vendor/summernote/summernote-bs4.min.css">
    <!-- Editor Style -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/admin/style.css">
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
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <button type="button" class="btn btn-default float-right ml-5" id="daterange-btn">
                                    <i class="far fa-calendar-alt"></i>
                                </button>
                            </div>
                            <input type="text" class="form-control float-right" id="reservation">
                        </div>
                        <div class="row ml-4 mr-4">
                            <div class="col-12 ml-4 mr-4">
                                <div class="info-box card-cyan card-outline">
                                    <div class="info-box-content text-center">
                                        <span class="info-box-text">Tổng doanh thu</span>
                                        <span class="info-box-number">3.192.120đ</span>
                                    </div>
                                    <div class="info-box-content text-center">
                                        <span class="info-box-text">khách vào bãi</span>
                                        <span class="info-box-number">4.295</span>
                                    </div>
                                    <div class="info-box-content text-center">
                                        <span class="info-box-text">Thẻ đã đăng ký</span>
                                        <span class="info-box-number">1.000</span>
                                    </div>
                                    <!-- /.info-box-content -->
                                </div>
                                <!-- /.info-box -->
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/jquery-ui/jquery-ui.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Select2 -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/select2/js/select2.full.min.js"></script>
<!-- bootstrap color picker -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- Bootstrap4 Duallistbox -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
<!-- BS-Stepper -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/bs-stepper/js/bs-stepper.min.js"></script>
<!-- ChartJS -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/jqvmap/jquery.vmap.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/admin/vendor/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/moment/moment.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/admin/vendor/inputmask/jquery.inputmask.min.js"></script>

<script src="<%= request.getContextPath() %>/assets/admin/vendor/daterangepicker/daterangepicker.js"></script>
<!-- Bootstrap Switch -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- dropzonejs -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/dropzone/min/dropzone.min.js"></script>
<!-- Summernote -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="<%= request.getContextPath() %>/assets/admin/vendor/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="<%= request.getContextPath() %>/assets/admin/dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<%= request.getContextPath() %>/assets/admin/dist/js/pages/dashboard.js"></script>
<!--js -->
<script src="<%= request.getContextPath() %>/assets/admin/js.js"></script>
</body>
</html>
