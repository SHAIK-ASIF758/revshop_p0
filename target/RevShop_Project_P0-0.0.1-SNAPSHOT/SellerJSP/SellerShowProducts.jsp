<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="ENTITY.Products" %>
    <%@ page import="java.util.List" %>
<%@ page import="DAO.Categories_DAO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seller Products</title>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
    <%@ include file="Seller_navBar.jsp" %> 
    <div class="container">
        <h1>My Products</h1>
        <hr>
        <div class="row">
            <%
            Categories_DAO category=new Categories_DAO();
    List<Products> productList = (List<Products>) request.getAttribute("productList");
    if (productList != null && !productList.isEmpty()) {
%>
    <div class="row">
        <% for (Products product : productList) { %>
        <div class="col-md-4">
            <div class="card mb-4">
                <img  src="<%= product.getImage_url() %>" style="height: 270px;object-fit: cover;" class="card-img-top" alt="<%= product.getProduct_name() %>">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getProduct_name() %></h5>
                    <p class="card-text"><%= product.getDescription() %></p>
                    <p class="card-text"><strong>Price:</strong> ₹<%= product.getPrice() %></p>
                    <p class="card-text"><strong>Discount Price:</strong> ₹<%= product.getPrice() - (product.getMax_discount() * product.getPrice() / 100) %></p>
                    <p class="card-text"><strong>Stock-Left:</strong> <%= product.getStock_quantity() %></p>
                    <p class="card-text"><strong>Category:</strong> <%= category.getCategoryById(product.getCategory_id()).getCategory_name() %></p>

                    <button class="btn btn-primary" onclick="updateProduct('<%= product.getProduct_id() %>')">Update</button>

                    <button class="btn btn-danger" onclick="deleteProduct('<%= product.getProduct_id() %>')">Delete Product</button>
                </div>
            </div>
        </div>
        <% } %>
    </div>
<% } else { %>
    <p>No products found.</p>
<% } %>
  
        </div>
    </div>

    <script>
        function updateProduct(productId) {

            window.location.href = 'JSP/SellerUpdateProducts.jsp?productId=' + productId;

        }

        function deleteProduct(productId) {
            if (confirm('Are you sure you want to delete this product?')) {
            	 fetch('SellerDeleteProductServlet?productId=' + productId, {
                     method: 'POST'
                 })
                 .then(response => response.text())
                 .then(result => {
                     if (result === 'success') {
                         alert('Product deleted successfully!');
                         location.reload();
                     } else {
                         alert('Failed to delete the product.');
                     }
                 })
                 .catch(error => console.error('Error:', error));
            }
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
    