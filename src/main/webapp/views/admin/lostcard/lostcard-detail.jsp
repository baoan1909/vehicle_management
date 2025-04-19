<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lost card detail</title>
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
                                <h3 class="card-title">${lostCard.lostCardId == 0 ? "Thêm Thẻ mất mới" : "Chỉnh sửa thông tin Thẻ mất"}</h3>
                            </div>
                            <!-- /.card-header -->
                            <!-- form start -->
                            <form action="${pageContext.request.contextPath}/admin/lost/save" method="post">
                                <div class="card-body">
                                    <div class="form-group">
                                        <div id="cardMessage" class="alert alert-danger" style="display: none"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <input type="hidden" name="id" value="${lostCard.lostCardId}" />
                                            <div class="form-group">
                                                <label>ID thẻ:</label>
                                                <select class="form-control select2" id="cardSelect" name="cardId" style="width: 100%;" required>
                                                    <option value="">-- Chọn thẻ --</option>
                                                    <c:forEach var="card" items="${cards}">
                                                        <option value="${card.cardId}"
                                                                <c:if test="${not empty lostCard.cardId && lostCard.cardId == card.cardId}">
                                                                    selected
                                                                </c:if>>
                                                            (ID:${card.cardId}) ${card.cardNumber}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>ID khách hàng:</label>
                                                <select class="form-control select2" name="customerId" id="customerSelect" style="width: 100%;">
                                                    <option value="">-- Chọn khách hàng --</option>
                                                    <c:forEach var="customer" items="${customers}">
                                                        <option value="${customer.customerId}" ${customer.customerId == lostCard.customerId ? "selected" : ""}>
                                                            (ID:${customer.customerId}) ${customer.fullName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Thời gian thông báo:</label>
                                                <div class="input-group date" id="reservationdatetime2" data-target-input="nearest">
                                                    <input type="text" class="form-control datetimepicker-input" name="notificationTime"
                                                           data-target="#reservationdatetime2" value="${notificationTimeStr}" data-toggle="datetimepicker" />
                                                    <div class="input-group-append" data-target="#reservationdatetime2" data-toggle="datetimepicker">
                                                        <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Thời gian mất:</label>
                                                <div class="input-group date" id="reservationdatetime1" data-target-input="nearest">
                                                    <input type="text" class="form-control datetimepicker-input" name="timeOfLost"
                                                           data-target="#reservationdatetime1" value="${timeOfLostStr}" data-toggle="datetimepicker" />
                                                    <div class="input-group-append" data-target="#reservationdatetime1" data-toggle="datetimepicker">
                                                        <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3" >
                                            <div class="form-group">
                                                <label>Loại thẻ:</label>
                                                <input type="text" id="type"  name="type" class="form-control" value="${displayType}" placeholder="-- Loại thẻ --" readonly/>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Tên khách hàng:</label>
                                                <input type="text" id="visitorName"  name="visitorName" class="form-control" value="${displayName}" placeholder="Nguyễn Văn A" required/>
                                                <input type="text" id="customerName"  name="customerName" class="form-control" value="${displayName}" placeholder="Nguyễn Văn A" hidden="hidden"/>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>CCCD:</label>
                                                <input type="text" id="identifyCard" name="identifyCard" class="form-control" value="${displayIdentifyCard}" placeholder="8362632742" required  />
                                                <input type="text" id="customerIdentifyCard" name="customerIdentifyCard" class="form-control" value="${displayIdentifyCard}" placeholder="8362632742" hidden="hidden" />
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Phí gửi xe:</label>
                                                <div class="input-group">
                                                    <input type="number" name="ticketPrice" class="form-control" value="${lostCard.ticketPrice}" placeholder="0.0" required />
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
                                                <label>Số giấy tờ xe:</label>
                                                <input type="text" name="registrationLicense" class="form-control" value="${lostCard.registrationLicense}" placeholder="Reg2" required/>
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Số điện thoại:</label>
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text bg-cyan"><i class="fas fa-phone"></i></span>
                                                    </div>
                                                    <input type="text" id="visitorPhoneNum" name="visitorPhoneNum" class="form-control" value="${displayPhone}" data-inputmask="'mask': ['999-999-9999 [x99999]', '+099 99 99 9999[9]-9999']" data-mask="" inputmode="text" required  />
                                                    <input type="text" id="customerPhoneNum" name="customerPhoneNum" class="form-control" value="${displayPhone}" data-inputmask="'mask': ['999-999-9999 [x99999]', '+099 99 99 9999[9]-9999']" data-mask="" inputmode="text" hidden="hidden"  />
                                                </div>
                                                <!-- /.input group -->
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Phí mất thẻ:</label>
                                                <div class="input-group">
                                                    <input type="number" name="lostCardFee" class="form-control" value="${lostCard.lostCardFee == 0.0 ? 100000.0 : lostCard.lostCardFee}" required/>
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
                                                            <div class="position-relative" id="checkInLicensePhotoPreview" style="min-height: 250px;">
                                                                <c:choose>
                                                                    <c:when test="${not empty lostCard.checkInLicensePhoto}">
                                                                        <img id="checkInLicensePhoto" src="<%=request.getContextPath()%>/assets/admin/dist/img/checkInLicensePhoto/${lostCard.checkInLicensePhoto}" alt="Check-in License Photo" class="img-fluid">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img id="checkInLicensePhoto" src="" alt="Check-in License Photo" class="img-fluid">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.card-body -->
                                                    <div class="card-footer">
                                                        <div class="form-group">
                                                            <div class="custom-file">
                                                                <input type="file" class="custom-file-input" id="checkInLicensePhotoFile" name="checkInLicensePhoto" onchange="previewLicenseImage()">
                                                                <label class="custom-file-label" for="checkInLicensePhotoFile">
                                                                    ${not empty lostCard.checkInLicensePhoto ? lostCard.checkInLicensePhoto : 'Chọn ảnh'}
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
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
                                                            <div class="position-relative" id="customerPhotoPreview" style="min-height: 250px;">
                                                                <c:choose>
                                                                    <c:when test="${not empty lostCard.checkInCustomerPhoto}">
                                                                        <img id="checkInCustomerPhoto" src="<%=request.getContextPath()%>/assets/admin/dist/img/checkInCustomerPhoto/${lostCard.checkInCustomerPhoto}" alt="Check-in Customer Photo" class="img-fluid">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img id="checkInCustomerPhoto" src="" alt="Check-in Customer Photo" class="img-fluid">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card-footer">
                                                        <div class="form-group">
                                                            <div class="custom-file">
                                                                <input type="file" class="custom-file-input" id="checkInCustomerPhotoFile" name="checkInCustomerPhoto" onchange="previewCustomerImage()">
                                                                <label class="custom-file-label" for="checkInCustomerPhotoFile">
                                                                    ${not empty lostCard.checkInCustomerPhoto ? lostCard.checkInCustomerPhoto : 'Chọn ảnh'}
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.card -->
                                            </div>
                                            <div class="col-xl-4">
                                                <div class="form-group">
                                                    <label>Mô tả:</label>
                                                    <textarea class="form-control" rows="3" name="note" placeholder="Khách hàng này cần giải quyết ..." style="height: 250px;">${lostCard.note}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-body -->
                                <div class="card-footer">
                                    <a class="btn btn-default" href="<%= request.getContextPath() %>/admin/lost">Thoát</a>
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
                                                                <th>ID quét thẻ</th>
                                                                <th>ID thẻ</th>
                                                                <th>Biển số</th>
                                                                <th>Thời gian vào</th>
                                                                <th>Thời gian ra</th>
                                                                <th>Loại xe</th>
                                                                <th>Loại thẻ</th>
                                                                <th>Phí DV</th>
                                                                <th style="width: 50px">Chức năng</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach var="cardSwipe" items="${lstCardSwipe}" varStatus="loop">
                                                                <tr>
                                                                    <td>${loop.count}</td>
                                                                    <td>${cardSwipe.cardSwipeId}</td>
                                                                    <td>${cardSwipe.cardId}</td>
                                                                    <td>${cardSwipe.licensePlate}</td>
                                                                    <td>${cardSwipe.checkInTime}</td>
                                                                    <td>${cardSwipe.checkOutTime}</td>
                                                                    <td>${cardSwipe.vehicleTypeName}</td>
                                                                    <td>${cardSwipe.type}</td>
                                                                    <td>${cardSwipe.price}</td>
                                                                    <td>
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <button type="button" class="btn btn-info btn-block edit-card-btn"
                                                                                data-card-id="${cardSwipe.cardId}">
                                                                                    <i class="fas fa-pen-square"></i>
                                                                                </button>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                            <tfoot>
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>ID quét thẻ</th>
                                                                <th>ID thẻ</th>
                                                                <th>Biển số</th>
                                                                <th>Thời gian vào</th>
                                                                <th>Thời gian ra</th>
                                                                <th>Loại xe</th>
                                                                <th>Loại thẻ</th>
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
            format: 'MM/DD/YYYY hh:mm A',
            icons: { time: 'far fa-clock' }
        });

        $('#reservationdatetime2').datetimepicker({
            format: 'MM/DD/YYYY hh:mm A',
            icons: { time: 'far fa-clock' }
        });

        bsCustomFileInput.init();
    });

    $('#cardSelect').on('change', function () {
        const selectedValue = $(this).val();

        console.log("Thẻ đã chọn:", selectedValue);

        if (selectedValue) {
            fetch("${pageContext.request.contextPath}/admin/lost/getcustomer?cardId=" + selectedValue)
                .then(response => response.json())
                .then(data => {
                    console.log("Dữ liệu thẻ:", data);
                    // Hiển thị message nếu có
                    if (data.message) {
                        $('#cardMessage').text(data.message).show(); // hoặc alert(data.message);
                    } else {
                        $('#cardMessage').text("").hide();
                    }

                    // Gán selected cho option customerId tương ứng nếu có
                    if (data.customerId) {
                        $('#customerSelect').val(data.customerId).trigger('change');
                        $('input[name="type"]').val(data.type || "");
                        $('input[name="ticketPrice"]').val("0.0").prop('readonly', true);
                        $('input[name="customerName"]').val(data.fullName || "").prop('hidden', false).prop('readonly', true);
                        $('input[name="customerPhoneNum"]').val(data.phoneNumber || "").prop('hidden', false).prop('readonly', true);
                        $('input[name="customerIdentifyCard"]').val(data.identifyCard || "").prop('hidden', false).prop('readonly', true);

                        $('input[name="visitorName"]').val("").prop('hidden', true).prop('required', false);
                        $('input[name="visitorPhoneNum"]').val("").prop('hidden', true).prop('required', false);
                        $('input[name="identifyCard"]').val("").prop('hidden', true).prop('required', false);
                    }else {
                        $('#customerSelect').val("").trigger('change');
                        $('input[name="type"]').val(data.type || "");
                        $('input[name="ticketPrice"]').val(data.price || "").prop('readonly', true);

                        $('input[name="customerName"]').val("").prop('hidden', true);
                        $('input[name="customerPhoneNum"]').val("").prop('hidden', true);
                        $('input[name="customerIdentifyCard"]').val("").prop('hidden', true);

                        $('input[name="visitorName"]').val("").prop('hidden', false).prop('required', true);
                        $('input[name="visitorPhoneNum"]').val("").prop('hidden', false).prop('required', true);
                        $('input[name="identifyCard"]').val("").prop('hidden', false).prop('required', true);
                    }

                    // Cập nhật ảnh check-in license
                    if (data.checkInImagePath) {
                        var imageSrc = "${pageContext.request.contextPath}/assets/admin/dist/img/checkInLicensePhoto/" + data.checkInImagePath;
                        $('#checkInLicensePhoto').attr("src", imageSrc);
                        $('#checkInLicensePhotoPreview').show();
                        $('#checkInLicensePhotoFile').next('label').text(data.checkInImagePath);
                    } else {
                        $('#checkInLicensePhoto').attr("src", "");
                        $('#checkInLicensePhotoPreview').show();
                        $('#checkInLicensePhotoFile').next('label').text('Chọn ảnh');
                    }
                })
                .catch(err => {
                    console.error("Lỗi khi fetch dữ liệu thẻ:", err);
                });
        } else {
            // Reset lại nếu không có cardId được chọn
            $('input[name="type"]').val("");
            $('input[name="ticketPrice"]').val("").prop('readonly', false);
            $('input[name="visitorName"]').val("").prop('readonly', false);
            $('input[name="visitorPhoneNum"]').val("").prop('readonly', false);
            $('input[name="identifyCard"]').val("").prop('readonly', false);
            $('#checkInLicensePhoto').attr("src", "");
            $('#checkInLicensePhotoPreview').show();
            $('#checkInLicensePhotoFile').next('label').text('Chọn ảnh');

            // Reset selected customer
            $('#customerSelect').val("").trigger('change');
        }
    });

    //Chọn cardId từ form tìm kiếm
    $(document).ready(function () {
        // Khi nhấn nút sửa
        $('.edit-card-btn').on('click', function () {
            const cardId = $(this).data('card-id'); // Lấy cardId từ button đã chọn

            $('#modal-xl').modal('hide');

            // Set giá trị cho select
            $('#cardSelect').val(cardId).trigger('change');

            // Nếu bạn muốn cuộn đến thẻ select
            $('html, body').animate({
                scrollTop: $('#cardSelect').offset().top - 100
            }, 500);
        });
    });

    // Hàm preview ảnh khi chọn ảnh từ input file
    function previewLicenseImage() {
        var file = document.getElementById('checkInLicensePhotoFile').files[0];
        var reader = new FileReader();

        reader.onload = function(e) {
            var previewContainer = document.getElementById('checkInLicensePhotoPreview');
            var img = document.getElementById('checkInLicensePhoto');

            if (!img) {
                img = document.createElement('img');
                img.id = 'checkInLicensePhoto';
                img.classList.add('img-fluid');
                previewContainer.innerHTML = ''; // clear old content (if any <p>)
                previewContainer.appendChild(img);
            }
            img.src = e.target.result;
            $('#checkInLicensePhotoPreview').show(); // Hiển thị ảnh khi người dùng chọn ảnh
        };

        if (file) {
            reader.readAsDataURL(file);
        }
    }


    function previewCustomerImage() {
        var file = document.getElementById('checkInCustomerPhotoFile').files[0];
        var reader = new FileReader();

        reader.onload = function(e) {
            var previewContainer = document.getElementById('customerPhotoPreview');
            var img = document.getElementById('checkInCustomerPhoto');

            if (!img) {
                img = document.createElement('img');
                img.id = 'checkInCustomerPhoto';
                img.classList.add('img-fluid');
                previewContainer.innerHTML = ''; // clear old content (if any <p>)
                previewContainer.appendChild(img);
            }
            img.src = e.target.result;
        };

        if (file) {
            reader.readAsDataURL(file);
        }
    }
</script>
</body>
</html>
