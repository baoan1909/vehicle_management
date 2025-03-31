<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/31/2025
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>forgot-password</title>
    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Bạn quên mật khẩu? Nhập lại Email của bạn để có thể dễ dàng tạo mật khẩu mới.</p>

            <form action="" method="post">
                <div class="input-group mb-3">
                    <input type="email" class="form-control" placeholder="Email">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-envelope"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <a href="<%= request.getContextPath() %>/forgot-password/recover-password?page=recover-password" class="btn btn-primary btn-block">Yêu cầu mật khẩu mới</a>
                    </div>
                    <!-- /.col -->
                </div>
            </form>

            <p class="mt-3 mb-1">
                <a href="<%= request.getContextPath() %>/login?page=login">Đăng nhập</a>
            </p>
            <p class="mb-0">
                <span>Bạn không có tài khoản?</span>
                <a href="<%= request.getContextPath() %>/register?page=register" class="text-center"> Đăng ký</a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->
<jsp:include page="/views/library/_script.jsp" />
</body>
</html>
