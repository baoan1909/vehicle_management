<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/31/2025
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">
        <a href="#"><b>WELCOME</b></a>
    </div>

    <div class="card">
        <div class="card-body register-card-body">
            <p class="login-box-msg">Đăng ký tài khoản mới</p>

            <form action="" method="post">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Họ và tên">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="email" class="form-control" placeholder="Email">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-envelope"></span>
                        </div>
                    </div>
                </div>
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
                    <div class="col-8">
                        <div class="icheck-primary">
                            <input type="checkbox" id="agreeTerms" name="terms" value="agree">
                            <label for="agreeTerms">
                                 Tôi đồng ý với<a href="#"> điều khoản</a>
                            </label>
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <div class="row mt-4">
                    <div class="col-12">
                        <a href="<%= request.getContextPath() %>/admin/dashboard?page=dashboard" class="btn btn-primary btn-block">Đăng ký</a>
                    </div>
                    <!-- /.col -->
                </div>
            </form>

<%--            <div class="social-auth-links text-center">--%>
<%--                <p>- OR -</p>--%>
<%--                <a href="#" class="btn btn-block btn-primary">--%>
<%--                    <i class="fab fa-facebook mr-2"></i>--%>
<%--                    Sign up using Facebook--%>
<%--                </a>--%>
<%--                <a href="#" class="btn btn-block btn-danger">--%>
<%--                    <i class="fab fa-google-plus mr-2"></i>--%>
<%--                    Sign up using Google+--%>
<%--                </a>--%>
<%--            </div>--%>
            <p class="mb-0">
                <span>Bạn đã có tài khoản?</span>
                <a href="<%= request.getContextPath() %>/login?page=login" class="text-center"> Đăng nhập</a>
            </p>
        </div>
        <!-- /.form-box -->
    </div><!-- /.card -->
</div>
<!-- /.register-box -->
<jsp:include page="/views/library/_script.jsp" />
</body>
</html>
