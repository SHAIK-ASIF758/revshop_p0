<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.Customer_DTO" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RevShop</title>
    <link rel="stylesheet" href="../CSS/Customer_Main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="Customer_navbar.jsp" %>
<% Customer_DTO customer = (Customer_DTO) session.getAttribute("customer"); %>
    <div class="container">
        <h2>Update Customer Details</h2>
        <hr>
        <form action="CustomerUpdateProfile" method="post">
            <div class="mb-3">
                <label for="customer_name" class="form-label">Name</label>
                <input type="text" class="form-control" id="customer_name" name="customer_name" value="<%= customer.getName() %>" required>
            </div>
            <div class="mb-3">
                <label for="customer_email" class="form-label">Email</label>
                <input type="email" class="form-control" id="customer_email" name="customer_email" value="<%= customer.getEmail() %>" required>
            </div>
            <div class="mb-3">
                <label for="customer_phone_number" class="form-label">Phone Number</label>
                <input type="text" class="form-control" id="customer_phone_number" name="customer_phone_number" value="<%= customer.getPhone_number() %>" required>
            </div>
            <div class="mb-3">
                <label for="customer_address" class="form-label">Address</label>
                <textarea class="form-control" id="customer_address" name="customer_address" rows="3" required><%= customer.getAddress() %></textarea>
            </div>
            <div class="mb-3">
                <label for="customer_password" class="form-label">Change Password</label>
                <input type="password" class="form-control" id="customer_password" name="customer_password" placeholder="Enter a new password if you want to change (min-10 characters)">
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
