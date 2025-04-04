<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/28/2025
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role Detail</title>
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
                        <h1 class="m-0">Thông tin chi tiết vai trò</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Quản lý vai trò</a></li>
                            <li class="breadcrumb-item active">Thông tin vai trò</li>
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
                                    <h3 class="card-title">${role.roleId == 0 ? "Thêm Vai trò mới" : "Chỉnh sửa Vai trò"}</h3>
                                </div>
                                <!-- /.card-header -->
                                <!-- form start -->
                                <form action="${pageContext.request.contextPath}/admin/role/save" method="post">
                                    <div class="card-body">
                                        <input type="hidden" name="id" value="${role.roleId != null ? role.roleId : 0}">
                                        <div class="form-group">
                                            <label>Tên vai trò:</label>
                                            <input type="text" class="form-control" placeholder="Admin" name="roleName" value="${role.roleName}" required>
                                        </div>
<%--                                        <div class="form-group">--%>
<%--                                            <label>Mô tả:</label>--%>
<%--                                            <textarea class="form-control" rows="3" placeholder="Nhập mô tả chi tiết về vai trò"></textarea>--%>
<%--                                        </div>--%>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <a class="btn btn-default" href="<%= request.getContextPath() %>/admin/role">Thoát</a>
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
</body>
</html>