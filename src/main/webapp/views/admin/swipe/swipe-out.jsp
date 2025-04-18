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
    <link rel="icon" href="<c:url value='/assets/admin/dist/img/AdminLTELogo.png' />" type="image/png" />
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
                        <a href="<%= request.getContextPath() %>/admin/swipe" class="btn btn-outline-dark btn-block">
                            <i class="fas fa-arrow-alt-circle-left"></i> Thoát
                        </a>
                    </div>
                    <form id="swipeOutForm" action="${pageContext.request.contextPath}/admin/swipe/save" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-12">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <div id="cardMessage" class="alert alert-danger" style="display: none"></div>
                                            <c:if test="${not empty sessionScope.message}">
                                                <div id="successAlert" class="alert alert-success">
                                                        ${sessionScope.message}
                                                </div>

                                                <script>
                                                    setTimeout(function () {
                                                        $('#successAlert').fadeOut('slow');
                                                    }, 2000);
                                                </script>

                                                <c:remove var="message" scope="session"/>
                                            </c:if>
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
                                                        <input type="hidden" id="vehicleTypeId" name="vehicleTypeId" class="form-control"/>
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
                                                <div class="input-group date" id="reservationdatetime2" data-target-input="nearest">
                                                    <input type="text" name="checkInTime" class="form-control datetimepicker-input"
                                                           data-target="#reservationdatetime2" data-toggle="datetimepicker" />
                                                    <div class="input-group-append" data-target="#reservationdatetime2" data-toggle="datetimepicker">
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
                                    <div class="row justify-content-center" style="min-height: 250px;">
                                        <div class="mt-auto">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <div class="form-group">
                                                        <div class="custom-file">
                                                            <input type="file" class="custom-file-input" id="checkOutImagePathFile" name="image" accept="image/*" onchange="previewLicenseOutImage()">
                                                            <label class="custom-file-label" for="checkOutImagePathFile">
                                                                Chọn ảnh
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <button type="button" class="btn btn-info" onclick="scanLicensePlate()">
                                                        <i class="fas fa-qrcode"></i> Quét
                                                    </button>
                                                </div>
                                            </div>
                                            <input type="hidden" name="fileName" id="fileNameInput">
                                        </div>
                                    </div>
                                    <div class="row d-flex justify-content-center" style="min-height: 190px;">
                                        <div class="mt-auto">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fas fa-save"></i> Lưu
                                            </button>

                                        </div>
                                    </div>
                                    <div class="row d-flex justify-content-center">
                                        <div class="mt-1">
                                            <a href="<%= request.getContextPath() %>/admin/swipe/swipeout" class="btn btn-danger">
                                                <i class="fas fa-times"></i> Hủy
                                            </a>
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
                                                <div class="position-relative" id="checkOutImagePathPreview" style="min-height: 250px;">
                                                    <img id="checkOutImagePath" src="" alt="Check-Out Image Path" class="img-fluid">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.card-body -->
                                        <div class="card-footer">
                                            <div class="form-group">
                                                <label>Biển số</label>
                                                <input type="text" class="form-control" id="plateNumberInput">
                                            </div>
                                            <!-- Date and time -->
                                            <div class="form-group">
                                                <label>Thời gian ra:</label>
                                                <div class="input-group date" id="reservationdatetime1" data-target-input="nearest">
                                                    <input type="text" class="form-control datetimepicker-input" name="checkOutTime"
                                                           data-target="#reservationdatetime1" data-toggle="datetimepicker" />
                                                    <div class="input-group-append" data-target="#reservationdatetime1" data-toggle="datetimepicker">
                                                        <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /.form group -->
                                            <div class="form-group">
                                                <label>Hình ảnh ra</label>
                                                <input type="text" class="form-control" name="imagePathOut" id="imagePathOutInput" readonly>
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
    const licensePlateApi = "<%= application.getInitParameter("licensePlateApiUrl") %>";
    $(function () {
        bsCustomFileInput.init();
    });

    $(function () {
        $('#reservationdatetime1').datetimepicker({
            format: 'MM/DD/YYYY hh:mm A',
            icons: { time: 'far fa-clock' }
        });

        $('#reservationdatetime2').datetimepicker({
            format: 'MM/DD/YYYY hh:mm A',
            icons: { time: 'far fa-clock' }
        });

        bsCustomFileInput.init();
    });


    async function scanLicensePlate() {
        const fileInput = document.getElementById("checkOutImagePathFile");
        const file = fileInput.files[0];

        if (!file) {
            alert("Vui lòng chọn ảnh trước khi quét.");
            return;
        }

        const formData = new FormData();
        formData.append("image", file);

        try {
            const response = await fetch(licensePlateApi, {
                method: "POST",
                body: formData
            });

            const text = await response.text();
            const result = JSON.parse(text);

            if (!result.license_plate || result.license_plate.trim() === "") {
                alert("Không phát hiện được biển số xe trong ảnh.");
                return;
            }
            document.getElementById("plateNumberInput").value = result.license_plate;
            document.getElementById("fileNameInput").value = result.image_name;
            document.getElementById("imagePathOutInput").value = result.image_name;

            const imgPath = "<%=request.getContextPath()%>/assets/admin/dist/img/checkInLicensePhoto/" + result.image_name;
            document.getElementById("checkOutImagePath").src = imgPath;

        } catch (error) {
            console.error("Fetch error:", error);
            alert("Lỗi khi quét biển số: " + error.message);
        }
    }

    function previewLicenseOutImage() {
        const input = document.getElementById("checkOutImagePathFile");
        const file = input.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById("checkOutImagePath").src = e.target.result;
                document.getElementById("plateNumberInput").value = "";
                document.getElementById("fileNameInput").value = "";
                document.getElementById("imagePathOutInput").value = "";
            };
            reader.readAsDataURL(file);
        }
    }
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
                    $('input[name="vehicleTypeId"]').val(data.vehicleTypeId).prop('readonly', true);
                    $('input[name="licensePlate"]').val(data.licensePlate).prop('readonly', true);
                    $('input[name="checkInTime"]').val(data.checkInTime).prop('readonly', true);
                    $('input[name="checkInImagePath"]').val(data.checkInImagePath).prop('readonly', true);
                    $('input[name="price"]').val(data.price).prop('readonly', true);
                    document.getElementById("imageInPreview").src = "${pageContext.request.contextPath}/assets/admin/dist/img/checkInLicensePhoto/" + data.checkInImagePath;

                })
                .catch(err => {
                    console.error("Lỗi khi fetch dữ liệu thẻ:", err);
                });
        } else {
            // Reset lại nếu không có cardId được chọn
            $('input[name="id"]').val("").prop('readonly', false);
            $('input[name="type"]').val("").prop('readonly', false);
            $('input[name="vehicleTypeName"]').val("").prop('readonly', false);
            $('input[name="vehicleTypeId"]').val("").prop('readonly', false);
            $('input[name="licensePlate"]').val("").prop('readonly', false);
            $('input[name="checkInTime"]').val("").prop('readonly', false);
            $('input[name="checkInImagePath"]').val("").prop('readonly', false);
            $('input[name="price"]').val("").prop('readonly', false);
            document.getElementById("imageInPreview").src = "";

        }
    });

    document.getElementById("swipeOutForm").addEventListener("submit", function (e) {
        const licensePlateIn = document.getElementById("licensePlateInput").value.trim().toUpperCase();
        const licensePlateOut = document.getElementById("plateNumberInput").value.trim().toUpperCase();

        if (licensePlateIn !== licensePlateOut) {
            e.preventDefault(); // Ngăn form submit
            alert("BIỂN SỐ KHÁC NHAU! Vui lòng kiểm tra lại.");
        }
    });
</script>
</body>
</html>
