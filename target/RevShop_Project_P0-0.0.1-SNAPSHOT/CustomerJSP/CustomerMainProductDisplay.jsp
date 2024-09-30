<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.Categories_DAO" %>
<%@ page import="DAO.Product_DAO" %>
<%@ page import="ENTITY.Products" %>
<%@ page import="DTO.Customer_DTO" %>
<%@ page import="ENTITY.Categories" %>
<%@ page import="java.util.List" %>
<% Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
 String Name = customer_DTO.getName();
 String Email =customer_DTO.getEmail();
 Name = Character.toUpperCase(Name.charAt(0)) + Name.substring(1);
 Email=Character.toUpperCase(Email.charAt(0)) + Email.substring(1);%>
<% 
    Boolean orderSuccess = (Boolean) request.getAttribute("orderSuccess");
    if (orderSuccess != null && orderSuccess) { 
%>
    <div id="success-message" class="success-message">
        Order placed successfully!
    </div>
    <script>
        setTimeout(function() {
            document.getElementById('success-message').style.opacity = '0';
        }, 3000);

        setTimeout(function() {
            document.getElementById('success-message').style.display = 'none';
        }, 4000); 
    </script>
<% 
    } 
%>

<style>
    .success-message {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%; /* Full width */
        background-color: #4CAF50; /* Green background for success */
        color: white;
        padding: 15px;
        text-align: center; /* Center the text */
        font-size: 16px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        z-index: 1000;
        opacity: 1;
        transition: opacity 1s ease-in-out; /* Smooth fading effect */
    }
</style>



 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RevShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    <style>
    
    .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            
        }
    </style>

</head>
<body>
    <%@ include file="Customer_navbar.jsp" %>
    <div class="header-container" style="margin:20px ;padding:0 25px 0 25px;">
            <h4 id="customer_name">Welcome, <%= Name %></h4>
            <h6 id="customer_email" ><%= Email %></h6>
        </div>
    <div class="container">
        <hr>
        <form method="get" action="/RevShop_Project_P0/CustomerMainServlet">
            <div class="form-group">
                <label for="category">Select Category:</label>
                <select id="category" name="category_id" class="form-control" onchange="this.form.submit()">
                    <option value="-1" <%= (request.getParameter("category_id") == null || request.getParameter("category_id").equals("-1")) ? "selected" : "" %>>--All Products--</option>
                    <% 
                        Categories_DAO categories_DAO = new Categories_DAO();
                        List<Categories> categories = categories_DAO.allCategories();
                        String selectedCategory = request.getParameter("category_id");
                        for (Categories category : categories) {
                            String isSelected = (selectedCategory != null && selectedCategory.equals(String.valueOf(category.getCategory_id()))) ? "selected" : "";
                            out.println("<option value='" + category.getCategory_id() + "' " + isSelected + ">" + category.getCategory_name() + "</option>");
                        }
                    %>
                </select>
            </div>
        </form>
       
        <div class="row">
            <%
                Product_DAO productsDAO = new Product_DAO();
                List<Products> productList;

                String selectedCategoryParam = request.getParameter("category_id");
                if (selectedCategoryParam != null && !selectedCategoryParam.equals("-1")) {
                    try {
                        int categoryId = Integer.parseInt(selectedCategoryParam);
                        productList = productsDAO.allProductsByCategory(categoryId);
                    } catch (NumberFormatException e) {
                        productList = productsDAO.allProducts();
                    }
                } else {
                    productList = productsDAO.allProducts();
                }

                if (productList != null && !productList.isEmpty()) {
            %>
            <div class="row" style="margin:10px;">
                <% for (Products product : productList) { %>
                <div class="col-md-4">
    <div class="card mb-4">
        <a href="/RevShop_Project_P0/CustomerJSP/ProductDetails.jsp?product_id=<%= product.getProduct_id() %>">
            <img src="<%= product.getImage_url() %>" style="height: 270px;object-fit: cover;" class="card-img-top" alt="<%= product.getProduct_name() %>">
        </a>
        <div class="card-body">
            <h5 class="card-title"><%= product.getProduct_name() %></h5>
            <p class="card-text"><%= product.getDescription() %></p>
            <p class="card-text"><strong>Price:</strong> ₹<%= product.getPrice() %></p>
            <p class="card-text"><strong>Discount Price:</strong> ₹<%=String.format("%.2f",product.getPrice() - (product.getMax_discount() * product.getPrice() / 100)) %></p>
            <p class="card-text"><strong>Stock-Left:</strong> <%= product.getStock_quantity() %></p>
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
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
