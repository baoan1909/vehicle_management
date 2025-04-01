<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Chủ - Liên Hệ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/style.css">
    <jsp:include page="/views/library/_css.jsp" />
</head>
<body class="hold-transition">
<div class="wrapper">
    <!-- Navbar -->
    <nav class="navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item d-none d-sm-inline-block">
                <a href="#" class="nav-link">Home</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="<%= request.getContextPath() %>/contact?page=contact" 
                   class="nav-link <%= "contact".equals(request.getParameter("page")) ? "active" : "" %>">
                   Contact
                </a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="<%= request.getContextPath() %>/pricing?page=pricing" 
                   class="nav-link <%= "pricing".equals(request.getParameter("page")) ? "active" : "" %>">
                   Pricing
                </a>
            </li>
        </ul>
    
        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Navbar Search -->
            <li class="nav-item mr-2">
                <a class="input-group" data-widget="">
                    <input class="form-control form-control-sidebar" type="search" placeholder="Tìm kiếm" aria-label="Search">
                    <div class="input-group-append">
                        <button class="btn btn-sidebar bg-cyan">
                            <i class="fas fa-search fa-fw"></i>
                        </button>
                    </div>
                </a>
            </li>
        </ul>
    </nav>
    
    <!-- Sidebar -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

        <section class="content">
            <div class="container-fluid">
                <div class="contact-card animate-fadeInUp">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="contact-info-section">
                                <div class="contact-decoration decoration-1"></div>
                                <div class="contact-decoration decoration-2"></div>
                                
                                <h2>Thông tin liên hệ</h2>
                                
                                <div class="contact-item">
                                    <div class="contact-icon">
                                        <i class="fas fa-map-marker-alt"></i>
                                    </div>
                                    <div class="contact-text">
                                        <strong>Địa chỉ</strong>
                                        123 Đường Nguyễn Văn Linh, Quận 7, TP.HCM
                                    </div>
                                </div>
                                
                                <div class="contact-item">
                                    <div class="contact-icon">
                                        <i class="fas fa-phone-alt"></i>
                                    </div>
                                    <div class="contact-text">
                                        <strong>Điện thoại</strong>
                                        +84 28 1234 5678
                                    </div>
                                </div>
                                
                                <div class="contact-item">
                                    <div class="contact-icon">
                                        <i class="fas fa-envelope"></i>
                                    </div>
                                    <div class="contact-text">
                                        <strong>Email</strong>
                                        support@smartparking.vn
                                    </div>
                                </div>
                                
                                <div class="contact-item">
                                    <div class="contact-icon">
                                        <i class="fas fa-clock"></i>
                                    </div>
                                    <div class="contact-text">
                                        <strong>Giờ làm việc</strong>
                                        T2-T6: 8:00 - 17:30<br>
                                        T7: 8:00 - 12:00
                                    </div>
                                </div>
                                
                                <div class="social-media">
                                    <div class="social-button">
                                        <i class="fab fa-facebook-f"></i>
                                    </div>
                                    <div class="social-button">
                                        <i class="fab fa-twitter"></i>
                                    </div>
                                    <div class="social-button">
                                        <i class="fab fa-instagram"></i>
                                    </div>
                                    <div class="social-button">
                                        <i class="fab fa-linkedin-in"></i>
                                    </div>
                                </div>
                                
                                <svg class="illustration float-animation" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="200" height="200">
                                    <path fill="#ffffff" d="M499.99 176h-59.87l-16.64-41.6C406.38 91.63 365.57 64 319.5 64h-127c-46.06 0-86.88 27.63-103.99 70.4L71.87 176H12.01C4.2 176-1.53 183.34.37 190.91l6 24C7.7 220.25 12.5 224 18.01 224h20.07C24.65 235.73 16 252.78 16 272v48c0 16.12 6.16 30.67 16 41.93V416c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32v-32h256v32c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32v-54.07c9.84-11.25 16-25.8 16-41.93v-48c0-19.22-8.65-36.27-22.07-48H494c5.51 0 10.31-3.75 11.64-9.09l6-24c1.89-7.57-3.84-14.91-11.65-14.91zm-352.06-17.83c7.29-18.22 24.94-30.17 44.57-30.17h127c19.63 0 37.28 11.95 44.57 30.17L384 208H128l19.93-49.83zM96 319.8c-19.2 0-32-12.76-32-31.9S76.8 256 96 256s48 28.71 48 47.85-28.8 15.95-48 15.95zm320 0c-19.2 0-48 3.19-48-15.95S396.8 256 416 256s32 12.76 32 31.9-12.8 31.9-32 31.9z"/>
                                </svg>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="form-section">
                                <h2>Dễ dàng tìm chỗ đậu xe, an toàn và hiệu quả</h2>
                                <p>Đội ngũ chuyên nghiệp của chúng tôi luôn sẵn sàng hỗ trợ bạn với mọi thắc mắc. Vui lòng điền thông tin của bạn, và chúng tôi sẽ liên hệ lại trong thời gian sớm nhất.</p>
                                
                                <form action="#" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="firstName" placeholder="Nhập họ của bạn" required>
                                                <label for="firstName" class="form-label">Họ</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="lastName" placeholder="Nhập tên của bạn" required>
                                                <label for="lastName" class="form-label">Tên</label>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="email" placeholder="Nhập địa chỉ email của bạn" required>
                                        <label for="email" class="form-label">Email</label>
                                    </div>
                                    
                                    <div class="form-group">
                                        <input type="tel" class="form-control" id="phone" placeholder="Nhập số điện thoại của bạn" required>
                                        <label for="phone" class="form-label">Số điện thoại</label>
                                    </div>
                                    
                                    <div class="checkbox-group">
                                        <label class="custom-checkbox">
                                            <input type="checkbox" checked="checked">
                                            <span class="checkmark"></span>
                                            Tôi đồng ý với các điều khoản và điều kiện của dịch vụ
                                        </label>
                                    </div>
                                    
                                    <button type="submit" class="submit-btn">
                                        <i class="fas fa-paper-plane btn-icon"></i>
                                        Gửi thông tin
                                    </button>
                                </form>
                                
                                <div class="or-divider">
                                    <span>Hoặc</span>
                                </div>
                                
                                <div class="quick-contact">
                                    <a href="mailto:support@smartparking.vn" class="quick-btn email-btn">
                                        <i class="fas fa-envelope"></i>
                                        Gửi email
                                    </a>
                                    <a href="tel:+842812345678" class="quick-btn phone-btn">
                                        <i class="fas fa-phone-alt"></i>
                                        Gọi ngay
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div><!-- /.content-wrapper -->
</div><!-- /.wrapper -->

<jsp:include page="/views/library/_script.jsp" />
<script src="${pageContext.request.contextPath}/assets/customer/contact.js"></script>
</body>
</html>