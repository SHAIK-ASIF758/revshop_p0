<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="DTO.Customer_DTO" %>
<%@ page import="DAO.Product_DAO" %>
<%@ page import="DAO.Cart_DAO" %>
<%@ page import="ENTITY.Carts" %>
<%@ page import="ENTITY.Products" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .order-section { margin: 30px 0; }
    </style>
    <script>
        function checkBalance() {
            var totalAmount = parseFloat(document.getElementById("totalAmount").value);
            var walletAmount = parseFloat(document.getElementById("walletAmount").value);

            if (walletAmount >= totalAmount) {
                document.getElementById("placeOrderBtn").disabled = false;
                document.getElementById("proceedSection").style.display = "block";
                document.getElementById("addMoneySection").style.display = "none";
            } else {
                document.getElementById("placeOrderBtn").disabled = true;
                document.getElementById("proceedSection").style.display = "none";
                document.getElementById("addMoneySection").style.display = "block";
            }
        }

        function confirmOrder() {
            return confirm("Are you sure you want to place the order?");
        }
    </script>
</head>
<body>

<%@ include file="Customer_navbar.jsp" %>

<div class="container order-section">
    <h1>Place Your Order</h1>
    <hr>

    <%
        Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
    	//double totalCartValue=(double) request.getAttribute("total_amount");
        if (customer_DTO != null) {
            double walletAmount = customer_DTO.getWallet_balance();
            Cart_DAO cartDAO = new Cart_DAO();
            Product_DAO product_DAO=new Product_DAO();
            List<Carts> cartItems = cartDAO.viewCartByCustomer_id(customer_DTO.getCustomer_id());
            double totalCartValue = 0.0;
            if (cartItems != null && !cartItems.isEmpty()) {
                for (Carts cartItem : cartItems) {
                    Products product=product_DAO.getProductDetails(cartItem.getProduct_id());
                    totalCartValue += cartItem.getQuantity()*(product.getPrice() - (product.getMax_discount() * product.getPrice() / 100));
                }

                DecimalFormat df = new DecimalFormat("#.00");
    %>

    <div class="row">
        <div class="col-md-6">
            <h4>Current Wallet Balance: ₹<%= df.format(walletAmount) %></h4>
            <h4>Total Billing Amount: ₹<%= df.format(totalCartValue) %></h4>
        </div>
    </div>

    <!-- Hidden fields to store wallet and billing amounts -->
    <input type="hidden" id="walletAmount" value="<%= df.format(walletAmount) %>">
    <input type="hidden" id="totalAmount" value="<%= df.format(totalCartValue) %>">

    <div class="row mt-4">
        <div class="col-md-6" id="addMoneySection" style="display:none;">
            <p>Insufficient wallet balance! Please add money to your wallet to place the order.</p>
            <a href="CustomerAddToWalletServlet" class="btn btn-warning">Go to Wallet to Add Money</a>
        </div>

        <div class="col-md-6" id="proceedSection" style="display:none;">
            <form method="post" action="CustomerPlaceOrderSuccessServlet" onsubmit="return confirmOrder();">
                <div class="mb-3">
                    <label for="deliveryAddress" class="form-label">Delivery Address</label>
                    <textarea class="form-control" id="deliveryAddress" name="deliveryAddress" rows="3" required></textarea>
                </div>

                <input type="hidden" name="total_value" value="<%= totalCartValue %>">
                <input type="hidden" name="customer_id" value="<%= customer_DTO.getCustomer_id() %>">
                <button type="submit" id="placeOrderBtn" class="btn btn-success">Proceed & Place Order</button>
            </form>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-6">
            <a href="CustomerCartServlet" class="btn btn-secondary">Back to Cart</a>
        </div>
    </div>

    <script>
        // Check balance on page load
        checkBalance();
    </script>

    <%
            } else {
                out.println("<p>Your cart is empty.</p>");
            }
        } else {
            out.println("<p>You need to log in to place an order.</p>");
        }
    %>
</div>

</body>
</html>
