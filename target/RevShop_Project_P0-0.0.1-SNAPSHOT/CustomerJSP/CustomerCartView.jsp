<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.Cart_DAO" %>
<%@ page import="DAO.Favorite_DAO" %>
<%@ page import="DAO.Product_DAO" %>
<%@ page import="java.util.List" %>
<%@ page import="ENTITY.Carts" %>
<%@ page import="ENTITY.Products" %>
<%@ page import="DTO.Customer_DTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RevShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        .product-card { margin: 10px 0; }
        .product-img { max-width: 100px; }
        .quantity-btn { cursor: pointer; padding: 5px 10px; background-color: #ddd; border: none; }
    </style>
</head>
<body>
    <%@ include file="Customer_navbar.jsp" %>

    <div class="container">
        <h1>My Cart</h1>
        <hr>

        <%
            Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");

            if (customer_DTO != null) {
                Cart_DAO cartDAO = new Cart_DAO();
                Product_DAO productDAO = new Product_DAO();
                Favorite_DAO favoriteDAO = new Favorite_DAO();
                List<Carts> cartItems = cartDAO.viewCartByCustomer_id(customer_DTO.getCustomer_id());

                double totalCartValue = 0.0;

                if (cartItems != null && !cartItems.isEmpty()) {
        %>
        <div class="row">
            <% for (Carts cartItem : cartItems) { 
                int product_id = cartItem.getProduct_id();
                int quantity = cartItem.getQuantity();
                Products product = productDAO.getProductDetails(product_id);
                double discountPrice = product.getPrice() - (product.getMax_discount() * product.getPrice() / 100);
                double itemTotal = discountPrice * quantity;

                totalCartValue += itemTotal;
            %>
            <div class="col-md-12 product-card">
                <div class="row">
                    <div class="col-md-2">
                    <a href="/RevShop_Project_P0/CustomerJSP/ProductDetails.jsp?product_id=<%= product.getProduct_id() %>">
                        <img src="<%= product.getImage_url() %>" class="img-fluid product-img" alt="<%= product.getProduct_name() %>">
                        </a>
                    </div>
                    <div class="col-md-6">
                        <h5><%= product.getProduct_name() %></h5>
                        <p><strong>Price:</strong> ₹<%= product.getPrice() %></p>
                        <p><strong>Discount Price:</strong> ₹<%= discountPrice %></p>
                    </div>
                    <div class="col-md-4 text-center">
                        <form method="post" action="/RevShop_Project_P0/CustomerUpdateCartServlet">
                            <input type="hidden" name="cart_id" value="<%= cartItem.getCart_id() %>">
                            <button type="submit" name="action" value="decrease" class="quantity-btn">-</button>
                            <span><%= quantity %></span>
                            <button type="submit" name="action" value="increase" class="quantity-btn">+</button>
                        </form>
                        <form method="post" action="/RevShop_Project_P0/CustomerCartActionsServlet" style="margin-top: 10px;">
                            <input type="hidden" name="cart_id" value="<%= cartItem.getCart_id() %>">
                            <input type="hidden" name="product_id" value="<%= product_id %>">
                            <button type="submit" name="action" value="remove" class="btn btn-danger">Remove from Cart</button>
                            <button type="submit" name="action" value="move_to_favorites" class="btn btn-secondary">Move to Wishlist</button>
                        </form>
                    </div>
                </div>
            </div>
            <hr>
            <% } %>
        </div>
        <div class="row">
            <div class="col-md-12 text-right">
                <h4>Total Cart Value: ₹<%= String.format("%.2f", totalCartValue) %></h4>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-right">
                <form method="post" action="CustomerPlaceOrderServlet">
                    <input type="hidden" name="total_value" value="<%= totalCartValue %>">
                    <button type="submit" class="btn btn-primary">Place Order</button>
                </form>
            </div>
        </div>
        <% 
                } else {
                    out.println("<p>Your cart is empty.</p>");
                }
            } else {
                out.println("<p>You need to log in to view your cart.</p>");
            }
        %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>