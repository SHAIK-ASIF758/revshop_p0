<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.Categories_DAO" %>
<%@ page import="DAO.Product_DAO" %>
<%@ page import="ENTITY.Products" %>
<%@ page import="DTO.Customer_DTO" %>
<%@ page import="ENTITY.Categories" %>
<%@ page import="java.util.List" %>
<% 
    Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
    String Name = customer_DTO.getName();
    String Email = customer_DTO.getEmail();
    Name = Character.toUpperCase(Name.charAt(0)) + Name.substring(1);
    Email = Character.toUpperCase(Email.charAt(0)) + Email.substring(1);
%>

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
    /* Success Message Styles */
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

    /* General Page Styles */
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f8f9fa; /* Light background for contrast */
        margin: 0;
        padding: 0;
    }

    /* Header Container */
    .header-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 20px 25px;
        background-color: grey; /* Dark background for header */
        color: white; /* White text for contrast */
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    /* Form Styles */
    .form-group {
        margin-bottom: 20px; /* Space between form elements */
    }

    .form-control {
        border-radius: 5px; /* Rounded corners */
        border: 1px solid black; /* Border color */
        transition: border-color 0.3s ease; /* Smooth border color transition */
    }

    .form-control:focus {
        border-color: #80bdff; /* Border color on focus */
        box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25); /* Focus shadow */
    }

    /* Card Styles */
    .card {
        border: none; /* No border for cards */
        border-radius: 10px; /* Rounded corners for cards */
        transition: transform 0.3s ease; /* Smooth scaling effect */
        margin-bottom: 20px; /* Space between cards */
        height: 40%; /* Full height for uniform cards */
    }

    .card:hover {
        transform: scale(1.02); /* Slightly enlarge card on hover */
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2); /* Shadow on hover */
    }

    .card-title {
        font-size: 1.1rem; /* Smaller font size for titles */
        color: #212529; /* Dark text color */
    }

    .card-text {
        color: #6c757d; /* Secondary text color */
    }

    /* Layout Styles */
    .container {
        margin: 20px auto; /* Centered container */
        max-width: 1200px; /* Maximum width for large screens */
        padding: 15px; /* Inner padding */
        background-color: #ffffff; /* White background for content */
        border-radius: 10px; /* Rounded corners */
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* Subtle shadow */
    }

    /* Product Grid */
    .row {
        display: flex; /* Flexbox layout for products */
        flex-wrap: wrap; /* Wrap products to the next line */
        justify-content: center; /* Center products */
    }

    .col-md-3 {
        flex: 0 0 22%; /* Fixed width for product columns (22% for 4 items per row) */
        max-width: 22%; /* Maximum width for product columns */
        margin: 10px; /* Space between columns */
    }

    /* Image Styles */
    .card-img-top {
        height: 200px; /* Fixed height for images */
        object-fit: contain; /* Ensure the full image is displayed */
        border-radius: 10px 10px 0 0; /* Rounded top corners */
    }

    /* Responsive adjustments */
    @media (max-width: 768px) {
        .col-md-3 {
            flex: 0 0 45%; /* Adjust width for medium screens (2 items per row) */
            max-width: 45%;
        }
    }

    @media (max-width: 576px) {
        .col-md-3 {
            flex: 0 0 100%; /* Full width for small screens (1 item per row) */
            max-width: 100%;
        }
    }

    /* No Products Message */
    .no-products {
        text-align: center; /* Center the message */
        font-size: 1.5rem; /* Larger font size */
        color: #dc3545; /* Red color for no products */
    }
</style>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RevShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
</head>
<body>
    <%@ include file="Customer_navbar.jsp" %>
    <div class="header-container">
        <h4 id="customer_name">Hello Mr: <%= Name %></h4>
        <h6 id="customer_email"><%= Email %></h6>
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
                <div class="col-md-3">
                    <div class="card mb-4">
                        <a href="/RevShop_Project_P0/CustomerJSP/ProductDetails.jsp?product_id=<%= product.getProduct_id() %>">
                            <img src="<%= product.getImage_url() %>" class="card-img-top" alt="<%= product.getProduct_name() %>">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title"><%= product.getProduct_name() %></h5>
                            <p class="card-text"><%= product.getDescription() %></p>
                            <p class="card-text"><strong>Price:</strong> ₹<%= product.getPrice() %></p>
                            <p class="card-text"><strong>Discount Price:</strong> ₹<%= String.format("%.2f", product.getPrice() - (product.getMax_discount() * product.getPrice() / 100)) %></p>
                            <p class="card-text"><strong>Stock-Left:</strong> <%= product.getStock_quantity() %></p>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
            <% } else { %>
                <p class="no-products">No products found.</p>
            <% } %>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
