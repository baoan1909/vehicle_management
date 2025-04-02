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
                    <div class="row d-flex justify-content-center mt-4">
                        <div class="col-md-10">
                            <div class="card card-cyan">
                                <div class="card-header">
                                    <h3 class="card-title">Thêm/Sửa thông tin khách hàng</h3>
                                </div>
                                <!-- /.card-header -->
                                <!-- form start -->
                                <form>
                                    <div class="card-body">
                                        <div class="form-group">
                                                <label>Tên đăng nhập:</label>
                                            <input type="text" class="form-control"  placeholder="nguyenvana">
                                        </div>
                                        <div class="form-group">
                                            <label>Mật khẩu:</label>
                                            <input type="password" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Nhập lại mật khẩu:</label>
                                            <input type="password" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Họ tên:</label>
                                            <input type="text" class="form-control" placeholder="Nguyễn Văn A">
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="row">
                                                    <label>Ngày sinh/ Giới tính</label>
                                                    <input type="date" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <label><br></label>
                                                <input type="text" class="form-control" placeholder="Nam">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Số điện thoại:</label>
                                            <input type="text" class="form-control" placeholder="0123456789">
                                        </div>
                                        <div class="form-group">
                                            <label>Địa chỉ:</label>
                                            <input type="text" class="form-control" placeholder="123/4 Thủ Đức">
                                        </div>
                                        <div class="form-group">
                                            <label>CCCD:</label>
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <a class="btn btn-default" href="<%= request.getContextPath() %>/admin/card?page=card">Thoát</a>
                                        <button type="submit" class="btn btn-info float-right"><i class="fas fa-save"></i> Lưu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
    <jsp:include page="/views/library/_script.jsp" />
</body>
</html>
