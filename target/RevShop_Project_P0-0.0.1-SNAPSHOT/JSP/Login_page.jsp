<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../CSS/Login_page.css">
    
    <script>
        function showLoginForm(userType) {
            document.getElementById("customerForm").style.display = "none";
            document.getElementById("sellerForm").style.display = "none";

            if (userType === 'customer') {
                document.getElementById("customerForm").style.display = "block";
            } else {
                document.getElementById("sellerForm").style.display = "block";
            }

            var buttons = document.querySelectorAll('.user-type-buttons .btn');
            buttons.forEach(function(btn) {
                if (btn.textContent.toLowerCase() === userType) {
                    btn.classList.add('active');
                } else {
                    btn.classList.remove('active');
                }
            });
        }
        function togglePasswordVisibility(id, iconId) {
            var passwordField = document.getElementById(id);
            var icon = document.getElementById(iconId);
            if (passwordField.type === "password") {
                passwordField.type = "text";
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            } else {
                passwordField.type = "password";
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            }
        }
    </script>
</head>
<body>
<div class="bubble small"></div>
<div class="bubble medium"></div>
<div class="bubble large"></div>
<div class="bubble extra-large"></div>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Login to RevShop</h2>

        <div class="user-type-buttons">
            <button class="btn" onclick="showLoginForm('customer')">Customer</button>
            <button class="btn" onclick="showLoginForm('seller')">Seller</button>
        </div>
        <!-- Customer Login Form -->
        <div id="customerForm">
            <h3>Customer Login</h3>
            <form action="/Project_P0/CustomerLoginServlet" method="post" class="form-group">
                <label for="custEmail">
                    <b>Email</b>
                </label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa-solid fa-envelope"></i>
                        </span>
                    </div>
                    <input type="email" id="custEmail" name="custEmail" class="form-control" required>
                </div>

                <label for="custPassword">
                    <b>Password</b>
                </label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa-solid fa-lock"></i>
                        </span>
                    </div>
                    <input type="password" id="custPassword" name="custPassword" class="form-control" required>
                    <div class="input-group-append">
                        <span class="input-group-text" onclick="togglePasswordVisibility('custPassword', 'custPasswordIcon')" style="cursor: pointer;">
                            <i id="custPasswordIcon" class="fa-solid fa-eye"></i>
                        </span>
                    </div>
                </div>

                <button type="submit" class="btn btn-success btn-block">Login</button>
            </form>
        </div>
        <!-- Seller Login Form -->
        <div id="sellerForm">
            <h3>Seller Login</h3>
            <form action="/Project_P0/SellerLoginServlet" method="post" class="form-group">
                <label for="sellEmail">
                    <b>Email</b>
                </label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa-solid fa-envelope"></i>
                        </span>
                    </div>
                    <input type="email" id="sellEmail" name="sellEmail" class="form-control" required>
                </div>
                <label for="sellPassword">
                    <b>Password</b>
                </label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa-solid fa-lock"></i>
                        </span>
                    </div>
                    <input type="password" id="sellPassword" name="sellPassword" class="form-control" required>
                    <div class="input-group-append">
                        <span class="input-group-text" onclick="togglePasswordVisibility('sellPassword', 'sellPasswordIcon')" style="cursor: pointer;">
                            <i id="sellPasswordIcon" class="fa-solid fa-eye"></i>
                        </span>
                    </div>
                </div>
                <button type="submit" class="btn btn-success btn-block">Login</button>
            </form>
        </div>

        <div class="loginLink">
            <p>New here? <a href="/Project_P0/JSP/Registration_page.jsp">Register</a></p>
        </div>
    </div>
<script src="https://kit.fontawesome.com/2f6436cdea.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
