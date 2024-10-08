<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="ENTITY.Orders" %>
<%@ page import="ENTITY.Display" %>
<%@ page import="DTO.Customer_DTO" %>
<%@ page import="DAO.Order_DAO" %>
<%@ page import="ENTITY.Order_Details" %>
<%@ page import="SERVICE.Order_Service" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
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
    <%@ include file="Customer_navbar.jsp" %>

    <div class="container">
        <h2 class="my-4">Order History</h2>
        <table class="table table-bordered order-table">
            <thead>
                <tr>
                    <th>Product Image</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Total Paid</th>
                    <th>Status</th>
                    <th>Order Date</th>
                </tr>
            </thead>
            <tbody>
                <% 
                Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
                int customer_id = customer_DTO.getCustomer_id();
                Order_Service instamart_order = new Order_Service();
                List<Display> orders = instamart_order.viewOrdersByCustomer(customer_id);

                if (orders != null && !orders.isEmpty()) {
                    for (Display order : orders) {
                %>
                <tr>
                    <td><a  href="/RevShop_New/CustomerJSP/ProductDetails.jsp?product_id=<%= order.getProduct_id() %>">
                    <img src="<%= order.getImage_url() %>" alt="Product Image" class="product-image"></a></td>
                    <td><%= order.getProduct_name() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td>₹<%= String.format("%.2f", order.getTotal_amount()) %></td>
                    <td class="status"><%= order.getStatus() %></td>
                    <td><%= new java.text.SimpleDateFormat("dd MMM yyyy").format(order.getOrder_date()) %></td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="7">No orders found.</td>
                </tr>
                <% 
                }
                %>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
