<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.Customer_DTO" %>
<%@ page import="DAO.Customer_DAO" %>

<% 
    Customer_DTO customer = (Customer_DTO) session.getAttribute("customer");
    double currentBalance = customer.getWallet_balance();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RevShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="Customer_navbar.jsp" %>

    <div class="container mt-5">
        <h2>My Wallet</h2>
        <hr>
        <div class="mb-4">
            <h4>Current Balance: ₹<%= String.format("%.2f", currentBalance) %></h4>
        </div>
        <form action="CustomerAddToWalletServlet" method="post" class="form-inline">
            <div class="mb-3">
                <label for="addAmount" class="form-label">Amount to Add:</label>
                <input type="number" class="form-control" id="addAmount" name="addAmount" min="1" required>
            </div>
            <button type="submit" class="btn btn-primary">Add to Wallet</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
