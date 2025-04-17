<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Account Detail</title>
    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
    <jsp:include page="/views/layout/navbar.jsp" />
    <jsp:include page="/views/layout/sidebar.jsp" />

    <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Thông tin tài khoản</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý tài khoản</a></li>
                            <li class="breadcrumb-item active">Thông tin tài khoản</li>
                        </ol>
                    </div>
                </div>
            </div>

            <section class="content">
                <div class="container-fluid">
                    <div class="row d-flex justify-content-center mt-4">
                        <div class="col-md-10">
                            <div class="card card-cyan">
                                <div class="card-header">
                                    <h3 class="card-title">
                                        ${account.accountId == 0 ? "Thêm Tài khoản mới" : "Chỉnh sửa thông tin Tài khoản"}
                                    </h3>
                                </div>

                                <form action="${pageContext.request.contextPath}/admin/account/save" method="post">
                                    <div class="card-body">
                                        <!-- Hiển thị lỗi nếu có -->
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger">${error}</div>
                                        </c:if>

                                        <input type="hidden" name="id" value="${account.accountId}" />

                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Tên tài khoản:</label>
                                                <input type="text" class="form-control" name="userName"
                                                       value="${account.userName}" placeholder="Nhập tên tài khoản">
                                            </div>
                                            <div class="col-md-6">
                                                <label>Tên khách hàng:</label>
                                                <select class="form-control select2" name="customerId" required>
                                                    <option value="">-- Tên khách hàng --</option>
                                                    <c:forEach var="customer" items="${customers}">
                                                        <option value="${customer.customerId}"
                                                                <c:if test="${customer.customerId == account.customerId}">selected</c:if>>
                                                                (${customer.customerId})- ${customer.fullName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-4">
                                                <label>Vai trò:</label>
                                                <select class="form-control select2" name="roleId" required>
                                                    <c:forEach var="role" items="${roles}">
                                                        <option value="${role.roleId}"
                                                                <c:if test="${role.roleId == account.roleId}">selected</c:if>>
                                                                ${role.roleName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-8">
                                                <label>Email:</label>
                                                <input type="email" class="form-control" name="email"
                                                       value="${account.email}"
                                                       placeholder="abc@gmail.com">
                                            </div>
                                        </div>

                                        <c:choose>
                                            <c:when test="${account.accountId == 0}">
                                                <!-- Tạo tài khoản mới -->
                                                <div class="row mt-3">
                                                    <div class="col-md-6 position-relative">
                                                        <label>Mật khẩu:</label>
                                                        <input class="form-control" name="hashPassword" id="password" type="password">
                                                    </div>
                                                    <div class="col-md-6 position-relative">
                                                        <label>Nhập lại mật khẩu:</label>
                                                        <input class="form-control" name="retypePassword" id="retypePassword" type="password">
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- Đổi mật khẩu khi chỉnh sửa -->
                                                <div class="row mt-3">
                                                    <div class="col-md-12">
                                                        <button type="button" class="btn btn-info mr-2" onclick="showPasswordFields()">
                                                            <i class="fas fa-key"></i> Đổi mật khẩu
                                                        </button>
                                                        <button type="button" class="btn btn-secondary" id="cancelChangePassword" style="display: none;" onclick="hidePasswordFields()">
                                                            <i class="fas fa-times"></i> Hủy
                                                        </button>
                                                    </div>
                                                </div>
                                                <div id="passwordFields" style="display: none;" class="mt-3">
                                                    <div class="row">
                                                        <div class="col-md-6 position-relative">
                                                            <label>Mật khẩu mới:</label>
                                                            <input class="form-control" name="hashPassword" id="passwordEdit" type="password">
                                                        </div>
                                                        <div class="col-md-6 position-relative">
                                                            <label>Nhập lại mật khẩu:</label>
                                                            <input class="form-control" name="retypePassword" id="retypePasswordEdit" type="password">
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="row mt-3 ">
                                            <div class="col-md-12">
                                                <label>Trạng thái:</label>
                                                <input class="ml-3" type="checkbox" name="status" ${account.status == 1 ? "checked" : ""}/> Hoạt động<br/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/account">Thoát</a>
                                        <button type="submit" class="btn btn-info float-right">
                                            <i class="fas fa-save"></i> Lưu
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>

<jsp:include page="/views/library/_script.jsp" />
<script>

    function showPasswordFields() {
        const pwFields = document.getElementById("passwordFields");
        pwFields.style.display = "flex";
        pwFields.style.flexDirection = "column";

        document.getElementById("cancelChangePassword").style.display = "inline-block";
    }

    function hidePasswordFields() {
        const pwFields = document.getElementById("passwordFields");
        pwFields.style.display = "none";

        // Xóa giá trị đã nhập (nếu có)
        document.getElementById("passwordEdit").value = "";
        document.getElementById("retypePasswordEdit").value = "";

        document.getElementById("cancelChangePassword").style.display = "none";
    }

</script>
</body>
</html>
