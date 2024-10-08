<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.Seller_DTO" %>
<%@ page import="ENTITY.Categories" %>
<%@ page import="DAO.Categories_DAO" %>
<%@ page import="java.util.List" %>
<% Seller_DTO seller_DTO = (Seller_DTO) session.getAttribute("seller");%>
<% Categories_DAO cat = new Categories_DAO(); List<Categories> catlist = cat.allCategories(); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RevShop - Add Product</title>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        /* Custom CSS for form styling */
        body {
            background-color: #f5f5f5;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
        }
        
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            font-weight: 600;
        }
        
        .form-label {
            font-weight: bold;
            color: #555;
        }
        
        .form-control, .form-select {
            border-radius: 0.25rem;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease;
        }

        .form-control:focus, .form-select:focus {
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        button[type="submit"] {
            background-color: #007bff;
            border-color: #007bff;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Spacing adjustments */
        .mb-3 {
            margin-bottom: 20px;
        }

        .btn-primary {
            width: 100%;
            padding: 10px;
            font-size: 18px;
            font-weight: bold;
        }

        /* Styling the select dropdown */
        select.form-select {
            padding: 10px;
        }

        /* Mobile responsiveness */
        @media (max-width: 576px) {
            .container {
                padding: 20px;
            }

            h2 {
                font-size: 24px;
            }

            button[type="submit"] {
                font-size: 16px;
            }
        }
        h2{
        
        color:blue;}
    </style>
</head>
<body>
<%@ include file="Seller_navBar.jsp" %>
<% Seller_DTO seller = (Seller_DTO) session.getAttribute("seller"); %>

<div class="container mt-5">
    <h2>Add New Product</h2>
    <hr>

    <form action="/RevShop_Project_P0/SellerAddProductServlet" method="post">
        <!-- Fetching Categories from Database for Dropdown -->
        <div class="mb-3">
            <label for="category_id" class="form-label">Category</label>
            <select class="form-select" id="category_id" name="category_id" required>
                <option value="">Select Category</option>
                <% for (Categories category : catlist) { %>
                    <option value="<%= category.getCategory_id() %>"><%= category.getCategory_name() %></option>
                <% } %>
            </select>
        </div>

        <input type="hidden" name="seller_id" value="<%= seller.getSeller_id() %>">

        <div class="mb-3">
            <label for="product_name" class="form-label">Product Name</label>
            <input type="text" class="form-control" id="product_name" name="product_name" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" required>
        </div>

        <div class="mb-3">
            <label for="stock_quantity" class="form-label">Stock Quantity</label>
            <input type="number" class="form-control" id="stock_quantity" name="stock_quantity" required>
        </div>

        <div class="mb-3">
            <label for="image_url" class="form-label">Image URL</label>
            <input type="url" class="form-control" id="image_url" name="image_url" required>
        </div>

        <div class="mb-3">
            <label for="threshold" class="form-label">Threshold (Minimum Stock Level)</label>
            <input type="number" class="form-control" id="threshold" name="threshold" required>
        </div>

        <div class="mb-3">
            <label for="max_discount" class="form-label">Max Discount</label>
            <input type="number" step="0.01" class="form-control" id="max_discount" name="max_discount" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Product</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
