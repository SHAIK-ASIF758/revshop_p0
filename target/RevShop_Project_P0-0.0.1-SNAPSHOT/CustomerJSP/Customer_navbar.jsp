<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RevShop</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">

    <!-- Custom CSS for Hover Effects -->
    <style>
        /* General Body Styling */
        body {
            background-color: #f8f9fa;
        }

        /* Navbar Styling */
        .navbar {
            margin-bottom: 20px;
        }

        .navbar-nav {
            display: flex;
            justify-content: space-around;
            align-items: center;
            flex-direction: row;
        }

        /* Navbar Links */
        .navbar-nav .nav-link {
            color: white;
            transition: all 0.3s ease; /* Smooth transition on hover */
            border: 2px solid transparent;
        }

        /* Hover effect for navbar links */
        .navbar-nav .nav-link:hover {
            color: #ffc107; /* Change text color on hover */
            background-color: #343a40; /* Slight background color change */
            border: 2px solid #ffc107; /* Add a yellow border on hover */
            border-radius: 5px; /* Slight border radius on hover */
        }

        /* Dropdown Styles */
        .dropdown-menu {
            background-color: #343a40;
            border: none;
        }

        .dropdown-item {
            color: white;
            transition: background-color 0.3s ease, color 0.3s ease, border 0.3s ease; /* Smooth transition on hover */
            border-left: 2px solid transparent;
        }

        .dropdown-item:hover {
            background-color: #ffc107;
            color: black;
            border-left: 2px solid #343a40;
        }

        /* Profile Icon Styling */
        .profile-icon {
            width: 30px;
            height: 30px;
            border-radius: 50%; /* Make the icon circular */
            transition: transform 0.3s ease, border 0.3s ease; /* Add scaling and border transition */
        }

        .profile-icon:hover {
            transform: scale(1.1); /* Slightly increase size on hover */
            border: 2px solid #ffc107; /* Add yellow border on hover */
        }

        /* Flexbox Spacing for Dropdown Items */
        .navbar-nav .nav-item {
            position: relative;
            margin-bottom: 10px; /* Add space between each nav-item */
        }

        /* Transition effects for dropdown */
        .navbar-nav .dropdown-menu {
            display: none;
            opacity: 0;
            transition: opacity 0.5s ease;
        }

        .nav-item:hover .dropdown-menu {
            display: block;
            opacity: 1;
        }

    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/RevShop_Project_P0/CustomerMainServlet">Products</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item dropdown" style="margin: 0 33px;">
                        <a class="nav-link dropdown-toggle" href="#" id="newsDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            News & Guidelines
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="newsDropdown">
                            <li><a class="dropdown-item" href="/RevShop_Project_P0/JSP/ErrorPage.jsp">News</a></li>
                            <li><a class="dropdown-item" href="/RevShop_Project_P0/JSP/ErrorPage.jsp">Guideline</a></li>
                        </ul>
                    </li>
                    <li class="nav-item" style="margin: 0 28px;">
                        <a class="nav-link" href="/RevShop_Project_P0/CustomerFavoriteActionsServlet">Wishlist</a>
                    </li>
                    <li class="nav-item" style="margin: 0 28px;">
                        <a class="nav-link" href="/Project_P0/CustomerCartServlet">My Cart</a>
                    </li>
                    <li class="nav-item" style="margin: 0 28px;">
                        <a class="nav-link" href="/Project_P0/CustomerJSP/CustomerOrderHistory.jsp">Orders History</a>
                    </li>
                    <li class="nav-item dropdown" style="margin: 0 28px;">
                        <a class="nav-link dropdown-toggle" href="#" id="helpDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Help
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="helpDropdown">
                            <li><a class="dropdown-item" href="/Project_P0/JSP/ErrorPage.jsp">Chat Bot</a></li>
                            <li><a class="dropdown-item" href="/Project_P0/JSP/ErrorPage.jsp">Customer Care</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown" style="margin: 0 15px;">
                        <a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="../images/profileicon.png" alt="Profile" class="profile-icon">
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="profileDropdown">
                            <li><a class="dropdown-item" href="/Project_P0/CustomerUpdateProfile">Update Profile</a></li>
                            <li><a class="dropdown-item" href="/Project_P0/CustomerAddToWalletServlet">Wallet</a></li>
                            <li><a class="dropdown-item" href="/Project_P0/JSP/Login_page.jsp">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Load Bootstrap JS and Popper.js at the end of the body for better performance -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
