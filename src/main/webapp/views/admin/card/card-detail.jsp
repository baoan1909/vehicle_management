<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                <form action="${pageContext.request.contextPath}/admin/card/save" method="post">
                                    <div class="card-body">
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger">${error}</div>
                                        </c:if>
                                        <!-- ID ẩn -->
                                        <input type="hidden" name="id" value="${card.cardId}" />
                                        <!-- Số thẻ -->
                                        <div class="form-group">
                                            <label>Số thẻ:</label>
                                            <input type="text"
                                                   name="cardNumber"
                                                   class="form-control ${not empty error ? 'is-invalid' : ''}"
                                                   value="${empty error ? card.cardNumber : ''}"
                                                   placeholder="C002" required/>
                                            <c:if test="${not empty error}">
                                                <div class="invalid-feedback">${error}</div>
                                            </c:if>
                                        </div>
                                        <!-- Loại thẻ -->
                                        <div class="form-group">
                                            <label>Loại thẻ:</label>
                                            <select name="type" class="form-control select2" style="width: 100%;" required>
                                                <option value="Vãng lai" ${card.type == 'Vãng lai' ? "selected" : ""}>Vãng lai</option>
                                                <option value="Đăng ký" ${card.type == 'Đăng ký' ? "selected" : ""}>Đăng ký</option>
                                            </select>
                                        </div>
                                        <!-- Loại xe -->
                                        <div class="form-group">
                                            <label>Loại xe:</label>
                                            <select class="form-control select2" name="vehicleTypeId" style="width: 100%;" required>
                                                <c:forEach var="vehicle" items="${vehicleTypes}">
                                                    <option value="${vehicle.vehicleTypeId}" ${vehicle.vehicleTypeId == card.vehicleTypeId || vehicle.vehicleTypeId == param.vehicleTypeId ? 'selected' : ''}>
                                                            ${vehicle.vehicleTypeName}
                                                    </option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                        <!-- Checkbox -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-check">
                                                    <input type="checkbox" class="form-check-input" name="isCreated" id="isCreated" ${card.isCreated == 1 ? "checked" : ""}>
                                                    <label class="form-check-label" for="isCreated">Đã tạo thẻ vật lý</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-check">
                                                    <input type="checkbox" class="form-check-input" name="isUsed" id="isUsed" ${card.isUsed == 1 ? "checked" : ""} ${card.isCreated == 0 ? "disabled" : ""} >
                                                    <label class="form-check-label" for="isUsed">Đã được sử dụng</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/card">Thoát</a>
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
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const isCreated = document.getElementById('isCreated');
        const isUsed = document.getElementById('isUsed');

        // Kiểm tra trạng thái checkbox isCreated
        isCreated.addEventListener('change', function() {
            // Nếu isCreated không được checked thì disable isUsed
            if (!isCreated.checked) {
                isUsed.checked = false;  // Bỏ chọn isUsed nếu isCreated không được checked
                isUsed.disabled = true;  // Vô hiệu hóa isUsed
            } else {
                isUsed.disabled = false; // Kích hoạt lại isUsed khi isCreated được checked
            }
        });

        // Kiểm tra trạng thái ngay khi trang được tải
        if (!isCreated.checked) {
            isUsed.disabled = true;
        }
    });
</script>
</body>
</html>
