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

            <form action="<%= request.getContextPath() %>/login" method="post">
                <% String error = (String) request.getAttribute("error"); if (error != null) { %>
                <div class="alert alert-danger mt-2"><%= error %></div>
                <% } %>
                <div class="input-group mb-3">
                    <input type="text" name="username" id="username" class="form-control" placeholder="Tên tài khoản" required>

                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" name="password" id="password" class="form-control" placeholder="Mật khẩu" required>

                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-8">
                        <div class="icheck-primary">
                            <input type="checkbox" name="remember" id="remember">
                            <label for="remember">Ghi nhớ mật khẩu</label>
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <div class="col-12 mt-4">
                    <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
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
