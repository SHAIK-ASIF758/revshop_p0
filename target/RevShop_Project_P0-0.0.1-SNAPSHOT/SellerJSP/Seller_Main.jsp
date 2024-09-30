<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.Seller_DTO" %>

<% Seller_DTO seller_DTO = (Seller_DTO) session.getAttribute("seller");
 String sellerName = seller_DTO.getSeller_name();
 String sellerEmail =seller_DTO.getSeller_email();
 sellerName = Character.toUpperCase(sellerName.charAt(0)) + sellerName.substring(1);
 sellerEmail=Character.toUpperCase(sellerEmail.charAt(0)) + sellerEmail.substring(1);%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RevShop</title>
   
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    
      <style>
    .charts-container {
        display: flex;
        justify-content: space-between;
        margin-top: 20px; /* Reduced margin-top */
    }
    .chart-card {
        flex: 1;
        margin: 0 5px; /* Reduced margin */
    }

    .chart-card canvas {
        max-width: 100% !important;
        max-height: 330px !important; /* Reduced height */
    }
    .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
    </style>
</head>
<body>

<%@ include file="Seller_navBar.jsp" %> 

    <section class="details-section container">

    <div class="header-container" style="margin:20px;">
            <h4 id="seller_name">Welcome, <%= sellerName %></h4>
            <h6 id="seller_email" ><%= sellerEmail %></h6>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total Earnings</h5>
                         
                        <p class="card-text" id="totalEarnings">₹<%= seller_DTO.getTotal_earning() %></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total Items Sold</h5>
                        <p class="card-text" id="totalItemsSold"><%= seller_DTO.getTotal_item_sold() %></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Current Month Earnings</h5>
                        <p class="card-text" id="currentMonthEarnings">₹<%= seller_DTO.getCurrent_month_earning() %></p>
                    </div>
                </div>
            </div>
            
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">This Month Items Sold</h5>
                        <p class="card-text" id="currentMonthItemsSold"><%= seller_DTO.getCurrent_month_item_sold() %></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4">
            <div class="chart-card">
                <h5>Items Sold</h5>
                <canvas id="itemsSoldChart"></canvas>
            </div>
            <div class="chart-card">
                <h5>Earnings</h5>
                <canvas id="earningsChart"></canvas>
            </div>
        </div>
    </section>
    <table>
    <thead></thead>
    </table>


    <!-- Chart.js Script -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        
        const totalEarnings = parseFloat(document.getElementById('totalEarnings').innerText.replace('₹', ''));
        const currentMonthEarnings = parseFloat(document.getElementById('currentMonthEarnings').innerText.replace('₹', ''));
        const totalItemsSold = parseInt(document.getElementById('totalItemsSold').innerText);
        const currentMonthItemsSold = parseInt(document.getElementById('currentMonthItemsSold').innerText);

        const itemsSoldCtx = document.getElementById('itemsSoldChart').getContext('2d');
        new Chart(itemsSoldCtx, {
            type: 'pie',
            data: {
                labels: ['Total Items Sold', 'Current Month Items Sold'],
                datasets: [{
                    data: [totalItemsSold, currentMonthItemsSold],
                    backgroundColor: ['#007bff', '#28a745'],
                    borderColor: '#fff',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top',
                        labels: {
                            color: '#333'
                        }
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                return context.label + ': ' + context.raw;
                            }
                        }
                    }
                }
            }
        });

        // Pie Chart for Earnings
        const earningsCtx = document.getElementById('earningsChart').getContext('2d');
        new Chart(earningsCtx, {
            type: 'pie',
            data: {
                labels: ['Total Earnings', 'Current Month Earnings'],
                datasets: [{
                    data: [totalEarnings, currentMonthEarnings],
                    backgroundColor: ['#dc3545', '#ffc107'],
                    borderColor: '#fff',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top',
                        labels: {
                            color: '#333'
                        }
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                return context.label + ': ₹' + context.raw;
                            }
                        }
                    }
                }
            }
        });
    });

    </script>
</body>
</html>
    