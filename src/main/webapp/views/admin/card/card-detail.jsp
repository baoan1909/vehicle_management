<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card Detail</title>
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
                        <h1 class="m-0">Thông tin chi tiết thẻ</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý thẻ</a></li>
                            <li class="breadcrumb-item active">Thông tin thẻ</li>
                        </ol>
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
                                    <h3 class="card-title">Thêm/Sửa thông tin thẻ</h3>
                                </div>
                                <!-- /.card-header -->
                                <!-- form start -->
                                <form>
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label>Số thẻ:</label>
                                            <input type="number" class="form-control" min="0" placeholder="002">
                                        </div>
                                        <div class="form-group">
                                            <label>Loại thẻ:</label>
                                            <select class="form-control select2" style="width: 100%;">
                                                <option selected="selected">Vãng lai</option>
                                                <option>Đăng ký/option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Loại xe:</label>
                                            <select class="form-control select2" style="width: 100%;">
                                                <option selected="selected">Mercedes</option>
                                                <option>Alaska</option>
                                                <option>California</option>
                                                <option>Delaware</option>
                                                <option>Tennessee</option>
                                                <option>Texas</option>
                                                <option>Washington</option>
                                            </select>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-check">
                                                    <input type="checkbox" class="form-check-input" id="isCreated">
                                                    <label class="form-check-label" for="isCreated">Đã tạo thẻ vậy lý</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-check">
                                                    <input type="checkbox" class="form-check-input" id="isUsed">
                                                    <label class="form-check-label" for="isUsed">Đã được sử dụng</label>
                                                </div>
                                            </div>
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
                    <!--Nội dung-->
                </div>
            </section>
        </div>
    </div>
</div>
<jsp:include page="/views/library/_script.jsp" />
</body>
</html>
