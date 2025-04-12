<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <div class="form-group col-md-1 ml-auto mr-3">
                        <a href="<%= request.getContextPath() %>/admin/card-swipe?page=card-swipe" class="btn btn-outline-dark btn-block">
                            <i class="fas fa-arrow-alt-circle-left"></i> Thoát
                        </a>
                    </div>
                    <form action="" method="post">
                        <div class="row">
                            <div class="col-12">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <div id="cardMessage" class="alert alert-danger" style="display: none"></div>
                                        </div>
                                        <div class="col-12 card card-cyan card-outline">
                                            <div class="row ml-3">
                                                <div class="col-md-2 mt-3">
                                                    <div class="form-group">
                                                        <label>ID Quẹt thẻ</label>
                                                        <input type="text" name="id" class="form-control" placeholder="-- ID Quẹt thẻ --" />
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mt-3">
                                                    <div class="form-group">
                                                        <label>ID thẻ xe</label>
                                                        <select class="form-control select2" id="cardSelect" name="cardId" style="width: 100%;" required>
                                                            <option value="">-- Chọn thẻ --</option>
                                                            <c:forEach var="card" items="${cards}">
                                                                <option value="${card.cardId}">
                                                                    (ID:${card.cardId}) ${card.cardNumber}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mt-3">
                                                    <div class="form-group">
                                                        <label>Loại thẻ</label>
                                                        <input type="text" id="type" name="type" class="form-control" placeholder="-- Loại thẻ --"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mt-3">
                                                    <div class="form-group">
                                                        <label>Loại xe:</label>
                                                        <input type="text" id="vehicleTypeName" name="vehicleTypeName" class="form-control" placeholder="-- Loại xe --"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row ml-3">
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label>Thành tiển</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="price" placeholder="0.0">
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
                                                    <img id="imageInPreview" src="" alt="Check-In Image Path" class="img-fluid">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.card-body -->
                                        <!-- /.card-body -->
                                        <div class="card-footer">
                                            <div class="form-group">
                                                <label>Biển số</label>
                                                <input type="text" class="form-control" placeholder="80H-1826-127" name="licensePlate" id="licensePlateInput" required>
                                            </div>
                                            <!-- Date and time -->
                                            <div class="form-group">
                                                <label>Thời gian vào:</label>
                                                <div class="input-group date">
                                                    <input type="text" name="checkInTime" class="form-control datetimepicker-input" />
                                                    <div class="input-group-append">
                                                        <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /.form group -->
                                            <div class="form-group">
                                                <label>Hình ảnh vào</label>
                                                <input type="text" class="form-control" name="checkInImagePath" id="checkInImagePathInput" required>
                                            </div>
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
                                            <div class="form-group">
                                                <label>Biển số</label>
                                                <input type="text" class="form-control" placeholder="80H-1826-127" required>
                                            </div>
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
                                            <div class="form-group">
                                                <label>Hình ảnh ra</label>
                                                <input type="text" class="form-control" required>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card -->
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</div>
<jsp:include page="/views/library/_script.jsp" />
<script>
    $('#cardSelect').on('change', function () {
        const selectedValue = $(this).val();

        console.log("Thẻ đã chọn:", selectedValue);

        if (selectedValue) {
            fetch("${pageContext.request.contextPath}/admin/swipe/getCardSwipeOut?cardId=" + selectedValue)
                .then(response => response.json())
                .then(data => {
                    console.log("Dữ liệu thẻ:", data);
                    // Hiển thị message nếu có
                    if (data.message) {
                        $('#cardMessage').text(data.message).show(); // hoặc alert(data.message);
                    } else {
                        $('#cardMessage').text("").hide();
                    }

                    $('input[name="id"]').val(data.cardSwipeId).prop('readonly', true);
                    $('input[name="type"]').val(data.type).prop('readonly', true);
                    $('input[name="vehicleTypeName"]').val(data.vehicleTypeName).prop('readonly', true);
                    $('input[name="licensePlate"]').val(data.licensePlate).prop('readonly', true);
                    $('input[name="checkInTime"]').val(data.checkInTime).prop('readonly', true);
                    $('input[name="checkInImagePath"]').val(data.checkInImagePath).prop('readonly', true);
                    $('input[name="price"]').val(data.price).prop('readonly', true);
                    document.getElementById("imageInPreview").src = "${pageContext.request.contextPath}/assets/admin/api/License-Plate-Recognition-YOLOv7-and-CNN-Model/data/test/images/" + data.checkInImagePath;

                })
                .catch(err => {
                    console.error("Lỗi khi fetch dữ liệu thẻ:", err);
                });
        } else {
            // Reset lại nếu không có cardId được chọn
            $('input[name="id"]').val("");
            $('input[name="type"]').val("");
            $('input[name="vehicleTypeName"]').val("");
            $('input[name="licensePlate"]').val("");
            $('input[name="checkInTime"]').val("");
            $('input[name="checkInImagePath"]').val("");
            $('input[name="price"]').val("");
            document.getElementById("imageInPreview").src = "";

        }
    });
</script>
</body>
</html>
