<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ENTITY.Products" %>
<%@ page import="DAO.Product_DAO" %>
<%
    String productId = request.getParameter("productId");
    Product_DAO product_DAO=new Product_DAO();
    Products product = product_DAO.getProductDetails(Integer.parseInt(productId));

    if (product == null) {
        out.println("Product not found");
        return;
    }
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="Seller_navBar.jsp" %> 
    <div class="container">
        <h1>Update Product Details</h1>
        <form action="/RevShop_New/SellerUpdateProductServlet" method="post">
            <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">

            <div class="mb-3">
                <label for="productName" class="form-label">Product Name</label>
                <input type="text" class="form-control" id="productName" name="productName" value="<%= product.getProduct_name() %>" required>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required><%= product.getDescription() %></textarea>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" class="form-control" id="price" name="price" value="<%= product.getPrice() %>" step="0.01" required>
            </div>

            <div class="mb-3">
                <label for="stock" class="form-label">Stock Quantity</label>
                <input type="number" class="form-control" id="stock" name="stock" value="<%= product.getStock_quantity() %>" required>
            </div>

            <div class="mb-3">
                <label for="image_url" class="form-label">Image URL</label>
                <input type="url" class="form-control" id="image_url" name="image_url" value="<%= product.getImage_url() %>" required>
            </div>
            <div class="mb-3">
                <label for="threshold" class="form-label">Threshold</label>
                <input type="number" class="form-control" id="threshold" name="threshold" value="<%= product.getThreshold() %>" required>
            </div>
            <div class="mb-3">
                <label for="max_discount" class="form-label">Max Discount</label>
                <input type="number" step="0.01" class="form-control" id="max_discount" name="max_discount" value="<%= product.getMax_discount() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Product</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
    