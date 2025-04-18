<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Customer Detail</title>
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
                        <h1 class="m-0">Thông tin khách hàng</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý khách hàng</a></li>
                            <li class="breadcrumb-item active">Thông tin khách hàng</li>
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
                                    <h3 class="card-title">${customerDTO.customerId == 0 ? "Thêm thông tin Khách hàng" : "Chỉnh sửa thông tin Khách hàng"}</h3>
                                </div>
                                <!-- /.card-header -->
                                <!-- form start -->
                                <form action="${pageContext.request.contextPath}/admin/customer/save" method="post">
                                    <div class="card-body">
                                        <input type="hidden" name="id" value="${customerDTO.customerRegisterTicketId}" />
                                        <input type="hidden" name="customerId" value="${customerDTO.customerId}" />
                                        <div class="form-group">
                                            <div id="cardMessage" class="alert alert-danger" style="display: none"></div>
                                            <div id="feeMessage" class="alert alert-danger" style="display: none"></div>
                                            <c:if test="${not empty error}">
                                                <div class="alert alert-danger">${error}</div>
                                            </c:if>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Tên khách hàng:</label>
                                                    <input name="fullName" type="text" class="form-control" placeholder="Nguyễn Văn A" value="${customerDTO.customerName}">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Số điện thoại:</label>
                                                    <div class="input-group">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text bg-cyan"><i class="fas fa-phone"></i></span>
                                                        </div>
                                                        <input name="phoneNumber" type="text" class="form-control" data-inputmask="'mask': ['999-999-9999 [x99999]', '+099 99 99 9999[9]-9999']" data-mask="" inputmode="text" value="${customerDTO.customerPhoneNumber}">
                                                    </div>
                                                    <!-- /.input group -->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Ngày sinh:</label>
                                                    <div class="input-group date" id="dateOfBirth" data-target-input="nearest">
                                                        <input name="dateOfBirth" type="text" class="form-control datetimepicker-input" data-target="#dateOfBirth"
                                                               placeholder="01/01/2025" value="${dateOfBirth}"/>
                                                        <div class="input-group-append" data-target="#dateOfBirth" data-toggle="datetimepicker">
                                                            <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>CCCD:</label>
                                                    <input name="identifyCard" type="text" class="form-control" placeholder="075123456789..." value="${customerDTO.identifyCard}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Giới tính:</label>
                                                    <select name="gender" class="form-control select2">
                                                        <option value="Nam" ${customerDTO.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                                        <option value="Nữ" ${customerDTO.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Địa chỉ:</label>
                                                    <input name="address" type="text" class="form-control" placeholder="TP. Hồ Ch Minh..." value="${customerDTO.address}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <input type="hidden" name="feeCustomerId" value="${customerDTO.feeCustomerId}" />
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Mã số thẻ:</label>
                                                    <select class="form-control select2" id="cardSelect" name="cardId" style="width: 100%;" required>
                                                        <option value="">-- Chọn thẻ --</option>
                                                        <c:forEach var="card" items="${cards}">
                                                            <option value="${card.cardId}"
                                                                    <c:if test="${not empty customerDTO.cardId && customerDTO.cardId == card.cardId}">
                                                                        selected
                                                                    </c:if>>
                                                                (ID:${card.cardId})- ${card.cardNumber}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <div class="form-group">
                                                        <label>Loại xe</label>
                                                        <input type="text" id="vehicleTypeName" name="vehicleTypeName" class="form-control" placeholder="-- Loại xe --" value="${displayVehicleName}" readonly/>
                                                        <input id="vehicleTypeId" type="hidden" name="vehicleTypeId" class="form-control" placeholder="-- Loại xe --" value="${customerDTO.vehicleTypeId}"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Loại vé:</label>
                                                    <select class="form-control select2" id="ticketTypeSelect" style="width: 100%;" name="ticketTypeId">
                                                        <option value="">-- Loại vé --</option>
                                                        <c:forEach var="ticketType" items="${ticketTypes}">
                                                            <option value="${ticketType.ticketTypeId}" ${customerDTO.ticketTypeName==ticketType.ticketTypeName ? "selected" : ""}>
                                                                    ${ticketType.ticketTypeName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Giá vé:</label>
                                                    <input name="price" type="text" class="form-control" placeholder="Giá vé..." value="${customerDTO.price}" readonly>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>Biển số xe:</label>
                                                    <input name="licensePlate" type="text" class="form-control" placeholder="59A-03979" value="${customerDTO.licensePlate}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Ngày đăng ký:</label>
                                                    <div class="input-group date" id="EffectiveDate" data-target-input="nearest">
                                                       <input name="effectiveDate" type="text" class="form-control datetimepicker-input" data-target="#EffectiveDate"
                                                               placeholder="01/01/2025" value="${effectiveDate}"/>
                                                        <div class="input-group-append" data-target="#EffectiveDate" data-toggle="datetimepicker">
                                                            <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Ngày hết hạn:</label>
                                                    <div class="input-group date" id="ExpirationDate" data-target-input="nearest">
                                                            <input name="expirationDate" type="text" class="form-control datetimepicker-input" data-target="#ExpirationDate"
                                                                   placeholder="01/01/2025" value="${expirationDate}"/>
                                                        <div class="input-group-append" data-target="#ExpirationDate" data-toggle="datetimepicker">
                                                            <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <a class="btn btn-default" href="<%= request.getContextPath() %>/admin/customer?page=customer">Thoát</a>
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
<c:if test="${not empty insertedCustomerId}">
    <script>
        console.log("Customer ID vừa insert là: ${insertedCustomerId}");
    </script>
</c:if>
<script>
    $(function () {
        $('#dateOfBirth, #EffectiveDate, #ExpirationDate').datetimepicker({
            format: 'MM/DD/YYYY',
        });
    });

    function fetchFeeCustomer() {
        const vehicleTypeId = $('input[name="vehicleTypeId"]').val();
        const ticketTypeId = $('select[name="ticketTypeId"]').val();

        console.log("vehicleTypeId:", vehicleTypeId);
        console.log("ticketTypeId:", ticketTypeId);

        if (vehicleTypeId && ticketTypeId) {
            fetch(`${pageContext.request.contextPath}/admin/customer/getFeeCustomer?ticketTypeId=` + ticketTypeId + `&vehicleTypeId=` + vehicleTypeId)
                .then(response => response.json())
                .then(data => {
                    if (data.message) {
                        $('#feeMessage').text(data.message).show();
                    } else {
                        $('#feeMessage').hide();
                    }

                    $('input[name="price"]').val(data.price).prop('readonly', true);
                    $('input[name="feeCustomerId"]').val(data.feeCustomerId);
                })
                .catch(err => {
                    console.error("Lỗi khi lấy feeCustomer:", err);
                });
        }
    }

    $('#cardSelect').on('change', function () {
        const selectedValue = $(this).val();
        if (selectedValue) {
            fetch(`${pageContext.request.contextPath}/admin/customer/getCard?cardId=` + selectedValue)
                .then(response => response.json())
                .then(data => {
                    if (data.message) {
                        $('#cardMessage').text(data.message).show();
                    } else {
                        $('#cardMessage').text("").hide();
                    }

                    $('input[name="vehicleTypeName"]').val(data.vehicleTypeName).prop('readonly', true);
                    $('input[name="vehicleTypeId"]').val(data.vehicleTypeId);

                    fetchFeeCustomer();
                });
        } else {
            $('input[name="vehicleTypeName"]').val("");
            $('input[name="vehicleTypeId"]').val("");
        }
    });

    $('#ticketTypeSelect').on('change', function () {
        fetchFeeCustomer();
    });

</script>
</body>
</html>