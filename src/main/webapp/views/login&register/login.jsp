<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/31/2025
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#"><b>WELCOME TO US</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Đăng nhập để thỏa sức khám phá</p>

            <form action="" method="post">
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
                <div class="row">
                    <div class="col-8">
                        <div class="icheck-primary">
                            <input type="checkbox" id="remember">
                            <label for="remember">
                                Ghi nhớ mật khẩu
                            </label>
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <div class="col-12 mt-4">
                    <a href="<%= request.getContextPath() %>/admin/dashboard?page=dashboard" class="btn btn-primary btn-block">Đăng nhập</a>
                </div>
                <!-- /.col -->
            </form>

<%--            <div class="social-auth-links text-center mb-3">--%>
<%--                <p>- OR -</p>--%>
<%--                <a href="#" class="btn btn-block btn-primary">--%>
<%--                    <i class="fab fa-facebook mr-2"></i> Sign in using Facebook--%>
<%--                </a>--%>
<%--                <a href="#" class="btn btn-block btn-danger">--%>
<%--                    <i class="fab fa-google-plus mr-2"></i> Sign in using Google+--%>
<%--                </a>--%>
<%--            </div>--%>
<%--            <!-- /.social-auth-links -->--%>

            <p class="mb-1">
                <a href="<%= request.getContextPath() %>/forgot-password?page=forgot-password">Bạn quên mật khẩu?</a>
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
