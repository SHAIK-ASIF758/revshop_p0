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
        /* General Body Styling */
        body {
           color:black;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url('https://plus.unsplash.com/premium_photo-1681488262364-8aeb1b6aac56?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8ZWNvbW1lcmNlfGVufDB8fDB8fHww');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            backdrop-filter: blur(6px); /* Add background blur */
        }

        /* Container Styling */
        .container {
        color:black;
            background-color: rgba(255, 255, 255, 0.2); /* Transparent with a slight blur */
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
            max-width: 450px; /* Default width */
            width: 100%;
            text-align: center;
            backdrop-filter: blur(10px); /* Slight blur on the container */
            border: 1px solid rgba(255, 255, 255, 0.4); /* Glassy effect border */
            transition: width 0.5s ease; /* Smooth transition for width */
        }

        /* Expanded container for form */
        .expanded {
            max-width: 70%; /* Expanded to 70% width when a form is clicked */
        }

        /* Heading Styling */
        h2, h3 {
            margin-bottom: 24px;
            color: #fff;
            font-weight: bold;
            letter-spacing: 1px;
            text-transform: uppercase;
        }

        /* Input Field Styling */
        input[type="text"], input[type="email"], input[type="password"] {
            border-radius: 20px;
            border: 2px solid #ddd;
            padding: 10px;
            width: 100%;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 16px;
            transition: border 0.3s, box-shadow 0.3s;
        }

        input[type="text"]:focus, input[type="email"]:focus, input[type="password"]:focus {
            border-color: #28a745;
            box-shadow: 0 0 8px rgba(40, 167, 69, 0.6); /* Green glow on focus */
        }

        /* Label Styling */
        label {
            display: block;
            font-size: 14px;
            color: #fff;
            font-weight: bold;
            text-align: left;
            margin-bottom: 8px;
        }

        /* Button Styling */
        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
            color: white;
            padding: 12px 20px;
            border-radius: 25px;
            width: 100%;
            transition: background-color 0.3s, transform 0.3s;
        }

        .btn-success:hover {
            background-color: #218838;
            transform: translateY(-2px);
        }

        .user-type-buttons .btn {
            margin: 0 10px;
            width: 150px;
            transition: background-color 0.3s, transform 0.3s;
        }

        .user-type-buttons .btn:hover {
            background-color: #007bff;
            transform: translateY(-2px);
        }

        /* Password Strength Indicator */
        .strong-password {
            color: #28a745;
            display: none;
        }

        .weak-password {
            color: #ff4d4d;
            display: none;
        }

        /* Registration Link */
        .text-center a {
            color: #fff;
        }

        .text-center a:hover {
            text-decoration: underline;
        }
#customerForm{
 color:black;}
    </style>

    <script>
        function showForm(userType) {
            document.getElementById("customerForm").style.display = userType === 'customer' ? 'block' : 'none';
            document.getElementById("sellerForm").style.display = userType === 'seller' ? 'block' : 'none';

            var container = document.querySelector('.container');
            container.classList.add('expanded'); // Add expanded class when form is shown

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
            var password = input.value;
            var minLength = password.length >= 8;
            var hasSymbol = /[!@#$%^&*(),.?":{}|<>]/g.test(password);
            var hasAlphabet = /[A-Za-z]/.test(password);

            if (minLength && hasSymbol && hasAlphabet) {
                input.classList.remove("is-invalid");
                input.classList.add("is-valid");
                document.getElementById('passwordStrength').classList.remove("weak-password");
                document.getElementById('passwordStrength').classList.add("strong-password");
                document.getElementById('passwordStrength').textContent = "Strong password";
                document.getElementById('passwordStrength').style.display = "block";
            } else {
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
    <div class="container">
        <h2>RevShop</h2>
        <div class="user-type-buttons">
            <button class="btn btn-secondary" onclick="showForm('customer')">Customer</button>
            <button class="btn btn-secondary" onclick="showForm('seller')">Seller</button>
        </div>

        <div id="customerForm" style="display:none;">
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

        <div id="sellerForm" style="display:none;">
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
