<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lost card detail</title>
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
                        <h1 class="m-0">Quản lý mất thẻ</h1>
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
                    <div class="col-md-12">
                        <div class="row">
                            <div class="form-group col-md-2 ml-auto mr-3">
                                <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#modal-xl">
                                    <i class="fas fa-search"></i> Tìm kiếm xe
                                </button>
                            </div>
                        </div>
                        <div class="card card-cyan">
                            <div class="card-header">
                                <h3 class="card-title">Thêm/Sửa thông tin mất thẻ</h3>
                            </div>
                            <!-- /.card-header -->
                            <!-- form start -->
                            <form>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>ID thẻ:</label>
                                                <select class="form-control select2">
                                                    <option selected="selected">Mercedes</option>
                                                    <option>Alaska</option>
                                                    <option>California</option>
                                                    <option>Delaware</option>
                                                    <option>Tennessee</option>
                                                    <option>Texas</option>
                                                    <option>Washington</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>ID khách hàng:</label>
                                                <select class="form-control select2">
                                                    <option selected="selected">Mercedes</option>
                                                    <option>Alaska</option>
                                                    <option>California</option>
                                                    <option>Delaware</option>
                                                    <option>Tennessee</option>
                                                    <option>Texas</option>
                                                    <option>Washington</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Thời gian thông báo:</label>
                                                <div class="input-group date" id="reservationdatetime" data-target-input="nearest">
                                                    <input type="text" class="form-control datetimepicker-input" data-target="#reservationdatetime"/>
                                                    <div class="input-group-append" data-target="#reservationdatetime" data-toggle="datetimepicker">
                                                        <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Thời gian mất:</label>
                                                <div class="input-group date" id="reservationdatetime1" data-target-input="nearest">
                                                    <input type="text" class="form-control datetimepicker-input" data-target="#reservationdatetime1"/>
                                                    <div class="input-group-append" data-target="#reservationdatetime1" data-toggle="datetimepicker">
                                                        <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Số thẻ:</label>
                                                <input type="number" class="form-control" min="0" placeholder="002">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Loại thẻ:</label>
                                                <select class="form-control select2" style="width: 100%;">
                                                    <option selected="selected">Vãng lai</option>
                                                    <option>Đăng ký</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Tên khách hàng:</label>
                                                <input type="text" class="form-control" placeholder="Nguyễn Văn A">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>CCCD:</label>
                                                <input type="text" class="form-control" placeholder="8362632742">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Phí gửi xe:</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control" placeholder="200000">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text bg-cyan">VNĐ</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Số điện thoại:</label>
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text bg-cyan"><i class="fas fa-phone"></i></span>
                                                    </div>
                                                    <input type="text" class="form-control" data-inputmask="'mask': ['999-999-9999 [x99999]', '+099 99 99 9999[9]-9999']" data-mask="" inputmode="text">
                                                </div>
                                                <!-- /.input group -->
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Số giấy tờ xe:</label>
                                                <input type="text" class="form-control" placeholder="02621234256">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Phí mất thẻ:</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control" placeholder="200000">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text bg-cyan">VNĐ</span>
                                                    </div>
                                                </div>
                                            </div>
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
                                                </div>
                                                <!-- /.card -->
                                            </div>
                                            <div class="col-xl-4">
                                                <div class="card card-cyan">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Hình ảnh khuôn mặt </h3>
                                                    </div>
                                                    <!-- /.card-header -->
                                                    <div class="card-body">
                                                        <div class="col-lx-12">
                                                            <div class="position-relative" style="min-height: 250px;">
                                                                <img src="<%= request.getContextPath() %>/assets/admin/dist/img/photo1.png" alt="Photo 1" class="img-fluid">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.card -->
                                            </div>
                                            <div class="col-xl-4">
                                                <div class="form-group">
                                                    <label>Mô tả:</label>
                                                    <textarea class="form-control" rows="3" placeholder="Khách hàng này cần giải quyết ..." style="height: 250px;"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-body -->
                                <div class="card-footer">
                                    <a class="btn btn-default" href="<%= request.getContextPath() %>/admin/lost-card?page=lost-card">Thoát</a>
                                    <button type="submit" class="btn btn-info float-right"><i class="fas fa-save"></i> Lưu</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--Nội dung-->
                    <div class="modal fade" id="modal-xl">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                                <div class="modal-header bg-cyan">
                                    <h4 class="modal-title">Tìm kiếm thông tin thẻ bị mất</h4>
                                    <button type="button" class="close btn btn-light" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="card card-outline card-cyan">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Bảng quản lý thông tin vào ra</h3>
                                                    </div>
                                                    <!-- /.card-header -->
                                                    <div class="card-body">
                                                        <table id="example1" class="table table-bordered table-striped">
                                                            <thead>
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>ID thẻ</th>
                                                                <th>Biển số</th>
                                                                <th>Thời gian vào</th>
                                                                <th>Thời gian ra</th>
                                                                <th>Loại xe</th>
                                                                <th>Loại vé</th>
                                                                <th>Phí DV</th>
                                                                <th style="width: 50px">Chức năng</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr>
                                                                <td>Trident</td>
                                                                <td>Internet
                                                                    Explorer 4.0
                                                                </td>
                                                                <td>Win 95+</td>
                                                                <td> 4</td>
                                                                <td>X</td>
                                                                <td>X</td>
                                                                <td>X</td>
                                                                <td>X</td>
                                                                <td>
                                                                    <div class="row">
                                                                        <div class="col-md-12">
                                                                            <button type="button" class="btn btn-info btn-block">
                                                                                <i class="fas fa-pen-square"></i>
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Trident</td>
                                                                <td>Internet
                                                                    Explorer 5.0
                                                                </td>
                                                                <td>Win 95+</td>
                                                                <td>5</td>
                                                                <td>C</td>
                                                                <td>X</td>
                                                                <td>X</td>
                                                                <td>X</td>
                                                                <td>
                                                                    <div class="row">
                                                                        <div class="col-md-12">
                                                                            <button type="button" class="btn btn-info btn-block">
                                                                                <i class="fas fa-pen-square"></i>
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                            <tfoot>
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>ID thẻ</th>
                                                                <th>Biển số</th>
                                                                <th>Thời gian vào</th>
                                                                <th>Thời gian ra</th>
                                                                <th>Loại xe</th>
                                                                <th>Loại vé</th>
                                                                <th>Phí DV</th>
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
                                </form>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                    <!-- /.modal -->
                </div>
            </section>
        </div>
    </div>
</div>
<jsp:include page="/views/library/_script.jsp" />
<script>
    $(function () {
        $('#reservationdatetime1').datetimepicker({
            format: 'MM/DD/YYYY HH:mm A',
            icons: { time: 'far fa-clock' }
        });
    });
</script>
</body>
</html>
