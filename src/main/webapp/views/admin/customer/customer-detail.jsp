<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Customer Detail</title>
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
                                    <h3 class="card-title">Thêm/Sửa thông tin khách hàng</h3>
                                </div>
                                <!-- /.card-header -->
                                <!-- form start -->
                                <form action="${pageContext.request.contextPath}/admin/customer/save" method="post">
                                    <div class="card-body">
                                        <c:if test="${not empty customerDTO.customerRegisterTicketId}">
                                            <input type="hidden" name="id" value="${customerDTO.customerRegisterTicketId}" />
                                        </c:if>
                                        <c:if test="${not empty customerDTO.customerId}">
                                            <input type="hidden" name="customerId" value="${customerDTO.customerId}" />
                                        </c:if>

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
                                                    <fmt:formatDate value="${formattedDateOfBirth}" pattern="MM/dd/yyyy" var="formattedDateOfBirth"/>
                                                    <div class="input-group date" id="dateOfBirth" data-target-input="nearest">
                                                        <input name="dateOfBirth" type="text" class="form-control datetimepicker-input" data-target="#dateOfBirth"
                                                               placeholder="01/01/2025" value="${formattedDateOfBirth}"/>
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
                                                    <input name="gender" type="text" class="form-control" placeholder="Nam..." value="${customerDTO.gender}">
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
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Email:</label>
                                                    <div class="input-group">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text bg-cyan"><i class="fas fa-envelope"></i></span>
                                                        </div>
                                                        <input name="email" type="email" class="form-control" placeholder="Email" value="${customerDTO.customerEmail}">
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${not empty customerDTO.feeCustomerId}">
                                                <input type="hidden" name="feeCustomerId" value="${customerDTO.feeCustomerId}" />
                                            </c:if>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Loại xe:</label>
                                                    <select class="form-control select2" style="width: 100%;" name="vehicleTypeId">
                                                        <c:forEach var="vehicleType" items="${vehicleTypes}">
                                                            <option value="${vehicleType.vehicleTypeId}" ${customerDTO.vehicleTypeId==vehicleType.vehicleTypeId ? "selected" : ""}>
                                                                    ${vehicleType.vehicleTypeName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Loại vé:</label>
                                                    <select class="form-control select2" style="width: 100%;" name="ticketTypeId">
                                                        <c:forEach var="ticketType" items="${ticketTypes}">
                                                            <option value="${ticketType.ticketTypeId}" ${customerDTO.ticketTypeName==ticketType.ticketTypeName ? "selected" : ""}>
                                                                    ${ticketType.ticketTypeName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Biển số xe:</label>
                                                    <input name="licensePlate" type="text" class="form-control" placeholder="59A-03979" value="${customerDTO.licensePlate}">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Mã số thẻ:</label>
                                                    <input name="cardNumber" type="text" class="form-control" placeholder="Mã số thẻ..." value="${customerDTO.cardNumber}">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Giá vé:</label>
                                                    <input name="price" type="text" class="form-control" placeholder="Giá vé..." value="${customerDTO.price}">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Ngày đăng ký:</label>
                                                    <div class="input-group date" id="reservationdateEffectiveDate" data-target-input="nearest">
                                                        <fmt:formatDate value="${formattedEffectiveDate}" pattern="MM/dd/yyyy" var="formattedEffectiveDate"/>
                                                       <input name="effectiveDate" type="text" class="form-control datetimepicker-input" data-target="#reservationdateEffectiveDate"
                                                               placeholder="01/01/2025" value="${formattedEffectiveDate}"/>
                                                        <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
                                                            <div class="input-group-text bg-cyan"><i class="fa fa-calendar"></i></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Ngày hết hạn:</label>
                                                    <div class="input-group date" id="reservationdateExpirationDate" data-target-input="nearest">
                                                         <fmt:formatDate value="${formattedExpirationDate}" pattern="MM/dd/yyyy" var="formattedExpirationDate"/>
                                                            <input name="expirationDate" type="text" class="form-control datetimepicker-input" data-target="#reservationdateExpirationDate"
                                                                   placeholder="01/01/2025" value="${formattedExpirationDate}"/>

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
<script>
    $(function () {
        $('#reservationdate1').datetimepicker({
            format: 'MM/DD/YYYY',
        });
    });
</script>
</body>
</html>