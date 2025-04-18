<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Dashboard</title>
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
                            <h1 class="m-0">Trang chủ</h1>
                        </div>
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <li class="breadcrumb-item"><a href="#">Quản lý</a></li>
                                <li class="breadcrumb-item active">Trang chủ</li>
                            </ol>
                        </div>
                    </div>
                </div>

                <!-- Content Wrapper. Contains page content -->
                <section class="content">
                    <div class="container-fluid">
                        <div class="container mt-4">
                            <div class="col-12">
                                <div class="card card-cyan card-outline">
                                    <div class="card-body">
                                        <div class="col-10 mx-auto">
                                            <div class="row info-box d-flex justify-content-center align-items-center shadow">
                                                <div class="col-md-4 text-center d-flex flex-column justify-content-center">
                                                    <span class="info-box-text">Tổng doanh thu</span>
                                                    <span class="info-box-number">
                                                        <fmt:formatNumber value="${totalRevenue}" type="currency" currencySymbol="" />đ
                                                    </span>
                                                </div>
                                                <div class="col-md-4 text-center d-flex flex-column justify-content-center">
                                                    <span class="info-box-text">Khách vào bãi</span>
                                                    <span class="info-box-number">${totalVisitors}</span>
                                                </div>
                                                <div class="col-md-4 text-center d-flex flex-column justify-content-center">
                                                    <span class="info-box-text">Thẻ đã đăng ký</span>
                                                    <span class="info-box-number">${totalRegisteredCards}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!-- Area Chart -->
                                <div class="col-xl-8 col-lg-7">
                                    <div class="card card-cyan card-outline shadow mb-4">
                                        <div class="card-header">
                                            <h3 class="card-title">Tổng doanh thu</h3>
                                            <div class="card-tools">
                                                <button type="button" class="btn btn-tool">
                                                    <i class="fas fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="chart-area">
                                                <canvas id="AreaChart" width="978" height="400" style="display: block; height: 320px; width: 783px;" class="chartjs-render-monitor"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-4 col-lg-5">
                                    <div class="card card-cyan card-outline shadow mb-4">
                                        <div class="card-header">
                                            <h3 class="card-title">Tổng loại xe</h3>
                                            <div class="card-tools">
                                                <button type="button" class="btn btn-tool">
                                                    <i class="fas fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="chart-pie pt-4 pb-2">
                                                <canvas id="PieChart" width="448" height="306" style="display: block; height: 245px; width: 359px;" class="chartjs-render-monitor"></canvas>
                                            </div>
                                            <div class="mt-4 text-center small">
                                                <c:forEach var="vehicle" items="${vehicleTypeData}" varStatus="loop">
                                                    <span class="mr-2">
                                                        <i class="fas fa-circle 
                                                            <c:choose>
                                                                <c:when test="${loop.index == 0}">text-primary</c:when>
                                                                <c:when test="${loop.index == 1}">text-success</c:when>
                                                                <c:when test="${loop.index == 2}">text-info</c:when>
                                                                <c:otherwise>text-warning</c:otherwise>
                                                            </c:choose>
                                                        "></i> ${vehicle.type}
                                                    </span>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 mb-4">
                                    <div class="card card-cyan card-outline shadow mb-4">
                                        <div class="card-header py-3">
                                            <h6 class="card-title">Thẻ</h6>
                                            <div class="card-tools">
                                                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                    <i class="fas fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <h4 class="small font-weight-bold">Mất 
                                                <span class="float-right">
                                                    <fmt:formatNumber value="${cardStats.lostCardsPercent}" type="number" maxFractionDigits="1"/>%
                                                </span>
                                            </h4>
                                            <div class="progress mb-4">
                                                <div class="progress-bar bg-yellowr" role="progressbar" 
                                                     style="width: ${cardStats.lostCardsPercent}%" 
                                                     aria-valuenow="${cardStats.lostCardsPercent}" 
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                            <h4 class="small font-weight-bold">Thành viên 
                                                <span class="float-right">
                                                    <fmt:formatNumber value="${cardStats.memberCardsPercent}" type="number" maxFractionDigits="1"/>%
                                                </span>
                                            </h4>
                                            <div class="progress mb-4">
                                                <div class="progress-bar bg-info" role="progressbar" 
                                                     style="width: ${cardStats.memberCardsPercent}%" 
                                                     aria-valuenow="${cardStats.memberCardsPercent}" 
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                            <h4 class="small font-weight-bold">Vãng lai
                                                <span class="float-right">
                                                    <fmt:formatNumber value="${cardStats.visitorCardsPercent}" type="number" maxFractionDigits="1"/>%
                                                </span>
                                            </h4>
                                            <div class="progress mb-4">
                                                <div class="progress-bar bg-success" role="progressbar" 
                                                     style="width: ${cardStats.visitorCardsPercent}%" 
                                                     aria-valuenow="${cardStats.visitorCardsPercent}" 
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 mb-4">
                                    <div class="card card-cyan card-outline shadow mb-4">
                                        <div class="card-header py-3">
                                            <h6 class="card-title">Thiết bị</h6>
                                            <div class="card-tools">
                                                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                    <i class="fas fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="d-sm-flex justify-content-between">
                                                <div class="col-xl-6 col-md-6 mb-4">
                                                    <div class="card card-info card-outline shadow h-100 py-2">
                                                        <div class="card-body">
                                                            <div class="row no-gutters align-items-center">
                                                                <div class="col mr-2">
                                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Camera</div>
                                                                    <div class="row no-gutters align-items-center">
                                                                        <div class="col-auto">
                                                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">50</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-auto">
                                                                    <i class="fas fa-video fa-2x text-gray"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xl-6 col-md-6 mb-4">
                                                    <div class="card card-yellow card-outline shadow h-100 py-2">
                                                        <div class="card-body">
                                                            <div class="row no-gutters align-items-center">
                                                                <div class="col mr-2">
                                                                    <div class="text-xs font-weight-bold text-yellow text-uppercase mb-1">Đầu lọc thẻ</div>
                                                                    <div class="row no-gutters align-items-center">
                                                                        <div class="col-auto">
                                                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">3</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-auto">
                                                                    <i class="fas fa-sd-card fa-2x text-gray"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="d-sm-flex justify-content-between">
                                                <div class="col-xl-6 col-md-6 mb-4">
                                                    <div class="card card-green card-outline shadow h-100 py-2">
                                                        <div class="card-body">
                                                            <div class="row no-gutters align-items-center">
                                                                <div class="col mr-2">
                                                                    <div class="text-xs font-weight-bold text-green text-uppercase mb-1">Máy tính</div>
                                                                    <div class="row no-gutters align-items-center">
                                                                        <div class="col-auto">
                                                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">4</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-auto">
                                                                    <i class="fas fa-desktop fa-2x text-gray"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xl-6 col-md-6 mb-4">
                                                    <div class="card card-primary card-outline shadow h-100 py-2">
                                                        <div class="card-body">
                                                            <div class="row no-gutters align-items-center">
                                                                <div class="col mr-2">
                                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Barie</div>
                                                                    <div class="row no-gutters align-items-center">
                                                                        <div class="col-auto">
                                                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-dark">7</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-auto">
                                                                    <i class="fas fa-pallet fa-2x text-gray"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Hidden data for charts -->
                    <div style="display: none;">
                        <!-- Revenue data for the area chart -->
                        <c:forEach var="revenue" items="${revenueData}">
                            <input type="hidden" class="revenue-data" 
                                   data-month="${revenue.month}" 
                                   data-value="${revenue.revenue}">
                        </c:forEach>
                        
                        <!-- Vehicle type data for the pie chart -->
                        <c:forEach var="vehicle" items="${vehicleTypeData}">
                            <input type="hidden" class="vehicle-data" 
                                   data-type="${vehicle.type}" 
                                   data-count="${vehicle.count}">
                        </c:forEach>
                    </div>
                </section>
            </div>
        </div>
    </div>
    <jsp:include page="/views/library/_script.jsp" />
    <script>
    $(document).ready(function() {
        // Khởi tạo biểu đồ doanh thu
        var ctx = document.getElementById("AreaChart");
        if (ctx) {
            // Lấy dữ liệu từ các input ẩn
            var revenueData = [];
            var months = [];
            
            $('.revenue-data').each(function() {
                var month = $(this).data('month');
                var revenue = $(this).data('value');
                
                // Chuyển số tháng thành tên tháng
                var monthNames = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", 
                                "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
                
                months.push(monthNames[month-1]);
                revenueData.push(revenue);
            });
            
            var myLineChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: months,
                    datasets: [{
                        label: "Doanh thu",
                        lineTension: 0.3,
                        backgroundColor: "rgba(78, 115, 223, 0.05)",
                        borderColor: "rgba(78, 115, 223, 1)",
                        pointRadius: 3,
                        pointBackgroundColor: "rgba(78, 115, 223, 1)",
                        pointBorderColor: "rgba(78, 115, 223, 1)",
                        pointHoverRadius: 3,
                        pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                        pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                        pointHitRadius: 10,
                        pointBorderWidth: 2,
                        data: revenueData,
                    }],
                },
                options: {
                    maintainAspectRatio: false,
                    layout: {
                        padding: {
                            left: 10,
                            right: 25,
                            top: 25,
                            bottom: 0
                        }
                    },
                    scales: {
                        xAxes: [{
                            time: {
                                unit: 'date'
                            },
                            gridLines: {
                                display: false,
                                drawBorder: false
                            },
                            ticks: {
                                maxTicksLimit: 7
                            }
                        }],
                        yAxes: [{
                            ticks: {
                                maxTicksLimit: 5,
                                padding: 10,
                                callback: function(value, index, values) {
                                    return number_format(value) + 'đ';
                                }
                            },
                            gridLines: {
                                color: "rgb(234, 236, 244)",
                                zeroLineColor: "rgb(234, 236, 244)",
                                drawBorder: false,
                                borderDash: [2],
                                zeroLineBorderDash: [2]
                            }
                        }],
                    },
                    legend: {
                        display: false
                    },
                    tooltips: {
                        backgroundColor: "rgb(255,255,255)",
                        bodyFontColor: "#858796",
                        titleMarginBottom: 10,
                        titleFontColor: '#6e707e',
                        titleFontSize: 14,
                        borderColor: '#dddfeb',
                        borderWidth: 1,
                        xPadding: 15,
                        yPadding: 15,
                        displayColors: false,
                        intersect: false,
                        mode: 'index',
                        caretPadding: 10,
                        callbacks: {
                            label: function(tooltipItem, chart) {
                                var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                                return datasetLabel + ': ' + number_format(tooltipItem.yLabel) + 'đ';
                            }
                        }
                    }
                }
            });
        }
        
        // Khởi tạo biểu đồ loại xe
        var ctxPie = document.getElementById("PieChart");
        if (ctxPie) {
            // Lấy dữ liệu từ các input ẩn
            var vehicleLabels = [];
            var vehicleCounts = [];
            var backgroundColors = [
                '#4e73df',
                '#1cc88a', 
                '#36b9cc', 
                '#f6c23e' 
            ];
            
            $('.vehicle-data').each(function(index) {
                var type = $(this).data('type');
                var count = $(this).data('count');
                
                vehicleLabels.push(type);
                vehicleCounts.push(count);
            });
            
            
            var myPieChart = new Chart(ctxPie, {
                type: 'doughnut',
                data: {
                    labels: vehicleLabels,
                    datasets: [{
                        data: vehicleCounts,
                        backgroundColor: backgroundColors,
                        hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf', '#dda20a'],
                        hoverBorderColor: "rgba(234, 236, 244, 1)",
                    }],
                },
                options: {
                    maintainAspectRatio: false,
                    tooltips: {
                        backgroundColor: "rgb(255,255,255)",
                        bodyFontColor: "#858796",
                        borderColor: '#dddfeb',
                        borderWidth: 1,
                        xPadding: 15,
                        yPadding: 15,
                        displayColors: false,
                        caretPadding: 10,
                    },
                    legend: {
                        display: false
                    },
                    cutoutPercentage: 80,
                },
            });
        }
        
        // Hàm định dạng số
        function number_format(number, decimals, dec_point, thousands_sep) {
            number = (number + '').replace(',', '').replace(' ', '');
            var n = !isFinite(+number) ? 0 : +number,
                prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
                sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                s = '',
                toFixedFix = function(n, prec) {
                    var k = Math.pow(10, prec);
                    return '' + Math.round(n * k) / k;
                };
            s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
            if (s[0].length > 3) {
                s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
            }
            if ((s[1] || '').length < prec) {
                s[1] = s[1] || '';
                s[1] += new Array(prec - s[1].length + 1).join('0');
            }
            return s.join(dec);
        }
    });
    </script>
</body>
</html>