<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ENTITY.Products" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.Categories_DAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Products</title>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #f0f2f5;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        h1 {
            font-size: 2.5rem;
            font-weight: bold;
            text-align: center;
            margin-bottom: 40px;
            color: #333;
        }

        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .card-img-top {
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
            object-fit: cover;
            transition: transform 0.3s ease;
        }

        .card-img-top:hover {
            transform: scale(1.05);
        }

        .card-title {
            font-size: 1.5rem;
            font-weight: bold;
            color: #007bff;
        }

        .card-text {
            font-size: 1rem;
            color: #555;
        }

        .btn-primary, .btn-danger {
            border-radius: 50px;
            padding: 10px 20px;
            font-size: 1rem;
            font-weight: bold;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .btn-primary {
            background: linear-gradient(90deg, #007bff 0%, #0056b3 100%);
            border: none;
        }

        .btn-danger {
            background: linear-gradient(90deg, #ff416c 0%, #ff4b2b 100%);
            border: none;
        }

        .btn-primary:hover, .btn-danger:hover {
            box-shadow: 0 4px 12px rgba(0, 123, 255, 0.4);
        }

        .container {
            margin-top: 50px;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            h1 {
                font-size: 2rem;
            }

            .card-title {
                font-size: 1.25rem;
            }

            .btn-primary, .btn-danger {
                font-size: 0.875rem;
            }
        }
    </style>
</head>
<body>
    <%@ include file="Seller_navBar.jsp" %> 
    <div class="container">
        <h1>My Products</h1>
        <hr>
        <div class="row">
            <% 
            Categories_DAO category = new Categories_DAO();
            List<Products> productList = (List<Products>) request.getAttribute("productList");
            if (productList != null && !productList.isEmpty()) {
            %>
            <div class="row">
                <% for (Products product : productList) { %>
                <div class="col-md-4">
                    <div class="card mb-4">
                        <img src="<%= product.getImage_url() %>" style="height: 270px;" class="card-img-top" alt="<%= product.getProduct_name() %>">
                        <div class="card-body">
                            <h5 class="card-title"><%= product.getProduct_name() %></h5>
                            <p class="card-text"><%= product.getDescription() %></p>
                            <p class="card-text"><strong>Price:</strong> ₹<%= product.getPrice() %></p>
                            <p class="card-text"><strong>Discount Price:</strong> ₹<%= product.getPrice() - (product.getMax_discount() * product.getPrice() / 100) %></p>
                            <p class="card-text"><strong>Stock-Left:</strong> <%= product.getStock_quantity() %></p>
                            <p class="card-text"><strong>Category:</strong> <%= category.getCategoryById(product.getCategory_id()).getCategory_name() %></p>

                            <button class="btn btn-primary" onclick="updateProduct('<%= product.getProduct_id() %>')">Update</button>
                            <button class="btn btn-danger" onclick="deleteProduct('<%= product.getProduct_id() %>')">Delete</button>
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
