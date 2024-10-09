<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* General Styling */
    body {
        background: url('../images/rev2.webp') no-repeat center center fixed;
        background-size: cover;
        font-family: Arial, sans-serif;
    }

    .container {
    border:1px solid yellow;
        background-color: rgba(255, 255, 255, 0.2); /* Semi-transparent container */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(10px); /* Blur effect */
    }

    /* Title */
    h2 {
        color: #333;
        font-weight: bold;
    }

    /* Buttons */
    .user-type-buttons .btn {
        width: 120px;
        margin-right: 10px;
        transition: all 0.3s ease-in-out;
    }

    .user-type-buttons .btn.active {
        background-color: #28a745;
        border-color: #28a745;
        color: white;
    }

    .user-type-buttons .btn:hover {
        background-color: #218838;
        color: white;
        transform: scale(1.05); /* Slight scaling on hover */
    }

    /* Forms */
    form {
    border:1px solid lime;
        background-color: rgba(255, 255, 255, 0.2); /* Slight transparency */
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(10px); /* Blur effect */
    }

    /* Input Fields */
    .form-control {
        border-radius: 5px;
        transition: box-shadow 0.3s ease;
    }

    .form-control:focus {
        box-shadow: 0 0 5px rgba(40, 167, 69, 0.7); /* Green glow on focus */
        border-color: #28a745;
    }

    .input-group-text {
        background-color: rgba(248, 249, 250, 0.2);
        backdrop-filter: blur(10px); /* Blur effect */
    }

    .input-group-text:hover {
        background-color: rgba(233, 236, 239, 0.2);
    }

    /* Password Toggle Icon */
    .input-group-text i {
        color: #333;
        transition: color 0.3s ease;
    }

    .input-group-text:hover i {
        color: #28a745;
    }

    /* Submit Button */
    .btn-success {
        background-color: #28a745;
        border-color: #28a745;
        transition: all 0.3s ease;
    }

    .btn-success:hover {
        background-color: #218838;
        border-color: #1e7e34;
    }

    /* Forgot Password Link */
    a.btn-link {
        color: #007bff;
    }

    a.btn-link:hover {
        color: #0056b3;
        text-decoration: underline;
    }

    /* Registration Link */
    a.registration-link {
        color: #28a745;
        font-weight: bold;
        margin-top: 10px;
        display: inline-block;
    }

    a.registration-link:hover {
        color:red;
        text-decoration: underline;
    }

    /* Modal Styling */
    .modal-content {
        background-color: rgba(255, 255, 255, 0.2);
        border-radius: 10px;
        backdrop-filter: blur(10px); /* Blur effect */
    }
</style>



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
    <div class="container mt-5">
        <h2 class="text-center mb-4">Login to RevShop</h2>

        <div class="user-type-buttons mb-4 text-center">
            <button class="btn btn-primary" onclick="showLoginForm('customer')">Customer</button>
            <button class="btn btn-primary" onclick="showLoginForm('seller')">Seller</button>
        </div>

        <!-- Customer Login Form -->
        <div id="customerForm" style="display: none;">
            <h3>Customer Login</h3>
            <form action="/RevShop_Project_P0/CustomerLoginServlet" method="post" class="form-group">
                <label for="custEmail">Email</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa-solid fa-envelope"></i></span>
                    </div>
                    <input type="email" id="custEmail" name="custEmail" class="form-control" required>
                </div>

                <label for="custPassword">Password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
                    </div>
                    <input type="password" id="custPassword" name="custPassword" class="form-control" required>
                    <div class="input-group-append">
                        <span class="input-group-text" onclick="togglePasswordVisibility('custPassword', 'custPasswordIcon')" style="cursor: pointer;">
                            <i id="custPasswordIcon" class="fa-solid fa-eye"></i>
                        </span>
                    </div>
                </div>

                <button type="submit" class="btn btn-success btn-block">Login</button>
                <a href="#" class="btn btn-link" onclick="showForgotPasswordModal()">Forgot Password?</a>
            </form>
            <a href="Registration_page.jsp" class="registration-link">Don't have an account? Register here!</a> <!-- Registration link -->
        </div>

        <!-- Seller Login Form -->
        <div id="sellerForm" style="display: none;">
            <h3>Seller Login</h3>
            <form action="/RevShop_Project_P0/SellerLoginServlet" method="post" class="form-group">
                <label for="sellEmail">Email</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa-solid fa-envelope"></i></span>
                    </div>
                    <input type="email" id="sellEmail" name="sellEmail" class="form-control" required>
                </div>

                <label for="sellPassword">Password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
                    </div>
                    <input type="password" id="sellPassword" name="sellPassword" class="form-control" required>
                    <div class="input-group-append">
                        <span class="input-group-text" onclick="togglePasswordVisibility('sellPassword', 'sellPasswordIcon')" style="cursor: pointer;">
                            <i id="sellPasswordIcon" class="fa-solid fa-eye"></i>
                        </span>
                    </div>
                </div>

                <button type="submit" class="btn btn-success btn-block">Login</button>
                <a href="#" class="btn btn-link" onclick="showForgotPasswordModal()">Forgot Password?</a>
            </form>
            <a href="Registration_page.jsp" class="registration-link">Don't have an account? Register here!</a> <!-- Registration link -->
        </div>
    </div>

    <!-- Forgot Password Modal -->
    <div class="modal" id="forgotPasswordModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Forgot Password</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/RevShop_Project_P0/ForgotPasswordServlet" method="post">
                        <div class="form-group">
                            <label for="emailForOTP">Enter your email address:</label>
                            <input type="email" id="emailForOTP" name="emailForOTP" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Send OTP</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function showForgotPasswordModal() {
            $('#forgotPasswordModal').modal('show');
        }
    </script>

    <script src="https://kit.fontawesome.com/2f6436cdea.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
