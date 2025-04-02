<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card</title>
    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition layout-top-nav">
<div class="wrapper">
    <!--Navbar-->
    <jsp:include page="/views/layout/navbar_client.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Thông tin khách hàng</h1>
                    </div><!-- /.col -->

                </div><!-- /.row -->
            </div>

            <!-- Content Wrapper. Contains page content -->
            <section class="content">
                <div class="container-fluid">

                    <!--Nội dung-->
                    <div class="row mt-4">
                        <div class="col-md-3">
                            <div class="info-box" style="background:linear-gradient(135deg, var(--primary), var(--indigo) ) ;color: white; box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);">
                                <div class="info-box-content">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <span class="info-box-text">Tổng vào: </span>
                                        </div>
                                        <div class="col-md-6">
                                            <span class="info-box-number">41</span>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3">
                            <div class="info-box" style="background:linear-gradient(135deg, var(--primary), var(--indigo) ) ;color: white; box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);">
                                <div class="info-box-content">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <span class="info-box-text">Tổng ra: </span>
                                        </div>
                                        <div class="col-md-6">
                                            <span class="info-box-number">41</span>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header" style="background:linear-gradient(135deg, var(--primary), var(--indigo) ) ;color: white; box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);">
                                    <h3 class="card-title">Bảng quản lý thông tin thẻ</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên chủ xe</th>
                                            <th>Biển số xe</th>
                                            <th>Giờ vào gần nhất</th>
                                            <th>Giờ ra gần nhất</th>
                                            <th>Loại thẻ</th>
                                            <th>Ngày hết hạn</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Nguyễn Văn A </td>
                                            <td>12345-67</td>
                                            <td> 2024-03-01 07:00:00</td>
                                            <td> 2024-03-01 18:00:00</td>
                                            <td> Thẻ đăng ký </td>
                                            <td>2024-04-01</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Nguyễn Văn B </td>
                                            <td>12345-68</td>
                                            <td> 2024-03-01 07:00:00</td>
                                            <td> 2024-03-01 18:00:00</td>
                                            <td> Thẻ đăng ký </td>
                                            <td>2024-04-01</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Nguyễn Văn C </td>
                                            <td>12345-67</td>
                                            <td> 2024-03-01 07:00:00</td>
                                            <td> 2024-03-01 18:00:00</td>
                                            <td> Thẻ đăng ký </td>
                                            <td>2024-04-01</td>
                                        </tr>


                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên chủ xe</th>
                                            <th>Biển số xe</th>
                                            <th>Giờ vào gần nhất</th>
                                            <th>Giờ ra gần nhất</th>
                                            <th>Loại thẻ</th>
                                            <th>Ngày hết hạn</th>
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