<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../CSS/registration.css">
    <style>
    body{
    background-image: url('/RevShop_Project_P0/Images/MainRevature.png'); /* Path to the background image */
        /* Full screen: 100% of the viewport width and height */
    background-repeat: no-repeat;
    background-position: center;
    background-attachment: fixed; /* Keeps the image fixed during scrolling */

            }
        /* Style for password validation feedback */
        .valid-feedback {
            display: none;
        }
        .invalid-feedback {
            display: none;
        }
        .strong-password {
            color: green;
            display: none;
        }
        .weak-password {
            color: red;
            display: none;
        }

        /* Style for the user type selection buttons */
        .user-type-buttons {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .user-type-buttons .btn {
            margin: 0 10px;
            width: 150px;
        }
        .user-type-buttons .btn:hover {
            opacity: 0.9;
        }
    </style>
    <script>
        function showForm(userType) {
            document.getElementById("customerForm").style.display = userType === 'customer' ? 'block' : 'none';
            document.getElementById("sellerForm").style.display = userType === 'seller' ? 'block' : 'none';
            var buttons = document.querySelectorAll('.user-type-buttons .btn');
            buttons.forEach(function(btn) {
                if (btn.textContent.toLowerCase() === userType) {
                    btn.classList.add('active');
                } else {
                    btn.classList.remove('active');
                }
            });
        }

        function validatePassword(input) {
            // Password validation conditions
            var password = input.value;
            var minLength = password.length >= 8;
            var hasSymbol = /[!@#$%^&*(),.?":{}|<>]/g.test(password);
            var hasAlphabet = /[A-Za-z]/.test(password);

            if (minLength && hasSymbol && hasAlphabet) {
                // Password meets all requirements
                input.classList.remove("is-invalid");
                input.classList.add("is-valid");
                document.getElementById('passwordStrength').classList.remove("weak-password");
                document.getElementById('passwordStrength').classList.add("strong-password");
                document.getElementById('passwordStrength').textContent = "Strong password";
                document.getElementById('passwordStrength').style.display = "block";
            } else {
                // Password does not meet requirements
                input.classList.remove("is-valid");
                input.classList.add("is-invalid");
                document.getElementById('passwordStrength').classList.remove("strong-password");
                document.getElementById('passwordStrength').classList.add("weak-password");
                document.getElementById('passwordStrength').textContent = "Password must be at least 8 characters long, contain one symbol, and one alphabet.";
                document.getElementById('passwordStrength').style.display = "block";
            }
        }
    </script>
</head>
<body>
<div class="container mt-5" >
        <h2 class="text-center mb-4">RevShop</h2>
        <div class="user-type-buttons">
            <button class="btn btn-secondary" onclick="showForm('customer')">Customer</button>
            <button class="btn btn-secondary" onclick="showForm('seller')">Seller</button>
        </div>

        <div id="customerForm" style="display:none;" class="mt-4">
            <h3>Customer Registration</h3>
            <form action="/RevShop_Project_P0/CustomerRegistrationServlet" method="post" class="form-group">
                <label for="custName">Name:</label>
                <input type="text" id="custName" name="custName" class="form-control" required><br>
                
                <label for="custEmail">Email:</label>
                <input type="email" id="custEmail" name="custEmail" class="form-control" required><br>
                
                <label for="custPhone">Phone Number:</label>
                <input type="text" id="custPhone" name="custPhone" class="form-control" required><br>
                
                <label for="custAddress">Address:</label>
                <input type="text" id="custAddress" name="custAddress" class="form-control" required><br>
                
                <label for="custPassword">Password:</label>
                <input type="password" id="custPassword" name="custPassword" class="form-control" onkeyup="validatePassword(this)" required><br>
                
                <div id="passwordStrength" class="weak-password"></div><br>

                <button type="submit" class="btn btn-success btn-block">Register</button>
            </form>
        </div>

        <div id="sellerForm" style="display:none;" class="mt-4">
            <h3>Seller Registration</h3>
            <form action="/RevShop_Project_P0/SellerRegistrationServlet" method="post" class="form-group">
                <label for="sellName">Name:</label>
                <input type="text" id="sellName"  name="sellName" class="form-control" required><br>
                
                <label for="sellEmail">Email:</label>
                <input type="email" id="sellEmail" name="sellEmail" class="form-control" required><br>
                
                <label for="sellPhone">Phone Number:</label>
                <input type="text" id="sellPhone" name="sellPhone" class="form-control" required><br>
                
                <label for="sellAddress">Address:</label>
                <input type="text" id="sellAddress" name="sellAddress" class="form-control" required><br>
                
                <label for="sellPassword">Password:</label>
                <input type="password" id="sellPassword" name="sellPassword" class="form-control" onkeyup="validatePassword(this)" required><br>
                
                <div id="passwordStrengthSeller" class="weak-password"></div><br>

                <button type="submit" class="btn btn-success btn-block">Register</button>
            </form>
        </div>

        <div class="text-center mt-4">
            <p>Already have an account? <a href="/RevShop_Project_P0/JSP/Login_page.jsp">Login</a></p>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>