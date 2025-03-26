<%--
  Created by IntelliJ IDEA.
  User: baoan
  Date: 3/25/2025
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card Swipe</title>
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
            <h1 class="m-0">Quản lý vào ra</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
              <li class="breadcrumb-item active">Vào ra</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div>

      <!-- Content Wrapper. Contains page content -->
      <section class="content">
        <div class="container-fluid">
          <div class="col-12 mt-4">
            <div class="card shadow">
              <div class="card-body">
                <div class="form-group col-md-4 ml-auto">
                  <div class="input-group">
                    <input type="text" class="form-control float-right" id="daterange-btn">
                    <div class="input-group-prepend">
                      <span class="input-group-text bg-cyan">
                        <i class="far fa-calendar-alt"></i>
                      </span>
                    </div>
                  </div>
                  <!-- /.input group -->
                </div>
                <div class="col-12 card card-cyan card-outline"  >

                </div>
              </div>
            </div>
            <!-- /.info-box -->
          </div>

        </div>
      </section>
    </div>
  </div>
</div>
<jsp:include page="/views/library/_script.jsp" />
</body>
</html>
