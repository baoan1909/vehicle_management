<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/27/2025
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Swipe in</title>
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
                        <h1 class="m-0">Quản lý xe vào </h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý vào ra</a></li>
                            <li class="breadcrumb-item active">Xe vào</li>
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
                    <form action="${pageContext.request.contextPath}/admin/swipe/save" method="post" enctype="multipart/form-data">
                        <div class="row ml-3 mr-3">
                            <div class="col-md-5">
                                <div class="card card-cyan">
                                    <div class="card-header">
                                        <h3 class="card-title">Hình ảnh xe vào</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <div class="col-lx-12">
                                            <div class="position-relative" id="checkInImagePathPreview" style="min-height: 400px;">
                                                <img id="checkInImagePath" src="<%=request.getContextPath()%>/assets/admin/api/License-Plate-Recognition-YOLOv7-and-CNN-Model/data/test/images/${fileName}" alt="Check-In Image Path" class="img-fluid">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">

                                        <div class="form-group">
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="checkInImagePathFile" name="image" accept="image/*" onchange="previewLicenseInImage()">
                                                <label class="custom-file-label" for="checkInImagePathFile">
                                                    Chọn ảnh
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <button type="button" class="btn btn-info" style="width: 100%" onclick="scanLicensePlate()">
                                                <i class="fas fa-qrcode"></i> Quét biển số từ ảnh
                                            </button>
                                        </div>

                                        <input type="hidden" name="fileName" id="fileNameInput">
                                        <input type="hidden" name="plateNumber" id="plateNumberInput">

                                    </div>
                                </div>
                                <!-- /.card -->
                            </div>
                            <div class="col-md-7">
                                <div class="card card-cyan card-outline shadow">
                                    <div class="card-header ">
                                        <h3 class="card-title">Thông tin xe vào</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <input type="hidden" name="id" value="${cardSwipe.cardSwipeId}" />
                                        <div class="form-group">
                                            <div id="cardMessage" class="alert alert-danger" style="display: none"></div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>ID thẻ xe</label>
                                                <select class="form-control select2" id="cardSelect" name="cardId" style="width: 100%;" required>
                                                    <option value="">-- Chọn thẻ --</option>
                                                    <c:forEach var="card" items="${cards}">
                                                        <option value="${card.cardId}"
                                                                <c:if test="${not empty cardSwipe.cardId && cardSwipe.cardId == card.cardId}">
                                                                    selected
                                                                </c:if>>
                                                            (ID:${card.cardId}) ${card.cardNumber}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12 mt-3">
                                            <div class="form-group">
                                                <label>Loại thẻ</label>
                                                <input type="text" id="type" name="type" class="form-control" placeholder="-- Loại thẻ --"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12 mt-3">
                                            <div class="form-group">
                                                <label>Loại xe</label>
                                                <input type="text" id="vehicleTypeName" name="vehicleTypeName" class="form-control" placeholder="-- Loại xe --"/>
                                                <input type="text" id="vehicleTypeId" hidden="hidden" name="vehicleTypeId" class="form-control" placeholder="-- Loại xe --"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12 mt-3">
                                            <div class="form-group">
                                                <label>Biển số</label>
                                                <input type="text" class="form-control" placeholder="80H-1826-127" name="licensePlate" id="licensePlateInput" required>
                                            </div>
                                        </div>
                                        <div class="col-md-12 mt-3">
                                            <div class="form-group">
                                                <label>Hình ảnh vào</label>
                                                <input type="text" class="form-control" name="checkInImagePath" id="checkInImagePathInput" required>
                                            </div>
                                        </div>
                                        <div class="col-xl-12 mt-3">
                                            <!-- Date and time -->
                                            <div class="form-group">
                                                <label>Thời gian vào:</label>
                                                <div class="input-group date" id="reservationdatetime" data-target-input="nearest">
                                                    <input type="text" class="form-control datetimepicker-input" name="checkInTime" data-target="#reservationdatetime"/>
                                                    <div class="input-group-append" data-target="#reservationdatetime" data-toggle="datetimepicker">
                                                        <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /.form group -->
                                            <div class="row">
                                                <div class="col-12">
                                                    <a href="#" class="btn btn-outline-warning">Hủy</a>
                                                    <button type="submit" class="btn btn-info float-right"><i class="fas fa-save"></i> Lưu</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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

    async function scanLicensePlate() {
        const fileInput = document.getElementById("checkInImagePathFile");
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

            document.getElementById("licensePlateInput").value = result.license_plate;
            document.getElementById("fileNameInput").value = result.image_name;
            document.getElementById("checkInImagePathInput").value = result.image_name;

            const imgPath = "<%=request.getContextPath()%>/assets/admin/api/License-Plate-Recognition-YOLOv7-and-CNN-Model/data/test/images/" + result.image_name;
            document.getElementById("checkInImagePath").src = imgPath;

        } catch (error) {
            console.error("Fetch error:", error);
            alert("Lỗi khi quét biển số: " + error.message);
        }
    }

    function previewLicenseInImage() {
        const input = document.getElementById("checkInImagePathFile");
        const file = input.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById("checkInImagePath").src = e.target.result;
                document.getElementById("licensePlateInput").value = "";
                document.getElementById("fileNameInput").value = "";
                document.getElementById("checkInImagePathInput").value = "";
            };
            reader.readAsDataURL(file);
        }
    }

    $('#cardSelect').on('change', function () {
        const selectedValue = $(this).val();

        console.log("Thẻ đã chọn:", selectedValue);

        if (selectedValue) {
            fetch("${pageContext.request.contextPath}/admin/swipe/getCardSwipeIn?cardId=" + selectedValue)
                .then(response => response.json())
                .then(data => {
                    console.log("Dữ liệu thẻ:", data);
                    // Hiển thị message nếu có
                    if (data.message) {
                        $('#cardMessage').text(data.message).show(); // hoặc alert(data.message);
                    } else {
                        $('#cardMessage').text("").hide();
                    }

                    $('input[name="type"]').val(data.type).prop('readonly', true);
                    $('input[name="vehicleTypeName"]').val(data.vehicleTypeName).prop('readonly', true);
                    $('input[name="vehicleTypeId"]').val(data.vehicleTypeId).prop('hidden', true);
                })
                .catch(err => {
                    console.error("Lỗi khi fetch dữ liệu thẻ:", err);
                });
        } else {
            // Reset lại nếu không có cardId được chọn
            $('input[name="type"]').val("");
            $('input[name="vehicleTypeName"]').val("");
            $('input[name="vehicleTypeId"]').val("")

        }
    });
</script>
</body>
</html>
