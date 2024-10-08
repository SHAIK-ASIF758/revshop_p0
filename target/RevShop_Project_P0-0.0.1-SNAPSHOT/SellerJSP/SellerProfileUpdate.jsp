<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.Seller_DTO" %>
<%@ page import="java.util.*" %>
<% Seller_DTO seller =(Seller_DTO) session.getAttribute("seller");%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Seller Details</title>
    <link rel="stylesheet" href="../CSS/Seller_Main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <%@ include file="Seller_navBar.jsp" %> 
    <div class="container">
        <h2>Update Seller Details</h2>
        <hr>
        <form action="SellerUpdateDetailsServlet" method="post">
            <div class="mb-3">
                <label for="seller_name" class="form-label">Name</label>
                <input type="text" class="form-control" id="seller_name" name="seller_name" value="<%= seller.getSeller_name() %>" required>
            </div>
            <div class="mb-3">
                <label for="seller_email" class="form-label">Email</label>
                <input type="email" class="form-control" id="seller_email" name="seller_email" value="<%= seller.getSeller_email() %>" required>
            </div>
            <div class="mb-3">
                <label for="seller_phone_number" class="form-label">Phone Number</label>
                <input type="text" class="form-control" id="seller_phone_number" name="seller_phone_number" value="<%= seller.getSeller_phone_number() %>" required>
            </div>
            <div class="mb-3">
                <label for="seller_address" class="form-label">Address</label>
                <input type="text" class="form-control" id="seller_address" name="seller_address" value="<%= seller.getSeller_address() %>" required>
            </div>
            <div class="mb-3">
                <label for="seller_password" class="form-label">Change Password</label>
                 <input type="password" class="form-control" id="seller_password" name="seller_password" placeholder="Enter a new password if you want to change (min-10 characters with special character, alphabet, and number)"> 	
           </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
    