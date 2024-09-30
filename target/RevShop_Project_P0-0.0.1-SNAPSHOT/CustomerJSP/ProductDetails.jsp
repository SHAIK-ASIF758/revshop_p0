<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.Product_DAO" %>
<%@ page import="ENTITY.Products" %>
<%@ page import="DAO.Cart_DAO" %>
<%@ page import="DTO.Customer_DTO" %>
<%@ page import="DAO.Favorite_DAO" %>
<%@ page import="ENTITY.Carts" %>
<%@ page import="ENTITY.Favorites" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RevShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
</head>
<body>
    <%@ include file="Customer_navbar.jsp" %>

    <div class="container">
        <%
            String productId = request.getParameter("product_id");
            if (productId != null) {
                Product_DAO productDAO = new Product_DAO();
                Products product = productDAO.getProductDetails(Integer.parseInt(productId));
                
                Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
                
                Cart_DAO cartDAO = new Cart_DAO();
                Favorite_DAO wishlistDAO = new Favorite_DAO();
                /*this.setSeller_id(seller_id);
		this.setCustomer_id(customer_id);
		this.product_id=product_id;
		this.setCategory_id(category_id);
		this.setQuantity(quantity);*/
                //Carts cart=new Carts(product.getSeller_id(),customer_DTO.getCustomer_id(),product.getProduct_id(),product.getCategory_id(),1);
                boolean isInCart = cartDAO.checkProductByCustomerCart(customer_DTO.getCustomer_id(), product.getProduct_id());
                boolean isInWishlist = wishlistDAO.checkProductByCustomerWishlist(customer_DTO.getCustomer_id(), product.getProduct_id());
        %>

        <div class="row">
            <div class="col-md-6">
                <img src="<%= product.getImage_url() %>" class="img-fluid" alt="<%= product.getProduct_name() %>">
            </div>
            <div class="col-md-6">
                <h2><%= product.getProduct_name() %></h2>
                <p><%= product.getDescription() %></p>
                <p><strong>Price:</strong> ₹<%= product.getPrice() %></p>
                <p><strong>Discount Price:</strong> ₹<%= String.format("%.2f",product.getPrice() - (product.getMax_discount() * product.getPrice() / 100)) %></p>
                <p><strong>Stock:</strong> <%= product.getStock_quantity() %></p>
                

                <form method="post" action="/RevShop_Project_P0/CustomerCartServlet">
                    <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
                    <input type="hidden" name="seller_id" value="<%= product.getSeller_id() %>">
                    <input type="hidden" name="category_id" value="<%= product.getCategory_id() %>">
                    <input type="hidden" name="id" value="<%= isInCart ? 1: 0 %>">
                    <button type="submit" class="btn <%= isInCart ? "btn-success" : "btn-primary" %>">
                        <%= isInCart ? "Already in Cart" : "Add to Cart" %>
                    </button>
                </form>


                <form method="post" action="/RevShop_Project_P0/CustomerWishlistServlet" style="margin-top: 10px;">
                    <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
                    <input type="hidden" name="seller_id" value="<%= product.getSeller_id() %>">
                    <input type="hidden" name="category_id" value="<%= product.getCategory_id() %>">
                    <input type="hidden" name="id" value="<%= isInWishlist ? 1: 0 %>">
                    <button type="submit" class="btn <%= isInWishlist ? "btn-success" : "btn-primary" %>">
                        <%= isInWishlist ? "Already in Wishlist" : "Add to Wishlist" %>
                    </button>
                </form>
            </div>
        </div>

        <%
            } else {
                out.println("<p>Product not found.</p>");
            }
        %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
