<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/31/2025
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recover Password</title>
    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Bạn chỉ còn một bước nữa là có được mật khẩu mới, hãy khôi phục mật khẩu ngay.</p>

            <form action="" method="post">
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="Mật khẩu">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="Nhập lại mật khẩu">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <a href="<%= request.getContextPath() %>/login?page=login" class="btn btn-primary btn-block">Thay đổi mật khẩu</a>
                    </div>
                    <!-- /.col -->
                </div>
            </form>

            <p class="mt-3 mb-1">
                <a href="<%= request.getContextPath() %>/login?page=login">Đăng nhập</a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->
<jsp:include page="/views/library/_script.jsp" />
</body>
</html>
