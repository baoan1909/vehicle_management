<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bảng Giá Dịch Vụ Đỗ Xe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/pricing_style.css">
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
                <!-- Hero Section -->
                <div class="pricing-hero">
                    <div class="hero-text">
                        <h2>Dễ dàng tìm chỗ đậu xe, an toàn và hiệu quả</h2>
                    </div>
                    <div class="hero-image">
                        <!-- Car SVG Image -->
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" style="float: right; margin-left: auto; display: block;">
                            <path fill="#4361ee" d="M499.99 176h-59.87l-16.64-41.6C406.38 91.63 365.57 64 319.5 64h-127c-46.06 0-86.88 27.63-103.99 70.4L71.87 176H12.01C4.2 176-1.53 183.34.37 190.91l6 24C7.7 220.25 12.5 224 18.01 224h20.07C24.65 235.73 16 252.78 16 272v48c0 16.12 6.16 30.67 16 41.93V416c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32v-32h256v32c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32v-54.07c9.84-11.25 16-25.8 16-41.93v-48c0-19.22-8.65-36.27-22.07-48H494c5.51 0 10.31-3.75 11.64-9.09l6-24c1.89-7.57-3.84-14.91-11.65-14.91zm-352.06-17.83c7.29-18.22 24.94-30.17 44.57-30.17h127c19.63 0 37.28 11.95 44.57 30.17L384 208H128l19.93-49.83zM96 319.8c-19.2 0-32-12.76-32-31.9S76.8 256 96 256s48 28.71 48 47.85-28.8 15.95-48 15.95zm320 0c-19.2 0-48 3.19-48-15.95S396.8 256 416 256s32 12.76 32 31.9-12.8 31.9-32 31.9z"/>
                        </svg>
                    </div>
                </div>

                <!-- Vehicle Type Tabs -->
                <div class="pricing-tabs">
                    <div class="tab-buttons">
                        <button class="tab-btn active" data-target="visitor">
                            <i class="fas fa-user-clock"></i> Vãng Lai
                        </button>
                        <button class="tab-btn" data-target="car">
                            <i class="fas fa-car"></i> Xe Hơi
                        </button>
                        <button class="tab-btn" data-target="motorcycle">
                            <i class="fas fa-motorcycle"></i> Xe Máy
                        </button>
                        <button class="tab-btn" data-target="other">
                            <i class="fas fa-truck"></i> Xe Khác
                        </button>
                    </div>
                </div>

                <!-- Pricing Containers -->
                 <!-- Vãng Lai Pricing -->
                <div class="pricing-container active" id="visitor">
                    <div class="pricing-cards">
                        <!-- Xe Hơi -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Xe Hơi</div>
                                <div class="card-price">80.000 ₫</div>
                                <div class="card-period">/ lượt</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Thanh toán đơn giản</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Đỗ xe khi cần</li>
                                    <li><i class="fas fa-check-circle"></i> Không cần đăng ký trước</li>
                                </ul>
                            </div>
                          
                        
                         
                        </div>

                        <!-- Xe Máy -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Xe Máy</div>
                                <div class="card-price">10.000 ₫</div>
                                <div class="card-period">/ lượt</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Thanh toán đơn giản</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Đỗ xe khi cần</li>
                                    <li><i class="fas fa-check-circle"></i> Không cần đăng ký trước</li>
                                </ul>
                            </div>
                         
                      
                        </div>

                        <!-- Xe Khác -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Xe Khác</div>
                                <div class="card-price">4.000 ₫</div>
                                <div class="card-period">/ lượt</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Thanh toán đơn giản</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Đỗ xe khi cần</li>
                                    <li><i class="fas fa-check-circle"></i> Không cần đăng ký trước</li>
                                </ul>
                            </div>
            
                    

                        </div>
                    </div>
                </div>
                                
                <!-- Xe Hơi Pricing -->
                <div class="pricing-container " id="car">
                    <div class="pricing-cards">
                        <!-- Tháng -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Tháng</div>
                                <div class="card-price">1.200.000 ₫</div>
                                <div class="card-period">/ tháng</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 50% so với theo ngày</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí cố định</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>

                        <!-- Quý -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Quý</div>
                                <div class="card-price">3.200.000 ₫</div>
                                <div class="card-period">/ quý</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 10% so với theo tháng</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí ưu tiên</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>

                        <!-- Năm -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Năm</div>
                                <div class="card-price">12.000.000 ₫</div>
                                <div class="card-period">/ năm</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 20% so với theo quý</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí VIP</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Xe Máy Pricing -->
                <div class="pricing-container" id="motorcycle">
                    <div class="pricing-cards">
                 

                        <!-- Tháng -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Tháng</div>
                                <div class="card-price">150.000 ₫</div>
                                <div class="card-period">/ tháng</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 50% so với theo ngày</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí cố định</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>

                        <!-- Quý -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Quý</div>
                                <div class="card-price">420.000 ₫</div>
                                <div class="card-period">/ quý</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 10% so với theo tháng</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí ưu tiên</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>

                        <!-- Năm -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Năm</div>
                                <div class="card-price">1.500.000 ₫</div>
                                <div class="card-period">/ năm</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 20% so với theo quý</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí VIP</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Xe Khác Pricing -->
                <div class="pricing-container" id="other">
                    <div class="pricing-cards">

                        <!-- Tháng -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Tháng</div>
                                <div class="card-price">50.000 ₫</div>
                                <div class="card-period">/ tháng</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 50% so với theo ngày</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí cố định</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>

                        <!-- Quý -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Quý</div>
                                <div class="card-price">130.000 ₫</div>
                                <div class="card-period">/ quý</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 10% so với theo tháng</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí ưu tiên</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>

                        <!-- Năm -->
                        <div class="pricing-card">
                            <div class="card-header">
                                <div class="card-title">Theo Năm</div>
                                <div class="card-price">500.000 ₫</div>
                                <div class="card-period">/ năm</div>
                            </div>
                            <div class="card-body">
                                <ul class="card-features">
                                    <li><i class="fas fa-check-circle"></i> Tiết kiệm 20% so với theo quý</li>
                                    <li><i class="fas fa-check-circle"></i> Hỗ trợ 24/7</li>
                                    <li><i class="fas fa-check-circle"></i> Bắt đầu: 30/04/2025</li>
                                    <li><i class="fas fa-check-circle"></i> Vị trí VIP</li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <button class="card-button">Đăng Ký Ngay</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Contact Information -->
                <div class="contact-section">
                    <div class="contact-title">Thông Tin Liên Hệ</div>
                    <div class="contact-items">
                        <div class="contact-item">
                            <div class="contact-icon">
                                <i class="fas fa-phone"></i>
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
                                info@smartparking.vn
                            </div>
                        </div>
                        <div class="contact-item">
                            <div class="contact-icon">
                                <i class="fas fa-map-marker-alt"></i>
                            </div>
                            <div class="contact-text">
                                <strong>Địa chỉ</strong>
                                123 Đường Nguyễn Văn Linh, Quận 7, TP.HCM
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div><!-- /.content-wrapper -->
</div><!-- /.wrapper -->

<jsp:include page="/views/library/_script.jsp" />
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Tab switching
        const tabButtons = document.querySelectorAll('.tab-btn');
        
        tabButtons.forEach(button => {
            button.addEventListener('click', function() {
                // Remove active class from all buttons and containers
                document.querySelectorAll('.tab-btn').forEach(btn => {
                    btn.classList.remove('active');
                });
                document.querySelectorAll('.pricing-container').forEach(container => {
                    container.classList.remove('active');
                });
                
                // Add active class to clicked button
                this.classList.add('active');
                
                // Show the corresponding container
                const targetId = this.getAttribute('data-target');
                document.getElementById(targetId).classList.add('active');
            });
        });
        
        // Button animations
        const cardButtons = document.querySelectorAll('.card-button');
        
        cardButtons.forEach(button => {
            button.addEventListener('click', function() {
                this.innerHTML = 'Đã đăng ký';
                this.style.backgroundColor = '#4CAF50';
                this.disabled = true;
                
                // Reset after 2 seconds
                setTimeout(() => {
                    this.innerHTML = 'Đăng Ký Ngay';
                    this.style.backgroundColor = '';
                    this.disabled = false;
                }, 2000);
            });
        });
    });
</script>
</body>
</html>