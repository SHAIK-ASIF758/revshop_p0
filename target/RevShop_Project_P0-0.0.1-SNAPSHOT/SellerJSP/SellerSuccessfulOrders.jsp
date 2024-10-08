<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="ENTITY.Display" %>
<%@ page import="DTO.Seller_DTO" %>
<%@ page import="DAO.Order_DAO" %>
<%@ page import="SERVICE.Order_Service" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RevShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    <style>
        .order-table {
            margin-top: 20px;
        }
        .order-table th, .order-table td {
            text-align: center;
        }
        .status {
            font-weight: bold;
        }
        .product-image {
            max-width: 100px;
            height: auto;
        }
    </style>
</head>
<body>
    <%@ include file="Seller_navBar.jsp" %>

    <div class="container">
        <h2 class="my-4">Completed Orders</h2>
        <table class="table table-bordered order-table">
            <thead>
                <tr><th>Order ID</th>
                    <th>Product Image</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Total Paid</th>
                    <th>Status</th>
                    <th>Order Date</th>
                    <th>Address></th>
                </tr>
            </thead>
            <tbody>
                <% 
                Seller_DTO seller_DTO = (Seller_DTO) session.getAttribute("seller");
                int seller_id = seller_DTO.getSeller_id();
                Order_Service instamart_order = new Order_Service();
                List<Display> orders = instamart_order.viewOrdersBySeller(seller_id);

                if (orders != null && !orders.isEmpty()) {
                    for (Display order : orders) {
                    	if(order.getStatus().equals("Delivered")){
                %>
                <tr>
                    <td><%= order.getOrder_id() %></td>
                    <td><img src="<%= order.getImage_url() %>" alt="Product Image" class="product-image"></td>
                    <td><%= order.getProduct_name() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td>₹<%= String.format("%.2f", order.getTotal_amount()) %></td>
                    <td class="status"><%= order.getStatus() %></td>
                    <td><%= new java.text.SimpleDateFormat("dd MMM yyyy").format(order.getOrder_date()) %></td>
                    <td>
    <div class="address-scroll">
        <%= order.getDelivery_address() %>
    </div>
</td>
                    
            <% }
                    }
                }
                %>

            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
